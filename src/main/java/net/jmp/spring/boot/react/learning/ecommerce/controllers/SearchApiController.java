package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)SearchApiController.java  0.4.0   03/21/2026
 *
 * @author    Jonathan Parker
 * @version   0.4.0
 * @since     0.4.0
 *
 * MIT License
 *
 * Copyright (c) 2026 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.services.SearchService;

import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// The search API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/search/ecommerce-products")
public class SearchApiController {
    /// The log tracer
    private final LogTracer logTracer;

    /// The search service
    private final SearchService searchService;

    /// The constructor
    ///
    /// @param  searchService  net.jmp.spring.boot.react.learning.ecommerce.services.SearchService
    public SearchApiController(final SearchService searchService) {
        this.searchService = searchService;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The OK method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return this.logTracer.traced(() -> new ResponseEntity<>("OK", HttpStatus.OK));
    }

    /// The ping method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return this.logTracer.traced(() -> {
            final int status = this.searchService.ping();

            String message;

            if (status == 0) {
                message = "Pinged Solr OK";
            } else {
                message = "Failed to ping Solr";
            }

            return new ResponseEntity<>(message, HttpStatus.OK);
        });
    }
}
