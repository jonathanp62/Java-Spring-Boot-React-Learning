package net.jmp.spring.boot.react.learning.ecommerce.solr;

/*
 * (#)SolrSearchConfiguration.java  0.5.0   05/09/2026
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

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// The Solr search configuration that associates faceted fields with collections
@Component
@ConfigurationProperties(prefix = "solr.search")
public class SolrSearchConfiguration {
    // The collections
    private List<CollectionConfiguration> collections = new ArrayList<>();

    /// Return the collections
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.solr.SolrSearchConfiguration.CollectionConfiguration>
    public List<CollectionConfiguration> getCollections() { return this.collections; }

    /// Set the collections
    ///
    /// @param  collections java.util.List<net.jmp.spring.boot.react.learning.ecommerce.solr.SolrSearchConfiguration.CollectionConfiguration>
    public void setCollections(List<CollectionConfiguration> collections) { this.collections = collections; }

    /// Return the string representation of the object
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "SolrSearchConfiguration [collections=" + this.collections + "]";
    }

    /// The collection configuration
    public static class CollectionConfiguration {
        /// The name
        private String name = "";

        /// The faceted fields
        private List<String> facets = new ArrayList<>();

        /// Return the name
        ///
        /// @return java.lang.String
        public String getName() { return this.name; }

        /// Set the name
        ///
        /// @param  name    java.lang.String
        public void setName(String name) { this.name = name; }

        /// Get the faceted fields
        ///
        /// @return java.util.List<java.lang.String>
        public List<String> getFacets() { return this.facets; }

        /// Set the faceted fields
        ///
        /// @param  facets  java.util.List<java.lang.String>
        public void setFacets(List<String> facets) { this.facets = facets; }

        /// Return the string representation of the object
        ///
        /// @return java.lang.String
        @Override
        public String toString() {
            return "CollectionConfiguration [name=" + this.name + ", facets=" + this.facets + "]";
        }
    }
}
