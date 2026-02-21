package net.jmp.spring.boot.react.learning.ecommerce.documents;

/*
 * (#)DistanceDocument.java 0.4.0   02/20/2026
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

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/// The distance document that is stored in the database.
@Document(collection = "distance")
public class DistanceDocument {
    /// The Mongo identifier
    @Id
    private String documentId;

    /// The 'from' zip code
    private String fromZipCode;

    /// The 'to' zip code
    private String toZipCode;

    /// The distance in miles
    private double distanceInMiles;

    /// The distance in kilometers
    private double distanceInKilometers;

    /// The default constructor
    public DistanceDocument() {
        super();
    }

    /// Get the document identifier
    ///
    /// @return java.lang.String
    public String getDocumentId() {
        return this.documentId;
    }

    /// Set the document identifier
    ///
    /// @param  documentId  java.lang.String
    public void setDocumentId(final String documentId) {
        this.documentId = documentId;
    }

    /// Get the 'from' zip code
    ///
    /// @return java.lang.String
    public String getFromZipCode() {
        return this.fromZipCode;
    }

    /// Set the 'from' zip code
    ///
    /// @param  fromZipCode  java.lang.String
    public void setFromZipCode(final String fromZipCode) {
        this.fromZipCode = fromZipCode;
    }

    /// Get the 'to' zip code
    ///
    /// @return java.lang.String
    public String getToZipCode() {
        return this.toZipCode;
    }

    /// Set the 'to' zip code
    ///
    /// @param  toZipCode  java.lang.String
    public void setToZipCode(final String toZipCode) {
        this.toZipCode = toZipCode;
    }

    /// Get the distance in miles
    ///
    /// @return double
    public double getDistanceInMiles() {
        return this.distanceInMiles;
    }

    /// Set the distance in miles
    ///
    /// @param  distanceInMiles  double
    public void setDistanceInMiles(final double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }

    /// Get the distance in kilometers
    ///
    /// @return double
    public double getDistanceInKilometers() {
        return this.distanceInKilometers;
    }

    /// Set the distance in kilometers
    ///
    /// @param  distanceInKilometers  double
    public void setDistanceInKilometers(final double distanceInKilometers) {
        this.distanceInKilometers = distanceInKilometers;
    }

    /// The equals method
    ///
    /// @param  o  java.lang.Object
    /// @return    boolean
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        final DistanceDocument that = (DistanceDocument) o;

        return Double.compare(this.distanceInMiles, that.distanceInMiles) == 0
                && Double.compare(this.distanceInKilometers, that.distanceInKilometers) == 0
                && Objects.equals(this.documentId, that.documentId)
                && Objects.equals(this.fromZipCode, that.fromZipCode)
                && Objects.equals(this.toZipCode, that.toZipCode);
    }

    /// The hash code method
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(
                this.documentId,
                this.fromZipCode,
                this.toZipCode,
                this.distanceInMiles,
                this.distanceInKilometers
        );
    }

    /// The to string method
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "DistanceDocument{" +
                "documentId='" + this.documentId + '\'' +
                ", fromZipCode='" + this.fromZipCode + '\'' +
                ", toZipCode='" + this.toZipCode + '\'' +
                ", distanceInMiles=" + this.distanceInMiles +
                ", distanceInKilometers=" + this.distanceInKilometers +
                '}';
    }
}
