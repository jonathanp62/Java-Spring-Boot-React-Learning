package net.jmp.spring.boot.react.learning.ecommerce.controllers;

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

import java.util.function.Supplier;

import net.jmp.spring.boot.react.learning.ecommerce.SalesTax;

import net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument;

import net.jmp.spring.boot.react.learning.ecommerce.services.SalesTaxService;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

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

    /// The constructor
    ///
    /// @param   salesTaxService net.jmp.spring.boot.react.learning.ecommerce.services.SalesTaxService
    public SalesTaxApiController(final SalesTaxService salesTaxService) {
        super();

        this.salesTaxService = salesTaxService;
        this.logTracer = new LogTracer(this.logger);
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
        return this.logTracer.tracedWith(() -> {
            final Optional<SalesTaxDocument> document = this.salesTaxService.getSalesTaxByStateName(stateName);

            return document
                    .map(found -> new ResponseEntity<>(found, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }, stateName);
    }

    /// The get sales tax document by state abbreviation method
    ///
    /// @param  stateAbbreviation   java.lang.String
    /// @return                     org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @GetMapping("/abbr/{stateAbbreviation}")
    public ResponseEntity<SalesTaxDocument> salesTaxByStateAbbreviation(final @PathVariable String stateAbbreviation) {
        return this.logTracer.tracedWith(() -> {
            final Optional<SalesTaxDocument> document = this.salesTaxService.getSalesTaxByStateAbbreviation(stateAbbreviation);

            return document
                    .map(found -> new ResponseEntity<>(found, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }, stateAbbreviation);
    }

    /// The create sales tax method
    ///
    /// @param   salesTax       net.jmp.spring.boot.react.learning.ecommerce.SalesTax
    /// @param   authentication org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @PostMapping("/")
    public ResponseEntity<SalesTaxDocument> createSalesTax(final @RequestBody SalesTax salesTax, final Authentication authentication) {
        return this.logTracer.tracedWith(() -> {
            ResponseEntity<SalesTaxDocument> result;

            final List<String> userRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

            if (userRoles.contains("ROLE_READWRITE")) {
                final SalesTaxDocument document = new SalesTaxDocument();

                document.setState(salesTax.state());
                document.setAbbreviation(salesTax.abbreviation());
                document.setRate(salesTax.rate());

                final SalesTaxDocument saved = this.salesTaxService.saveSalesTax(document);

                this.logger.info("User {} saved sales tax: {}", authentication.getName(), saved);

                result = new ResponseEntity<>(saved, HttpStatus.CREATED);
            } else {
                this.logger.warn("User {} does not have the READWRITE role", authentication.getName());

                result = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            return result;
        }, salesTax, authentication);
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
                final List<String> userRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

                if (userRoles.contains("ROLE_READWRITE")) {
                    final SalesTaxDocument document = new SalesTaxDocument();

                    document.setDocumentId(existing.get().getDocumentId());
                    document.setState(salesTax.state());
                    document.setAbbreviation(salesTax.abbreviation());
                    document.setRate(salesTax.rate());

                    final SalesTaxDocument saved = this.salesTaxService.saveSalesTax(document);

                    this.logger.info("User {} updated sales tax: {}", authentication.getName(), saved);

                    result = new ResponseEntity<>(saved, HttpStatus.OK);
                } else {
                    this.logger.warn("User {} does not have the READWRITE role", authentication.getName());

                    result = new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }

            return result;
        }, documentId, salesTax, authentication);
    }

    /// The delete sales tax by state name method
    ///
    /// @param   stateName      java.lang.String
    /// @param   authentication org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<java.lang.Void>
    @DeleteMapping("/{stateName}")
    public ResponseEntity<Void> deleteSalesTaxByStateName(final @PathVariable String stateName, final Authentication authentication) {
        return this.logTracer.tracedWith(() ->
                this.ifReadWrite(authentication, () -> {
                    this.salesTaxService.deleteSalesTaxByStateName(stateName);

                    this.logger.info("User {} deleted sales tax by state name: {}", authentication.getName(), stateName);

                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }), stateName, authentication);
    }

    /// The delete sales tax by state abbreviation method
    ///
    /// @param   stateAbbreviation  java.lang.String
    /// @param   authentication     org.springframework.security.core.Authentication
    /// @return                     org.springframework.http.ResponseEntity<java.lang.Void>
    @DeleteMapping("/abbr/{stateAbbreviation}")
    public ResponseEntity<Void> deleteSalesTaxByStateAbbreviation(final @PathVariable String stateAbbreviation, final Authentication authentication) {
        return this.logTracer.tracedWith(() ->
            this.ifReadWrite(authentication, () -> {
                this.salesTaxService.deleteSalesTaxByStateAbbreviation(stateAbbreviation);

                this.logger.info("User {} deleted sales tax by state abbreviation: {}", authentication.getName(), stateAbbreviation);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }), stateAbbreviation, authentication);
    }

    /// The method that runs the allowed supplier
    /// when the user has the READWRITE role.
    ///
    /// @param   <T>            The return type of the allowed supplier
    /// @param   authentication org.springframework.security.core.Authentication
    /// @param   allowed        java.util.function.Supplier<org.springframework.http.ResponseEntity<T>>
    /// @return                 org.springframework.http.ResponseEntity<T>
    private <T> ResponseEntity<T> ifReadWrite(final Authentication authentication, final Supplier<ResponseEntity<T>> allowed) {
        final List<String> userRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        if (userRoles.contains("ROLE_READWRITE")) {
            return allowed.get();
        } else {
            this.logger.warn("User {} does not have the READWRITE role", authentication.getName());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
