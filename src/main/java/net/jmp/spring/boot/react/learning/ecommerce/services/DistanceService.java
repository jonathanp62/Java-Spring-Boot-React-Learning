package net.jmp.spring.boot.react.learning.ecommerce.services;

/*
 * (#)DistanceService.java  0.4.0   02/20/2026
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

import net.jmp.spring.boot.react.learning.ecommerce.documents.DistanceDocument;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.repositories.DistanceDocumentRepository;

import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

/// The distance service
@Service
public class DistanceService {
    /// The log tracer
    private final LogTracer logTracer;

    /// The distance document repository
    private final DistanceDocumentRepository distanceDocumentRepository;

    /// The constructor
    ///
    /// @param   distanceDocumentRepository net.jmp.spring.boot.react.learning.ecommerce.repositories.DistanceDocumentRepository
    public DistanceService(final DistanceDocumentRepository distanceDocumentRepository) {
        super();

        this.distanceDocumentRepository = distanceDocumentRepository;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The get distances method
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.DistanceDocument>
    public List<DistanceDocument> getDistances() {
        return this.logTracer.traced(() -> this.distanceDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "toZipCode")));
    }

    /// The get distance by 'to' zip code method
    ///
    /// @param  toZipCode   java.lang.String
    /// @return             java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.DistanceDocument>
    public Optional<DistanceDocument> getDistanceByToZipCode(final String toZipCode) {
        return this.logTracer.tracedWith(() -> this.distanceDocumentRepository.findByToZipCode(toZipCode), toZipCode);
    }
}
