package net.jmp.spring.boot.react.learning.ecommerce.services;

/*
 * (#)SearchService.java    0.5.0   03/21/2026
 * (#)SearchService.java    0.4.0   03/21/2026
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

import java.util.List;

import java.util.function.Supplier;

import net.jmp.spring.boot.react.learning.ecommerce.SolrProduct;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.solr.PingSolrResponse;
import net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse;

import org.apache.solr.client.solrj.impl.HttpJdkSolrClient;

import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.request.SolrQuery;

import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;

import org.apache.solr.client.solrj.util.ClientUtils;

import org.apache.solr.common.params.CommonParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

/// The search service
@Service
public class SearchService {
    /// The Solr start number
    @Value("${solr.start:0}")
    private int solrStart;

    /// The Solr rows number
    @Value("${solr.rows:100}")
    private int solrRows;

    /// The log tracer
    private final LogTracer logTracer;

    /// The Solr client
    private final HttpJdkSolrClient solrClient;

    /// The constructor
    ///
    /// @param  solrClient  org.apache.solr.client.solrj.impl.HttpJdkSolrClient
    public SearchService(final HttpJdkSolrClient solrClient) {
        this.solrClient = solrClient;
        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The ping method
    ///
    /// @param  collection  java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.PingSolrResponse
    public PingSolrResponse ping(final String collection) {
        return this.logTracer.tracedWith(() -> {
            PingSolrResponse pingSolrResponse;

            if (this.isSolrCollectionValid(collection)) {
                try {
                    final SolrPingResponse response = this.solrClient.ping(collection);

                    if (response.getStatus() == 0) {
                        pingSolrResponse = new PingSolrResponse(200, "OK");

                        pingSolrResponse.setElapsedTime(response.getElapsedTime());
                        pingSolrResponse.setQTime(response.getQTime());
                    } else {
                        pingSolrResponse = new PingSolrResponse(500, "Not OK");
                    }
                } catch (final Exception e) {
                    final Logger logger = this.logTracer.getLogger();
                    final String message = String.format("Failed to ping Solr: %s", e.getMessage());

                    logger.error(message);

                    pingSolrResponse = new PingSolrResponse(500, message);
                }
            } else {
                pingSolrResponse = new PingSolrResponse(404, String.format("Solr collection %s was not found", collection));
            }

            return pingSolrResponse;
        }, collection);
    }

    /// The select all method
    ///
    /// @param  collection  java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectAll(final String collection) {
        return this.logTracer.tracedWith(() -> {
            final SolrQuery query = new SolrQuery("*:*");

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);
            query.setSort("product_id", SolrQuery.ORDER.asc);

            return this.querySolr(collection, query, () -> "");
        }, collection);
    }

    /// The select by ID method
    ///
    /// @param  collection  java.lang.String
    /// @param  id          java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectById(final String collection, final String id) {
        return this.logTracer.tracedWith(() -> this.querySolr(
                collection,
                new SolrQuery(String.format("id:%s", id)),
                () -> String.format("ID %s was not found", id)
        ), collection, id);
    }

    /// The select by product ID method
    ///
    /// @param  collection  java.lang.String
    /// @param  productId   java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByProductId(final String collection, final String productId) {
        return this.logTracer.tracedWith(() -> this.querySolr(
                collection,
                new SolrQuery(String.format("product_id:%s", productId)),
                () -> String.format("Product ID %s was not found", productId)
        ), collection, productId);
    }

    /// The select by description method
    ///
    /// @param  collection  java.lang.String
    /// @param  term        java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByDescription(final String collection, final String term, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);
            query.setQuery(term);
            query.setParam(CommonParams.DF, "description");
            query.setSort("product_id", SolrQuery.ORDER.asc);

            if (!category.isEmpty()) {
                notFoundMessage = () -> String.format("No products returned with term '%s' in the description for category '%s'", term, category);

                this.addCategoryFacet(query, category);
            } else {
                notFoundMessage = () -> String.format("No products returned with term '%s' in the description", term);
            }

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, term, category);
    }

    /// The select by title method
    ///
    /// @param  collection  java.lang.String
    /// @param  term        java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByTitle(final String collection, final String term, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);
            query.setQuery(term);
            query.setParam(CommonParams.DF, "title");
            query.setSort("product_id", SolrQuery.ORDER.asc);

            if (!category.isEmpty()) {
                notFoundMessage = () -> String.format("No products returned with term '%s' in the title for category '%s'", term, category);

                this.addCategoryFacet(query, category);
            } else {
                notFoundMessage = () -> String.format("No products returned with term '%s' in the title", term);
            }

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, term, category);
    }

    /// The select by description and title method
    ///
    /// @param  collection  java.lang.String
    /// @param  term        java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByDescriptionAndTitle(final String collection, final String term, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);
            query.setQuery(term);
            query.setParam(CommonParams.DF, "description", "title");
            query.setSort("product_id", SolrQuery.ORDER.asc);

            if (!category.isEmpty()) {
                notFoundMessage = () -> String.format("No products returned with term '%s' in either the description or title for category '%s'", term, category);

                this.addCategoryFacet(query, category);
            } else {
                notFoundMessage = () -> String.format("No products returned with term '%s' in either the description or title", term);
            }

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, term, category);
    }

    /// The select by price method
    ///
    /// @param  collection  java.lang.String
    /// @param  min         java.lang.String
    /// @param  max         java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByPrice(final String collection, final String min, final String max, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);

            if (max.isEmpty()) {
                query.setQuery(String.format("price:[%s TO *]", min));
            } else {
                query.setQuery(String.format("price:[%s TO %s]", min, max));
            }

            query.setSort("price", SolrQuery.ORDER.asc);

            if (!category.isEmpty()) {
                if (max.isEmpty()) {
                    notFoundMessage = () -> String.format("No products returned with price '%s' or more for category '%s'", min, category);
                } else {
                    notFoundMessage = () -> String.format("No products returned in price range '%s' to '%s' for category '%s'", min, max, category);
                }

                this.addCategoryFacet(query, category);
            } else {
                if (max.isEmpty()) {
                    notFoundMessage = () -> String.format("No products returned with price '%s' or more", min);
                } else {
                    notFoundMessage = () -> String.format("No products returned in price range '%s' to '%s'", min, max);
                }
            }

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, min, max, category);
    }

    /// The select by rating count method
    ///
    /// @param  collection  java.lang.String
    /// @param  min         java.lang.String
    /// @param  max         java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByRatingCount(final String collection, final String min, final String max, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);

            if (max.isEmpty()) {
                query.setQuery(String.format("rating_count:[%s TO *]", min));
            } else {
                query.setQuery(String.format("rating_count:[%s TO %s]", min, max));
            }

            query.setSort("rating_count", SolrQuery.ORDER.asc);

            if (!category.isEmpty()) {
                if (max.isEmpty()) {
                    notFoundMessage = () -> String.format("No products returned with rating count '%s' or more for category '%s'", min, category);
                } else {
                    notFoundMessage = () -> String.format("No products returned in rating count range '%s' to '%s' for category '%s'", min, max, category);
                }

                this.addCategoryFacet(query, category);
            } else {
                if (max.isEmpty()) {
                    notFoundMessage = () -> String.format("No products returned with rating count '%s' or more", min);
                } else {
                    notFoundMessage = () -> String.format("No products returned in rating count range '%s' to '%s'", min, max);
                }
            }

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, min, max, category);
    }

    /// The select by rating rate method
    ///
    /// @param  collection  java.lang.String
    /// @param  min         java.lang.String
    /// @param  max         java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByRatingRate(final String collection, final String min, final String max, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);

            if (max.isEmpty()) {
                query.setQuery(String.format("rating_rate:[%s TO *]", min));
            } else {
                query.setQuery(String.format("rating_rate:[%s TO %s]", min, max));
            }

            query.setSort("rating_rate", SolrQuery.ORDER.asc);

            if (!category.isEmpty()) {
                if (max.isEmpty()) {
                    notFoundMessage = () -> String.format("No products returned with rating rate '%s' or more for category '%s'", min, category);
                } else {
                    notFoundMessage = () -> String.format("No products returned in rating rate range '%s' to '%s' for category '%s'", min, max, category);
                }

                this.addCategoryFacet(query, category);
            } else {
                if (max.isEmpty()) {
                    notFoundMessage = () -> String.format("No products returned with rating rate '%s' or more", min);
                } else {
                    notFoundMessage = () -> String.format("No products returned in rating rate range '%s' to '%s'", min, max);
                }
            }

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, min, max, category);
    }

    /// The select by category method
    ///
    /// @param  collection  java.lang.String
    /// @param  category    java.lang.String
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    public QuerySolrResponse<SolrProduct> selectByCategory(final String collection, final String category) {
        return this.logTracer.tracedWith(() -> {
            Supplier<String> notFoundMessage;

            final SolrQuery query = new SolrQuery();

            query.setRows(this.solrRows);
            query.setStart(this.solrStart);
            query.setQuery("*:*");
            query.addFilterQuery(String.format("category:%s", ClientUtils.escapeQueryChars(category)));
            query.setSort("product_id", SolrQuery.ORDER.asc);

            notFoundMessage = () -> String.format("No products returned with category '%s'", category);

            return this.querySolr(
                    collection,
                    query,
                    notFoundMessage
            );
        }, collection, category);
    }

    /// The validate Solr collection method
    ///
    /// @param  collection  java.lang.String
    /// @return             boolean
    private boolean isSolrCollectionValid(final String collection) {
        return this.logTracer.tracedWith(() -> {
            try {
                final CollectionAdminRequest.List listRequest = new CollectionAdminRequest.List();
                final CollectionAdminResponse response = listRequest.process(this.solrClient);

                @SuppressWarnings("unchecked")
                final List<String> collections = (List<String>) response.getResponse().get("collections");
                
                return collections.contains(collection);
            } catch (final Exception e) {
                final Logger logger = this.logTracer.getLogger();

                logger.error("Failed to get Solr collections", e);

                return false;
            }
        }, collection);
    }

    /// The query Solr method
    ///
    /// @param  collection      java.lang.String
    /// @param  query           org.apache.solr.client.solrj.request.SolrQuery
    /// @param  notFoundMessage java.util.function.Supplier<java.lang.String>
    /// @return                 net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    private QuerySolrResponse<SolrProduct> querySolr(final String collection, final SolrQuery query, final Supplier<String> notFoundMessage) {
        return this.logTracer.tracedWith(() -> {
            QuerySolrResponse<SolrProduct> querySolrResponse;

            if (this.isSolrCollectionValid(collection)) {
                final Logger logger = this.logTracer.getLogger();

                try {
                    final QueryResponse response = this.solrClient.query(collection, query);

                    this.logQueryResponse(response, logger);

                    if (response.getStatus() == 0) {
                        final List<SolrProduct> products = response.getBeans(SolrProduct.class);

                        if (products.isEmpty()) {
                            querySolrResponse = new QuerySolrResponse<>(404, notFoundMessage.get());
                        } else {
                            querySolrResponse = this.createOkQuerySolrResponse(response, products);
                        }
                    } else {
                        querySolrResponse = new QuerySolrResponse<>(500, "Not OK");
                    }
                } catch (final Exception e) {
                    final String message = String.format("Failed to select from Solr: %s", e.getMessage());

                    logger.error(message);

                    querySolrResponse = new QuerySolrResponse<>(500, message);
                }
            } else {
                querySolrResponse = new QuerySolrResponse<>(404, String.format("Solr collection %s was not found", collection));
            }

            return querySolrResponse;
        }, collection, query);
    }

    /// The log query response method
    ///
    /// @param  queryResponse  org.apache.solr.client.solrj.response.QueryResponse
    /// @param  logger         org.slf4j.Logger
    private void logQueryResponse(final QueryResponse queryResponse, final Logger logger) {
        if (logger.isDebugEnabled()) {
            logger.debug("QueryResponse: {}", queryResponse);

            logger.debug("Results size  : {}", queryResponse.getResults() != null ? queryResponse.getResults().size() : 0); // Returns 20
            logger.debug("NumFound      : {}", queryResponse.getResults() != null ? queryResponse.getResults().getNumFound() : 0);  // Returns 20
            logger.debug("NumFound Exact: {}", queryResponse.getResults() != null ? queryResponse.getResults().getNumFoundExact() : 0);
            logger.debug("Start         : {}", queryResponse.getResults() != null ? queryResponse.getResults().getStart() : 0);
        }
    }

    /// The create OK QuerySolrRresponse method
    ///
    /// @param  response    org.apache.solr.client.solrj.response.QueryResponse
    /// @param  documents   java.util.List<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    /// @return             net.jmp.spring.boot.react.learning.ecommerce.solr.QuerySolrResponse<net.jmp.spring.boot.react.learning.ecommerce.SolrProduct>
    private QuerySolrResponse<SolrProduct> createOkQuerySolrResponse(final QueryResponse response, final List<SolrProduct> documents) {
        return this.logTracer.tracedWith(() -> {
            final QuerySolrResponse<SolrProduct> querySolrResponse = new QuerySolrResponse<>(200, "OK");

            querySolrResponse.setQTime(response.getQTime());
            querySolrResponse.setElapsedTime(response.getElapsedTime());
            querySolrResponse.setDocuments(documents);
            querySolrResponse.setNumFound(response.getResults().getNumFound());
            querySolrResponse.setStart(response.getResults().getStart());
            querySolrResponse.setMaxScore(response.getResults().getMaxScore() != null ? response.getResults().getMaxScore() : 0);

            return querySolrResponse;
        }, response);
    }

    /// The add category facet method
    ///
    /// @param  query     org.apache.solr.client.solrj.request.SolrQuery
    /// @param  category  java.lang.String
    private void addCategoryFacet(final SolrQuery query, final String category) {
        this.logTracer.tracedWith(() -> {
            query.setFacet(true);
            query.addFacetField("category");
            query.addFilterQuery(String.format("category:%s", ClientUtils.escapeQueryChars(category)));
            query.setFacetMinCount(1);
        }, query, category);
    }
}
