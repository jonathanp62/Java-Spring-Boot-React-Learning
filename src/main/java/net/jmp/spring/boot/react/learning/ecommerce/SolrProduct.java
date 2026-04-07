package net.jmp.spring.boot.react.learning.ecommerce;

/*
 * (#)SolrProduct.java  0.5.0   03/26/2026
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

import org.apache.solr.client.solrj.beans.Field;

import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/// The product as stored in Solr
public class SolrProduct {
    /// The identifier
    @SuppressWarnings("NullAway.Init")
    @Field
    private String id;

    /// The product identifier
    @Nullable
    @Field("product_id")
    private Integer productId;

    /// The title
    @Nullable
    @Field
    private List<@Nullable String> title;

    /// The price
    @Field
    private double price;

    /// The description
    @Nullable
    @Field
    private List<@Nullable String> description;

    /// The category
    @Nullable
    @Field
    private String category;

    /// The link to the image
    @Nullable
    @Field
    private String image;

    /// The rating rate
    @Nullable
    @Field("rating_rate")
    private Double ratingRate;

    /// The rating count
    @Nullable
    @Field("rating_count")
    private Integer ratingCount;

    /// The default constructor
    public SolrProduct() {
        super();
    }

    /// Get the identifier
    ///
    /// @return java.lang.String
    public String getId() {
        return this.id;
    }

    /// Set the identifier
    ///
    /// @param  id  java.lang.String
    public void setId(final String id) {
        this.id = id;
    }

    /// Get the product identifier
    ///
    /// @return java.lang.Integer
    public @Nullable Integer getProductId() {
        return this.productId;
    }

    /// Set the product identifier
    ///
    /// @param  productId  java.lang.Integer
    public void setProductId(final Integer productId) {
        this.productId = productId;
    }

    /// Get the title
    ///
    /// @return java.util.List<java.lang.String>
    public @Nullable List<@Nullable String> getTitle() {
        return this.title;
    }

    /// Set the title
    ///
    /// @param  title  java.util.List<java.lang.String>
    public void setTitle(final List<String> title) {
        this.title = title;
    }

    /// Get the price
    ///
    /// @return double
    public double getPrice() {
        return this.price;
    }

    /// Set the price
    ///
    /// @param  price  double
    public void setPrice(final double price) {
        this.price = price;
    }

    /// Get the description
    ///
    /// @return java.util.List<java.lang.String>
    public @Nullable List<@Nullable String> getDescription() {
        return this.description;
    }

    /// Set the description
    ///
    /// @param  description  java.util.List<java.lang.String>
    public void setDescription(final List<String> description) {
        this.description = description;
    }

    /// Get the category
    ///
    /// @return java.lang.String
    public @Nullable String getCategory() {
        return this.category;
    }

    /// Set the category
    ///
    /// @param  category  java.lang.String
    public void setCategory(final String category) {
        this.category = category;
    }

    /// Get the link to the image
    ///
    /// @return java.lang.String
    public @Nullable String getImage() {
        return this.image;
    }

    /// Set the link to the image
    ///
    /// @param  image  java.lang.String
    public void setImage(final String image) {
        this.image = image;
    }

    /// Get the rating rate
    ///
    /// @return java.lang.Double
    public @Nullable Double getRatingRate() {
        return this.ratingRate;
    }

    /// Set the rating rate
    ///
    /// @param  ratingRate  java.lang.Double
    public void setRatingRate(final Double ratingRate) {
        this.ratingRate = ratingRate;
    }

    /// Get the rating count
    ///
    /// @return java.lang.Integer
    public @Nullable Integer getRatingCount() {
        return this.ratingCount;
    }

    /// Set the rating count
    ///
    /// @param  ratingCount  java.lang.Integer
    public void setRatingCount(final Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    /// The equals method
    ///
    /// @param  o  java.lang.Object
    /// @return    boolean
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SolrProduct that)) return false;;

        return Double.compare(
                this.price, that.price) == 0
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.productId, that.productId)
                && Objects.equals(this.title, that.title)
                && Objects.equals(this.description, that.description)
                && Objects.equals(this.category, that.category)
                && Objects.equals(this.image, that.image)
                && Objects.equals(this.ratingRate, that.ratingRate)
                && Objects.equals(this.ratingCount, that.ratingCount
        );
    }

    /// The hash code method
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.productId,
                this.title,
                this.price,
                this.description,
                this.category,
                this.image,
                this.ratingRate,
                this.ratingCount
        );
    }

    /// The to string method
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "SolrProduct{" +
                "id='" + this.id + '\'' +
                ", productId=" + this.productId +
                ", title=" + this.title +
                ", price=" + this.price +
                ", description=" + this.description +
                ", category='" + this.category + '\'' +
                ", image=" + this.image +
                ", ratingRate=" + this.ratingRate +
                ", ratingCount=" + this.ratingCount +
                '}';
    }
}
