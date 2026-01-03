package com.ewaterman.genderinfilm.common.base;

import org.springframework.stereotype.Controller;

/**
 * Base class for all controllers that returns some data formatted as HTML templates (ie not a full web page).
 *
 * The reason we distinguish between APIs and components (instead of treating components as APIs that return HTML)
 * is that thymeleaf can't render templates for @RestController annotated classes.
 */
@Controller
public abstract class ComponentController {
}
