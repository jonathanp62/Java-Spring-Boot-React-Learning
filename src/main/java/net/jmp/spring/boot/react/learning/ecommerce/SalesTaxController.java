package net.jmp.spring.boot.react.learning.ecommerce;

/*
 * (#)SalesTaxController.java   0.1.0   12/20/2025
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
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

import static net.jmp.util.logging.LoggerUtils.entry;
import static net.jmp.util.logging.LoggerUtils.exitWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/// The sales tax controller
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/react/learning/api/e-commerce/sales-tax")
public class SalesTaxController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The sales tax document repository
    private final SalesTaxDocumentRepository salesTaxDocumentRepository;

    /// The constructor
    ///
    /// @param   salesTaxDocumentRepository net.jmp.spring.boot.react.learning.ecommerce.SalesTaxDocumentRepository
    public SalesTaxController(final SalesTaxDocumentRepository salesTaxDocumentRepository) {
        super();

        this.salesTaxDocumentRepository = salesTaxDocumentRepository;
    }

    /// The OK method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ResponseEntity<String> result = new ResponseEntity<>("OK", HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get all sales tax documents method
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.learning.ecommerce.OrderDocument>>
    @GetMapping("/")
    public ResponseEntity<List<SalesTaxDocument>> salesTaxes() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<SalesTaxDocument> documents = this.salesTaxDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "state"));
        final ResponseEntity<List<SalesTaxDocument>> result = new ResponseEntity<>(documents, HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get sales tax document by state name method
    ///
    /// @return org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.SalesTaxDocument>
    @GetMapping("/{stateName}")
    public ResponseEntity<SalesTaxDocument> salesTaxByStateName(final @PathVariable String stateName) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Optional<SalesTaxDocument> document = this.salesTaxDocumentRepository.findByStateName(stateName);

        final ResponseEntity<SalesTaxDocument> result = document
                .map(found -> new ResponseEntity<>(found, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }
}
