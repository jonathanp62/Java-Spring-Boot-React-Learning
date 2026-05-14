package net.jmp.spring.boot.react.learning.components;

/*
 * (#)ApplicationStartupChecker.java    0.5.0   05/14/2026
 *
 * @author    Jonathan Parker
 * @version   0.5.0
 * @since     0.5.0
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

import org.apache.solr.client.solrj.impl.HttpJdkSolrClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

/// The application startup checker
@Component
public class ApplicationStartupChecker implements CommandLineRunner {
    /// The Solr client
    final HttpJdkSolrClient solrClient;

    /// The logger
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The constructor
    ///
    /// @param  solrClient  org.apache.solr.client.solrj.impl.HttpJdkSolrClient
    public ApplicationStartupChecker(final HttpJdkSolrClient solrClient) {
        super();

        this.solrClient = solrClient;
    }

    /// The run method
    ///
    /// @param  args  java.lang.String[]
    /// @throws       java.lang.Exception   When an exception occurs
    @Override
    public void run(String... args) throws Exception {
        try {
            final int status = this.solrClient.ping("products").getStatus();

            if (status == 0) {
                this.logger.info("Connected to Solr at: {}", this.solrClient.getBaseURL());
            } else {
                throw new IllegalStateException(String.format("Solr ping failed with status: %d", status));
            }
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Error connecting to Solr at: %s", this.solrClient.getBaseURL()), e);
        }
    }
}
