package net.jmp.spring.boot.react.learning.ecommerce.controllers.rest;

/*
 * (#)SearchApiController.java  0.6.0   05/20/2026
 * (#)SearchApiController.java  0.5.0   03/27/2026
 * (#)SearchApiController.java  0.4.0   03/21/2026
 *
 * @author    Jonathan Parker
 * @version   0.6.0
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

import net.jmp.spring.boot.react.learning.ecommerce.SolrProduct;
import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.services.SearchService;

import net.jmp.spring.boot.react.learning.ecommerce.solr.FacetsSolrResponse;
import net.jmp.spring.boot.react.learning.ecommerce.solr.PingSolrResponse;
import net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse;
import net.jmp.spring.boot.react.learning.ecommerce.solr.TermsSolrResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.MessageSource;

import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/// The search API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/search")
public class SearchApiController {
    /// The log tracer
    private final LogTracer logTracer;

    /// The search service
    private final SearchService searchService;

    /// The message source
    final MessageSource messageSource;

    /// The constructor
    ///
    /// @param  searchService  net.jmp.spring.boot.react.learning.ecommerce.services.SearchService
    /// @param  messageSource  org.springframework.context.MessageSource
    public SearchApiController(final SearchService searchService, final MessageSource messageSource) {
        this.searchService = searchService;
        this.messageSource = messageSource;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The OK method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return this.logTracer.traced(() -> {
            final String ok = this.messageSource.getMessage("j.ok", null, LocaleContextHolder.getLocale());

            return new ResponseEntity<>(ok, HttpStatus.OK);
        });
    }

    /// The ping method
    ///
    /// @param  collection  java.lang.String
    /// @return             org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/{collection}/ping")
    public ResponseEntity<String> ping(final @PathVariable String collection) {
        return this.logTracer.tracedWith(() -> {
            final PingSolrResponse response = this.searchService.ping(collection);

            return switch (response.getStatus()) {
                case 200 -> new ResponseEntity<>(
                        this.messageSource.getMessage("j.solr.ping.coll.ok", new Object[] { collection }, LocaleContextHolder.getLocale()),
                        HttpStatus.OK
                );
                case 404 -> new ResponseEntity<>(
                        response.getMessage(),
                        HttpStatus.NOT_FOUND
                );
                case 500 -> new ResponseEntity<>(
                        response.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
                default -> new ResponseEntity<>(
                        this.messageSource.getMessage("j.solr.ping.coll.failed", new Object[] { collection }, LocaleContextHolder.getLocale()),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            };
        }, collection);
    }

    /// The facets method
    ///
    /// @param  collection  java.lang.String
    /// @return             org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/{collection}/facets")
    public ResponseEntity<FacetsSolrResponse> facets(final @PathVariable String collection) {
        return this.logTracer.tracedWith(() -> {
            final FacetsSolrResponse response = this.searchService.facets(collection);

            return switch (response.getStatus()) {
                case 200 -> new ResponseEntity<>(
                        response,
                        HttpStatus.OK
                );
                case 404 -> new ResponseEntity<>(
                        new FacetsSolrResponse(404, response.getMessage()),
                        HttpStatus.NOT_FOUND
                );
                case 500 -> new ResponseEntity<>(
                        new FacetsSolrResponse(500, response.getMessage()),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
                default -> new ResponseEntity<>(
                        new FacetsSolrResponse(500, this.messageSource.getMessage("j.solr.ping.facets.failed", new Object[] { collection }, LocaleContextHolder.getLocale())),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            };
        }, collection);
    }

    /// The terms method
    ///
    /// @param  collection  java.lang.String
    /// @param  field       java.lang.String
    /// @return             org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/{collection}/terms")
    public ResponseEntity<TermsSolrResponse> terms(final @PathVariable String collection, final @RequestParam String field) {
        return this.logTracer.tracedWith(() -> {
            final TermsSolrResponse response = this.searchService.terms(collection, field);

            return switch (response.getStatus()) {
                case 200 -> new ResponseEntity<>(
                        response,
                        HttpStatus.OK
                );
                case 404 -> new ResponseEntity<>(
                        new TermsSolrResponse(404, response.getMessage()),
                        HttpStatus.NOT_FOUND
                );
                case 500 -> new ResponseEntity<>(
                        new TermsSolrResponse(500, response.getMessage()),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
                default -> new ResponseEntity<>(
                        new TermsSolrResponse(500, this.messageSource.getMessage("j.solr.ping.terms.failed", new Object[] { collection, field }, LocaleContextHolder.getLocale())),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            };
        }, collection, field);
    }

    /// The all-purpose select from ecommerce-products method
    ///
    /// @return org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    @GetMapping("/ecommerce-products/select")
    public ResponseEntity<List<SolrProduct>> select(@RequestParam final Map<String, String> requestParameters) {
        return this.logTracer.traced(() -> {
            final Logger logger = this.logTracer.getLogger();
            final String collectionName = "ecommerce-products";

            QuerySolrResponse<SolrProduct> response;

            if (requestParameters.isEmpty()) {
                response = this.searchService.selectAll(collectionName);
            } else {
                if (requestParameters.containsKey("field")) {
                    final String fieldName = requestParameters.get("field");
                    final String fieldValue = requestParameters.getOrDefault("value", "");
                    final String fieldMin = requestParameters.getOrDefault("min", "");
                    final String fieldMax = requestParameters.getOrDefault("max", "");
                    final String category = requestParameters.getOrDefault("category", "");

                    response = switch (fieldName.toLowerCase(Locale.getDefault())) {
                        case "category" -> this.searchService.selectByCategory(collectionName, fieldValue);
                        case "description,title", "title,description" -> this.searchService.selectByDescriptionAndTitle(collectionName, fieldValue, category);
                        case "description" -> this.searchService.selectByDescription(collectionName, fieldValue, category);
                        case "price" -> this.searchService.selectByPrice(collectionName, fieldMin, fieldMax, category);
                        case "productid" -> this.searchService.selectByProductId(collectionName, fieldValue);
                        case "ratingcount" -> this.searchService.selectByRatingCount(collectionName, fieldMin, fieldMax, category);
                        case "ratingrate" -> this.searchService.selectByRatingRate(collectionName, fieldMin, fieldMax, category);
                        case "title" -> this.searchService.selectByTitle(collectionName, fieldValue, category);
                        default -> new QuerySolrResponse<>(400, this.messageSource.getMessage("j.solr.unrecognized.field", new Object[] { fieldName }, LocaleContextHolder.getLocale()));
                    };
                } else {
                    response = new QuerySolrResponse<>(400, this.messageSource.getMessage("j.solr.unrecognized.request", new Object[] { requestParameters }, LocaleContextHolder.getLocale()));
                }
            }

            return switch (response.getStatus()) {
                case 200 -> new ResponseEntity<>(
                        response.getDocuments(),
                        HttpStatus.OK
                );
                case 400 -> {
                    logger.error(response.getMessage());

                    yield new ResponseEntity<>(
                            new ArrayList<>(),
                            HttpStatus.BAD_REQUEST
                    );
                }
                case 404 -> {
                    logger.error(response.getMessage());

                    yield new ResponseEntity<>(
                        new ArrayList<>(),
                        HttpStatus.NOT_FOUND
                    );
                }
                default -> {
                    logger.error(response.getMessage());

                    yield new ResponseEntity<>(
                        new ArrayList<>(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            };
        });
    }

    /// The select by ID from ecommerce-products method
    ///
    /// @param  id  java.lang.String
    /// @return     org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    @GetMapping("/ecommerce-products/select/{id}")
    public ResponseEntity<SolrProduct> selectById(final @PathVariable String id) {
        return this.logTracer.tracedWith(() -> {
            final Logger logger = this.logTracer.getLogger();
            final QuerySolrResponse<SolrProduct> response = this.searchService.selectById("ecommerce-products", id);

            return switch (response.getStatus()) {
                case 200 -> new ResponseEntity<>(
                        response.getDocuments() != null ? response.getDocuments().getFirst() : new SolrProduct(),
                        HttpStatus.OK
                    );
                case 404 -> {
                    logger.warn(response.getMessage());

                    yield new ResponseEntity<>(
                            new SolrProduct(),
                            HttpStatus.NOT_FOUND
                    );
                }
                default -> {
                    logger.error(response.getMessage());

                    yield new ResponseEntity<>(
                            new SolrProduct(),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            };
        }, id);
    }
}
