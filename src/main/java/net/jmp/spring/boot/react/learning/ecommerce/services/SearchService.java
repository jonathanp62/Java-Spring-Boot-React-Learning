package net.jmp.spring.boot.react.learning.ecommerce.services;

/*
 * (#)SearchService.java    0.4.0   03/21/2026
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

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import org.apache.solr.client.solrj.impl.HttpJdkSolrClient;

import org.apache.solr.client.solrj.request.CollectionAdminRequest;

import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/// The search service
@Service
public class SearchService {
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
    /// @return             int
    public int ping(final String collection) {
        return this.logTracer.tracedWith(() -> {
            int status;

            if (this.isSolrCollectionValid(collection)) {
                try {
                    final SolrPingResponse response = this.solrClient.ping(collection);

                    status = response.getStatus();
                } catch (final Exception e) {
                    final Logger logger = this.logTracer.getLogger();

                    logger.error("Failed to ping Solr: {}", e.getMessage());

                    status = 500;
                }
            } else {
                status = 404;
            }

            return status;
        }, collection);
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

                logger.error("Failed to get Solr collections: {}", e.getMessage());

                return false;
            }
        }, collection);
    }
}
