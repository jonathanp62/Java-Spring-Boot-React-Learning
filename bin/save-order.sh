#!/usr/bin/env zsh

# (#)save-order.sh  0.1.0   12/14/2025
#
# @author   Jonathan Parker
# @version  0.1.0
# @since    0.1.0
#
# MIT License
#
# Copyright (c) 2025 Jonathan M. Parker
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

SITE="http://localhost:8080"

UUID=$(uuidgen)
LOWER_UUID=$(echo "$UUID" | tr '[:upper:]' '[:lower:]')
NOW=$(date -u +"%Y-%m-%dT%H:%M:%S%z")

curl \
  -i \
  -u admin:admin123 \
  -X POST "${SITE}/react/learning/api/e-commerce/order" \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -d @- <<EOF
{
  "orderId": "${LOWER_UUID}",
  "orderDate": "${NOW}",
  "firstName": "Jane",
  "lastName": "Doe",
  "address": "123 Main Street",
  "city": "Anytown",
  "state": "CA",
  "zipCode": "12345",
  "country": "USA",
  "phone": "123-456-7890",
  "email": "jane.doe@example.com",
  "products": [
    {
      "id": 101,
      "title": "The title of the first product",
      "price": 99.99,
      "description": "The first description",
      "category": "The first category",
      "type": "clothes",
      "target": "women",
      "image": "image1.jpg",
      "rating": {
        "rate": 4.2,
        "count": 10
      }
    },
    {
      "id": 102,
      "title": "The title of the second product",
      "price": 109.99,
      "description": "The second description",
      "category": "The second category",
      "type": "electronics",
      "target": "unisex",
      "image": "image2.jpg",
      "rating": {
        "rate": 3.7,
        "count": 18
      }
    }
  ]
}
EOF
