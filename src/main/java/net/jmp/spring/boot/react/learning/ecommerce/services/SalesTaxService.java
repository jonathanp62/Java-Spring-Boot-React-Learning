package net.jmp.spring.boot.react.learning.ecommerce.services;

/*
 * (#)SalesTaxService.java  0.2.0   01/11/2026
 * (#)SalesTaxService.java  0.1.0   01/02/2026
 *
 * @author    Jonathan Parker
 * @version   0.2.0
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

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.repositories.SalesTaxDocumentRepository;

import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

/// The sales tax service
@Service
public class SalesTaxService {
    /// The log tracer
    private final LogTracer logTracer;

    /// The sales tax document repository
    private final SalesTaxDocumentRepository salesTaxDocumentRepository;

    /// The constructor
    ///
    /// @param   salesTaxDocumentRepository net.jmp.spring.boot.react.learning.ecommerce.repositories.SalesTaxDocumentRepository
    public SalesTaxService(final SalesTaxDocumentRepository salesTaxDocumentRepository) {
        super();

        this.salesTaxDocumentRepository = salesTaxDocumentRepository;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The get sales taxes method
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public List<SalesTaxDocument> getSalesTaxes() {
        return this.logTracer.traced(() -> this.salesTaxDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "state")));
    }

    /// The get sales tax by document identifier
    ///
    /// @param  documentId  java.lang.String
    /// @return             java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public Optional<SalesTaxDocument> getSalesTaxByDocumentId(final String documentId) {
        return this.logTracer.tracedWith(() -> this.salesTaxDocumentRepository.findById(documentId), documentId);
    }

    /// The get sales tax by state name method
    ///
    /// @param  stateName   java.lang.String
    /// @return             java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public Optional<SalesTaxDocument> getSalesTaxByStateName(final String stateName) {
        return this.logTracer.tracedWith(() -> this.salesTaxDocumentRepository.findByStateName(stateName), stateName);
    }

    /// The get sales tax by state abbreviation method
    ///
    /// @param  stateAbbreviation   java.lang.String
    /// @return                     java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    public Optional<SalesTaxDocument> getSalesTaxByStateAbbreviation(final String stateAbbreviation) {
        return this.logTracer.tracedWith(() -> this.salesTaxDocumentRepository.findByStateAbbreviation(stateAbbreviation), stateAbbreviation);
    }

    /// The save sales tax method
    ///
    /// @param  salesTaxDocument  net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument
    /// @return                   net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument
    public SalesTaxDocument saveSalesTax(final SalesTaxDocument salesTaxDocument) {
        return this.logTracer.tracedWith(() -> this.salesTaxDocumentRepository.save(salesTaxDocument), salesTaxDocument);
    }

    /// The delete sales tax by state name method
    ///
    /// @param  stateName   java.lang.String
    public void deleteSalesTaxByStateName(final String stateName) {
        this.logTracer.tracedWith(() -> this.salesTaxDocumentRepository.deleteByStateName(stateName), stateName);
    }

    /// The delete sales tax by state abbreviation method
    ///
    /// @param  stateAbbreviation   java.lang.String
    public void deleteSalesTaxByStateAbbreviation(final String stateAbbreviation) {
        this.logTracer.tracedWith(() -> this.salesTaxDocumentRepository.deleteByStateAbbreviation(stateAbbreviation), stateAbbreviation);
    }
}
