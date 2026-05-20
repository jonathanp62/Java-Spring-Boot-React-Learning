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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import org.springframework.context.MessageSource;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

/// The error controller class. Used to test the error template.
@Controller
public class ErrorController {
    /// The message source
    final MessageSource messageSource;

    /// The constructor
    ///
    /// @param  messageSource   org.springframework.context.MessageSource
    public ErrorController(final MessageSource messageSource) {
        super();

        this.messageSource = messageSource;
    }

    /// Maps GET requests for the "/error-test" path to the error template.
    ///
    /// @param  request     jakarta.servlet.http.HttpServletRequest
    /// @param  response    jakarta.servlet.http.HttpServletResponse
    /// @param  model       org.springframework.ui.Model
    /// @return             java.lang.String
    @GetMapping("/error-test")
    public String error(final HttpServletRequest request, final HttpServletResponse response, final Model model) {
        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);

        model.addAttribute("status", HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        model.addAttribute("error", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        model.addAttribute("path", request.getRequestURI());
        model.addAttribute("message", this.messageSource.getMessage("j.testing.error.template", null, LocaleContextHolder.getLocale()));

        return "error";
    }
}
