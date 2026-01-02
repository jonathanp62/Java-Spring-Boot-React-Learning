package net.jmp.spring.boot.react.learning.ecommerce.services;

/*
 * (#)SalesTaxService.java  0.1.0   01/02/2026
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
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

import net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument;

import net.jmp.spring.boot.react.learning.ecommerce.repositories.SalesTaxDocumentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import static net.jmp.util.logging.LoggerUtils.*;

/// The sales tax service
@Service
public class SalesTaxService {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The sales tax document repository
    private final SalesTaxDocumentRepository salesTaxDocumentRepository;

    /// The constructor
    ///
    /// @param   salesTaxDocumentRepository net.jmp.spring.boot.react.learning.ecommerce.repositories.SalesTaxDocumentRepository
    public SalesTaxService(final SalesTaxDocumentRepository salesTaxDocumentRepository) {
        super();

        this.salesTaxDocumentRepository = salesTaxDocumentRepository;
    }

    /// The get sales taxes method
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public List<SalesTaxDocument> getSalesTaxes() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<SalesTaxDocument> documents = this.salesTaxDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "state"));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(documents));
        }

        return documents;
    }

    /// The get sales tax by state name method
    ///
    /// @param  stateName   java.lang.String
    /// @return             java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public Optional<SalesTaxDocument> getSalesTaxByStateName(final String stateName) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(stateName));
        }

        final Optional<SalesTaxDocument> document = this.salesTaxDocumentRepository.findByStateName(stateName);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(document));
        }

        return document;
    }

    /// The get sales tax by state abbreviation method
    ///
    /// @param  stateAbbreviation   java.lang.String
    /// @return                     java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public Optional<SalesTaxDocument> getSalesTaxByStateAbbreviation(final String stateAbbreviation) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(stateAbbreviation));
        }

        final Optional<SalesTaxDocument> document = this.salesTaxDocumentRepository.findByStateAbbreviation(stateAbbreviation);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(document));
        }

        return document;
    }
}
