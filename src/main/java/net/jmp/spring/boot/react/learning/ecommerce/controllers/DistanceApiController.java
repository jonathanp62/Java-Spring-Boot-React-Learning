package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)DistanceApiController.java    0.4.0   02/20/2026
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

import java.util.List;
import java.util.Optional;

import java.util.function.Consumer;

import net.jmp.spring.boot.react.learning.ecommerce.SalesTax;

import net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument;

import net.jmp.spring.boot.react.learning.ecommerce.services.SalesTaxService;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;
import net.jmp.spring.boot.react.learning.ecommerce.helpers.OptionalToResponseEntityMapper;
import net.jmp.spring.boot.react.learning.ecommerce.helpers.UserRoleChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

/// The distance API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/distance")
public class DistanceApiController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The sales tax service
//    private final SalesTaxService salesTaxService;

    /// The log tracer
    private final LogTracer logTracer;

    /// The user role checker
    private final UserRoleChecker userRoleChecker;

    /// The constructor
    ///
    /// @param   salesTaxService net.jmp.spring.boot.react.learning.ecommerce.services.SalesTaxService
    public DistanceApiController(/*final SalesTaxService salesTaxService*/) {
        super();

//        this.salesTaxService = salesTaxService;
        this.logTracer = new LogTracer(this.logger);
        this.userRoleChecker = new UserRoleChecker();
    }

    /// The OK method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return this.logTracer.traced(() -> new ResponseEntity<>("OK", HttpStatus.OK));
    }
}
