package net.jmp.spring.boot.react.learning.ecommerce.controllers.rest;

/*
 * (#)FakeStoreApiController.java   0.5.0   04/18/2026
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.jmp.spring.boot.react.learning.ecommerce.fakestore.Product;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;
import net.jmp.spring.boot.react.learning.ecommerce.helpers.OptionalToResponseEntityMapper;

import org.slf4j.LoggerFactory;

import org.springframework.core.io.ClassPathResource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/// The fake store API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/fakestore")
public class FakeStoreApiController {
    /// The fake store JSON file name
    private static final String FAKE_STORE_JSON = "fakestore.json";

    /// The log tracer
    private final LogTracer logTracer;

    /// The constructor
    public FakeStoreApiController() {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The OK method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return this.logTracer.traced(() -> new ResponseEntity<>("OK", HttpStatus.OK));
    }

    /// The get all products method
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.learning.ecommerce.fakestore.Product>>
    @GetMapping("/products")
    public ResponseEntity<List<Product>> products() {
        return this.logTracer.traced(() -> {
            final ObjectMapper objectMapper = new ObjectMapper();

            List<Product> products = new ArrayList<>();

            try (final InputStream in = new ClassPathResource(FAKE_STORE_JSON).getInputStream()) {
                products =  objectMapper.readValue(in, new TypeReference<>() {});
            } catch (final Exception e) {
                this.logTracer.getLogger().error("Failed to read fake store JSON", e);

                return new ResponseEntity<>(products, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        });
    }

    /// The get product by ID method
    ///
    /// @param  id  int
    /// @return     org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.fakestore.Product>
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> productById(final @PathVariable String id) {
        return this.logTracer.tracedWith(() -> {
            final ObjectMapper objectMapper = new ObjectMapper();

            List<Product> products;

            try (final InputStream in = new ClassPathResource(FAKE_STORE_JSON).getInputStream()) {
                products =  objectMapper.readValue(in, new TypeReference<>() {});
            } catch (final Exception e) {
                this.logTracer.getLogger().error("Failed to read fake store JSON", e);

                return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            final Product product = products.stream()
                    .filter(p -> p.getId() == Integer.parseInt(id))
                    .findFirst()
                    .orElse(null);

            final Optional<Product> result = Optional.ofNullable(product);

            return OptionalToResponseEntityMapper.map(result);
        }, id);
    }
}
