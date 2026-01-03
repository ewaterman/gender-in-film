package com.ewaterman.genderinfilm.common.base;

import org.springframework.web.bind.annotation.RestController;

/**
 * Base class for all controllers that returns non-HTML data (such as JSON).
 *
 * The reason we distinguish between APIs and components (instead of treating components as APIs that return HTML)
 * is that thymeleaf can't render templates for @RestController annotated classes.
 */
@RestController
public abstract class ApiController {
}
