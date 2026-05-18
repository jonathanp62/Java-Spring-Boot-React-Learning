package net.jmp.spring.boot.react.learning.controller;

/*
 * (#)ErrorController.java  0.6.0   05/18/2026
 *
 * @author    Jonathan Parker
 * @version   0.6.0
 * @since     0.6.0
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

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

/// The error controller class. Used to test the error template.
@Controller
public class ErrorController {
    /// The default constructor
    public ErrorController() {
        super();
    }

    /// Maps GET requests for the "/error-test" path to the error template.
    ///
    /// @param  response    jakarta.servlet.http.HttpServletResponse
    /// @throws             java.io.IOException                         If an I/O error occurs
    @GetMapping("/error-test")
    public void error(final HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Testing error template rendering");
    }
}
