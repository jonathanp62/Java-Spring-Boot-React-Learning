#!/usr/bin/env zsh

# (#)update-sakes-tax.sh  0.2.0   01/12/2026
#
# @author   Jonathan Parker
# @version  0.2.0
# @since    0.2.0
#
# MIT License
#
# Copyright (c) 2026 Jonathan M. Parker
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

if [ "$#" -ne 3 ]
then
        echo "Usage: $0 <state-name> <state-abbreviation> <tax-rate>"
        exit 1
fi

STATE_NAME=${1}
STATE_ABBREVIATION=${2}
TAX_RATE=${3}

curl \
  -i \
  -u admin:admin123 \
  -X PUT "${SITE}/react/learning/api/e-commerce/sales-tax/" \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -d @- <<EOF
{
  "state": "${STATE_NAME}",
  "abbreviation": "${STATE_ABBREVIATION}",
  "rate": ${TAX_RATE}
}
EOF
