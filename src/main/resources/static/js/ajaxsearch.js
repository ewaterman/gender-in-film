// Used to issue AJAX requests to search for movie data. All that's needed is to define HTML elements with ids matching
// the below constants. We'll eventually want to make this generic by creating a JS function that takes the below
// constants as method args and dynamically adds the jquery function listeners to those elements.
const user_input = $("#user-input")
const search_icon = $('#search-icon')
const replaceable_div = $('#replaceable-content')
const endpoint = '/components/movies/search'
const delay_by_in_ms = 500
let scheduled_function = false

let render_result = function(html) {
    replaceable_div.fadeTo('slow', 0).promise().then(() => {
        replaceable_div.html(html)
        replaceable_div.fadeTo('slow', 1)
        search_icon.removeClass('blink')
    })
}

// Actually perform the call to fetch the data
let ajax_call = function (endpoint, request_parameters) {
    // If the searchbar is blank (which is possible if you typed and then deleted), don't search for anything.
    if (request_parameters.movieTitle.length == 0) {
        render_result("")
        return
    }

    $.get(endpoint, request_parameters).done(response => {
        render_result(response)
    })
}

// When there's typing in the searchbar, queue up a search request on a delay
user_input.on('keyup', function () {
    search_icon.addClass('blink')

    // We only want to issue the request if we've stopped typing so if we're still typing, reset the timer.
    if (scheduled_function) {
        clearTimeout(scheduled_function)
    }

    const request_parameters = {
        movieTitle: $(this).val()  // value of the input HTML element with id "user-input" (ie the searchbar)
    }
    scheduled_function = setTimeout(ajax_call, delay_by_in_ms, endpoint, request_parameters)
})

// When the searchbar loses focus, hide the search results. The delay is to ensure that if the user clicks
// a search result, that gets triggered first. Otherwise the element would be hidden before the click event.
user_input.focusout(function() {
    setTimeout(
        function() {
            replaceable_div.hide()
        },
        200); // 100 ms is the minimum
})

// When the searchbar gains focus, show the search results
user_input.focusin(function() {
    replaceable_div.show()
})
