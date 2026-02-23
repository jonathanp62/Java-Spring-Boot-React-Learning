package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)DistanceController.java   0.4.0   02/23/2026
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

import net.jmp.spring.boot.react.learning.ecommerce.services.DistanceService;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

/// The distance controller class.
@Controller
public class DistanceController {
    /// The distance service
    private final DistanceService distanceService;

    /// The log tracer
    private final LogTracer logTracer;

    /// The constructor
    ///
    /// @param  distanceService net.jmp.spring.boot.react.learning.ecommerce.services.DistanceService
    public DistanceController(final DistanceService distanceService) {
        super();

        this.distanceService = distanceService;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// Maps GET requests for the e-commerce distance path to the "e-commerce/distance" view.
    ///
    /// @return java.lang.String
    @GetMapping("/e-commerce/distance/")
    public String distances(final Model model) {
        return this.logTracer.tracedWith(() -> {
            model.addAttribute("distanceList", this.distanceService.getDistances());

            return "e-commerce/distance";

        }, model);
    }
}
