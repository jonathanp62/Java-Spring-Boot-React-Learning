package net.jmp.spring.boot.react.learning.ecommerce.controllers.rest;

/*
 * (#)SearchApiController.java  0.5.0   03/27/2026
 * (#)SearchApiController.java  0.4.0   03/21/2026
 *
 * @author    Jonathan Parker
 * @version   0.5.0
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

import net.jmp.spring.boot.react.learning.ecommerce.solr.PingSolrResponse;
import net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/// The search API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/search")
public class SearchApiController {
    /// The log tracer
    private final LogTracer logTracer;

    /// The search service
    private final SearchService searchService;

    /// The constructor
    ///
    /// @param  searchService  net.jmp.spring.boot.react.learning.ecommerce.services.SearchService
    public SearchApiController(final SearchService searchService) {
        this.searchService = searchService;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The OK method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return this.logTracer.traced(() -> new ResponseEntity<>("OK", HttpStatus.OK));
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
                        String.format("Pinged Solr collection %s OK", collection),
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
                        String.format("Failed to ping Solr collection %s", collection),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            };
        }, collection);
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
                    final String category = requestParameters.getOrDefault("category", null);

                    response = switch (fieldName.toLowerCase()) {
                        case "description,title", "title,description" -> this.searchService.selectByDescriptionAndTitle(collectionName, fieldValue, category);
                        case "description" -> this.searchService.selectByDescription(collectionName, fieldValue, category);
                        case "productid" -> this.searchService.selectByProductId(collectionName, fieldValue);
                        case "title" -> this.searchService.selectByTitle(collectionName, fieldValue, category);
                        default -> new QuerySolrResponse<>(400, String.format("Unrecognized field name: %s", fieldName));
                    };
                } else {
                    response = new QuerySolrResponse<>(400, String.format("Unrecognized request parameters: %s", requestParameters));
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
                        response.getDocuments().getFirst(),
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
