package net.jmp.spring.boot.react.learning.ecommerce.repositories;

/*
 * (#)SalesTaxDocumentRepository.java   0.2.0   01/11/2026
 * (#)SalesTaxDocumentRepository.java   0.1.0   12/20/2025
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

import net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/// The sales tax document repository
///
/// @version    0.2.0
/// @since      0.1.0
public interface SalesTaxDocumentRepository extends MongoRepository<SalesTaxDocument, String> {
    /// Get a sales tax document by state name.
    ///
    /// @param  stateName   java.lang.String
    /// @return             java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @Query("{ 'state' :  ?0}")
    Optional<SalesTaxDocument> findByStateName(final String stateName);

    /// Delete a sales tax document by state name.
    ///
    /// @param  stateName   java.lang.String
    @Query(value = "{ 'state' :  ?0}", delete = true)
    void deleteByStateName(final String stateName);

    /// Get a sales tax document by state abbreviation.
    ///
    /// @param  stateAbbreviation   java.lang.String
    /// @return                     java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.SalesTaxDocument>
    @Query("{ 'abbreviation' :  ?0}")
    Optional<SalesTaxDocument> findByStateAbbreviation(final String stateAbbreviation);

    /// Delete a sales tax document by state abbreviation.
    ///
    /// @param  stateAbbreviation   java.lang.String
    @Query(value = "{ 'abbreviation' :  ?0}", delete = true)
    void deleteByStateAbbreviation(final String stateAbbreviation);
}
