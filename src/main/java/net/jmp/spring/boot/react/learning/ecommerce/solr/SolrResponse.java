package net.jmp.spring.boot.react.learning.ecommerce.solr;

/*
 * (#)SolrResponse.java 0.5.0   03/27/2026
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

import java.util.Objects;

/// This SolrResponse class is a base class for all Solr responses
public class SolrResponse {
    /// The elapsed time
    private final long elapsedTime;

    /// The query time
    private final int  qTime;

    /// The status code
    private final int status;

    /// The default constructor
    private SolrResponse() {
        super();

        this.elapsedTime = 0;
        this.qTime = 0;
        this.status = 0;
    }

    /// The constructor
    ///
    /// @param  elapsedTime long
    /// @param  qTime       int
    /// @param  status      int
    protected SolrResponse(final long elapsedTime, final int qTime, final int status) {
        super();

        this.elapsedTime = elapsedTime;
        this.qTime = qTime;
        this.status = status;
    }

    /// Get the elapsed time
    ///
    /// @return long
    public long getElapsedTime() {
        return this.elapsedTime;
    }

    /// Get the query time
    ///
    /// @return int
    public int getqTime() {
        return this.qTime;
    }

    /// Get the status code
    ///
    /// @return int
    public int getStatus() {
        return this.status;
    }

    /// The equals method
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        final SolrResponse that = (SolrResponse) o;

        return this.elapsedTime == that.elapsedTime
                && this.qTime == that.qTime
                && this.status == that.status;
    }

    /// The hash code
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(this.elapsedTime, this.qTime, this.status);
    }

    /// The string representation
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "SolrResponse{" +
                "elapsedTime=" + this.elapsedTime +
                ", qTime=" + this.qTime +
                ", status=" + this.status +
                '}';
    }
}
