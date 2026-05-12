package net.jmp.spring.boot.react.learning.ecommerce.solr;

/*
 * (#)TermsSolrResponse.java    0.5.0   05/12/2026
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

import java.util.ArrayList;
import java.util.List;

/// The Solr response for terms requests
public class TermsSolrResponse extends SolrResponse {
    /// The term fields
    private List<TermField> termFields = new ArrayList<>();

    /// The constructor
    ///
    /// @param  status  int
    /// @param  message java.lang.String
    public TermsSolrResponse(final int status, final String message) {
        super(status, message);
    }

    /// Get the term fields
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.solr.TermsSolrResponse.TermField>
    public List<TermField> getTermFields() {
        return this.termFields;
    }

    /// Set the term fields
    ///
    /// @param  termFields  java.util.List<net.jmp.spring.boot.react.learning.ecommerce.solr.TermsSolrResponse.TermField>
    public void setTermFields(final List<TermField> termFields) {
        this.termFields = termFields;
    }

    /// The term field class
    public static class TermField {
        /// The name
        private String name = "";

        /// The frequency
        private List<TermValue> values = new ArrayList<>();

        /// The default constructor
        public TermField() {
            super();
        }

        /// Get the name
        ///
        /// @return java.lang.String
        public String getName() {
            return this.name;
        }

        /// Set the name
        ///
        /// @param  name  java.lang.String
        public void setName(final String name) {
            this.name = name;
        }

        /// Get the values
        ///
        /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.solr.TermsSolrResponse.TermValue>
        public List<TermValue> getValues() {
            return this.values;
        }

        /// Set the values
        ///
        /// @param  values  java.util.List<net.jmp.spring.boot.react.learning.ecommerce.solr.TermsSolrResponse.TermValue>
        public void setValues(final List<TermValue> values) {
            this.values = values;
        }
    }

    /// The term value class
    public static class TermValue {
        /// The name
        private String name = "";

        /// The frequency
        private int frequency = 0;

        /// The default constructor
        public TermValue() {
            super();
        }

        /// Get the name
        ///
        /// @return java.lang.String
        public String getName() {
            return this.name;
        }

        /// Set the name
        ///
        /// @param  name  java.lang.String
        public void setName(final String name) {
            this.name = name;
        }

        /// Get the frequency
        ///
        /// @return int
        public int getFrequency() {
            return this.frequency;
        }

        /// Set the frequency
        ///
        /// @param  frequency   int
        public void setFrequency(final int frequency) {
            this.frequency = frequency;
        }
    }
}
