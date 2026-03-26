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

/// The product as stored in Solr
///
/// @param  id          java.lang.String
/// @param  productId   int
/// @param  title       java.lang.String
/// @param  price       double
/// @param  description java.lang.String
/// @param  category    java.lang.String
/// @param  image       java.lang.String
/// @param  ratingRate  double
/// @param  ratingCount int
public record SolrProduct(
    String id,
    @Field("product_id")
    int productId,
    String title,
    double price,
    String description,
    String category,
    String image,
    @Field("rating_rate")
    double ratingRate,
    @Field("rating_count")
    int ratingCount
) {}
