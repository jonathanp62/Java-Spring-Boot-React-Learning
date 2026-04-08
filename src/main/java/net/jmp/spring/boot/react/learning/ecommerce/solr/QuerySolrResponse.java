package net.jmp.spring.boot.react.learning.ecommerce.solr;

/*
 * (#)QuerySolrResponse.java    0.5.0   03/27/2026
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

import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/// The Solr response for query requests
///
/// @param  <T> The type of the documents in the response
public class QuerySolrResponse<T> extends SolrResponse {
    /// The max score
    private float maxScore;

    /// The number of documents found
    private long numFound;

    /// Where the returned documents started
    private long start;

    /// The list of documents
    @Nullable
    private List<T> documents;

    /// The constructor
    ///
    /// @param  status      int
    /// @param  message     java.lang.String
    public QuerySolrResponse(final int status, final String message) {
        super(status, message);
    }

    /// Get the max score
    ///
    /// @return float
    public float getMaxScore() {
        return this.maxScore;
    }

    /// Set the max score
    ///
    /// @param  maxScore    float
    public void setMaxScore(final float maxScore) {
        this.maxScore = maxScore;
    }

    /// Get the number of documents found
    ///
    /// @return long
    public long getNumFound() {
        return this.numFound;
    }

    /// Set the number of documents found
    ///
    /// @param  numFound    long
    public void setNumFound(final long numFound) {
        this.numFound = numFound;
    }

    /// Get where the returned documents started
    ///
    /// @return long
    public long getStart() {
        return this.start;
    }

    /// Set where the returned documents started
    ///
    /// @param  start   long
    public void setStart(final long start) {
        this.start = start;
    }

    /// Get the list of documents
    ///
    /// @return java.util.List<T>
    public @Nullable List<T> getDocuments() {
        return this.documents;
    }

    /// Set the list of documents
    ///
    /// @param  documents   java.util.List<T>
    public void setDocuments(final List<T> documents) {
        this.documents = documents;
    }

    /// The equals method
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        if (!(o instanceof QuerySolrResponse<?> that)) return false;

        return Float.compare(this.maxScore, that.maxScore) == 0
                && this.numFound == that.numFound
                && this.start == that.start
                && Objects.equals(this.documents, that.documents);
    }

    /// The hash code
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                this.maxScore,
                this.numFound,
                this.start,
                this.documents
        );
    }

    @Override
    public String toString() {
        return "QuerySolrResponse{" +
                super.toString() +
                ", maxScore=" + this.maxScore +
                ", numFound=" + this.numFound +
                ", start=" + this.start +
                ", documents=" + this.documents +
                '}';
    }
}
