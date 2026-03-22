package net.jmp.spring.boot.react.learning.ecommerce.controllers.rest;

/*
 * (#)SalesTaxApiController.java    0.2.0   01/11/2026
 * (#)SalesTaxApiController.java    0.1.0   12/20/2025
 *
 * @author    Jonathan Parker
 * @version   0.2.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025, 2026 Jonathan M. Parker
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

/// The sales tax API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/sales-tax")
public class SalesTaxApiController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The sales tax service
    private final SalesTaxService salesTaxService;

    /// The log tracer
    private final LogTracer logTracer;

    /// The user role checker
    private final UserRoleChecker userRoleChecker;

    /// The constructor
    ///
    /// @param   salesTaxService net.jmp.spring.boot.react.learning.ecommerce.services.SalesTaxService
    public SalesTaxApiController(final SalesTaxService salesTaxService) {
        super();

        this.salesTaxService = salesTaxService;
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

    /// The get all sales tax documents method
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>>
    @GetMapping("/")
    public ResponseEntity<List<SalesTaxDocument>> salesTaxes() {
        return this.logTracer.traced(() -> {
            final List<SalesTaxDocument> documents = this.salesTaxService.getSalesTaxes();

            return new ResponseEntity<>(documents, HttpStatus.OK);
        });
    }

    /// The get sales tax document by state name method
    ///
    /// @param   stateName  java.lang.String
    /// @return             org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @GetMapping("/{stateName}")
    public ResponseEntity<SalesTaxDocument> salesTaxByStateName(final @PathVariable String stateName) {
        return this.logTracer.tracedWith(() ->
            OptionalToResponseEntityMapper.map(this.salesTaxService.getSalesTaxByStateName(stateName)), stateName);
    }

    /// The get sales tax document by state abbreviation method
    ///
    /// @param  stateAbbreviation   java.lang.String
    /// @return                     org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @GetMapping("/abbr/{stateAbbreviation}")
    public ResponseEntity<SalesTaxDocument> salesTaxByStateAbbreviation(final @PathVariable String stateAbbreviation) {
        return this.logTracer.tracedWith(() ->
            OptionalToResponseEntityMapper.map(this.salesTaxService.getSalesTaxByStateAbbreviation(stateAbbreviation)), stateAbbreviation);
    }

    /// The create sales tax method
    ///
    /// @param   salesTax       net.jmp.spring.boot.react.learning.ecommerce.SalesTax
    /// @param   authentication org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @PostMapping("/")
    public ResponseEntity<SalesTaxDocument> createSalesTax(final @RequestBody SalesTax salesTax, final Authentication authentication) {
        return this.logTracer.tracedWith(() ->
            this.userRoleChecker.ifReadWrite(authentication, () -> {
                final SalesTaxDocument document = new SalesTaxDocument();

                this.applySalesTax(document, salesTax);

                final SalesTaxDocument saved = this.salesTaxService.saveSalesTax(document);

                this.logger.info("User {} saved sales tax: {}", authentication.getName(), saved);

                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            }), salesTax, authentication);
    }

    /// The update sales tax method
    ///
    /// @param   documentId     java.lang.String
    /// @param   salesTax       net.jmp.spring.boot.react.learning.ecommerce.SalesTax
    /// @param   authentication org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @PutMapping("/{documentId}")
    public ResponseEntity<SalesTaxDocument> updateSalesTax(final @PathVariable String documentId, final @RequestBody SalesTax salesTax, final Authentication authentication) {
        return this.logTracer.tracedWith(() -> {
            ResponseEntity<SalesTaxDocument> result;

            final Optional<SalesTaxDocument> existing = this.salesTaxService.getSalesTaxByDocumentId(documentId);

            if (existing.isEmpty()) {
                this.logger.warn("Document {} was not found", documentId);

                result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                result = this.userRoleChecker.ifReadWrite(authentication, () -> {
                    final SalesTaxDocument document = new SalesTaxDocument();

                    document.setDocumentId(existing.get().getDocumentId());

                    this.applySalesTax(document, salesTax);

                    final SalesTaxDocument saved = this.salesTaxService.saveSalesTax(document);

                    this.logger.info("User {} updated sales tax: {}", authentication.getName(), saved);

                    return new ResponseEntity<>(saved, HttpStatus.OK);
                });
            }

            return result;
        }, documentId, salesTax, authentication);
    }

    /// The apply sales tax method
    ///
    /// @param   document   net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument
    /// @param   salesTax   net.jmp.spring.boot.react.learning.ecommerce.SalesTax
    private void applySalesTax(final SalesTaxDocument document, final SalesTax salesTax) {
        this.logTracer.tracedWith(() -> {
            document.setState(salesTax.state());
            document.setAbbreviation(salesTax.abbreviation());
            document.setRate(salesTax.rate());
        }, document, salesTax);
    }

    /// The delete sales tax by state name method
    ///
    /// @param   stateName      java.lang.String
    /// @param   authentication org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<java.lang.Void>
    @DeleteMapping("/{stateName}")
    public ResponseEntity<Void> deleteSalesTaxByStateName(final @PathVariable String stateName, final Authentication authentication) {
        return this.logTracer.tracedWith(() ->
            this.performDelete(
                    stateName,
                    authentication,
                    "state name",
                    this.salesTaxService::deleteSalesTaxByStateName
            ), stateName, authentication);
    }

    /// The delete sales tax by state abbreviation method
    ///
    /// @param   stateAbbreviation  java.lang.String
    /// @param   authentication     org.springframework.security.core.Authentication
    /// @return                     org.springframework.http.ResponseEntity<java.lang.Void>
    @DeleteMapping("/abbr/{stateAbbreviation}")
    public ResponseEntity<Void> deleteSalesTaxByStateAbbreviation(final @PathVariable String stateAbbreviation, final Authentication authentication) {
        return this.logTracer.tracedWith(() ->
                this.performDelete(
                        stateAbbreviation,
                        authentication,
                        "state abbreviation",
                        this.salesTaxService::deleteSalesTaxByStateAbbreviation
                ), stateAbbreviation, authentication);
    }

    /// Perform the deletion of a sales tax item
    /// by state name or state abbreviation.
    ///
    /// @param   identifier       java.lang.String
    /// @param   authentication   org.springframework.security.core.Authentication
    /// @param   logLabel         java.lang.String
    /// @param   action           java.util.function.Consumer<java.lang.String>
    /// @return                   org.springframework.http.ResponseEntity<java.lang.Void>
    private ResponseEntity<Void> performDelete(final String identifier,
                                               final Authentication authentication,
                                               final String logLabel,
                                               final Consumer<String> action) {
        return this.logTracer.tracedWith(() ->
            this.userRoleChecker.ifReadWrite(authentication, () -> {
                action.accept(identifier);

                this.logger.info("User {} deleted sales tax by {}: {}", authentication.getName(), logLabel, identifier);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }), identifier, authentication, logLabel);
    }
}
