package net.jmp.spring.boot.react.learning.bankkycform;

/*
 * (#)Form.java 0.1.0   12/11/2025
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
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

/// The form record for POST operations.
///
/// @author    Jonathan Parker
/// @version   0.1.0
/// @since     0.1.0
///
/// @param     fullName               java.lang.String
/// @param     gender                 java.lang.String
/// @param     dateOfBirth            java.lang.String
/// @param     fatherName             java.lang.String
/// @param     grandFatherName        java.lang.String
/// @param     maritalStatus          java.lang.String
/// @param     occupation             java.lang.String
/// @param     emailAddress           java.lang.String
/// @param     contactNumber          java.lang.String
/// @param     state                  java.lang.String
/// @param     district               java.lang.String
/// @param     municipality           java.lang.String
/// @param     wardNumber             java.lang.String
/// @param     familyName             java.lang.String
/// @param     documentType           java.lang.String
/// @param     citizenshipNumber      java.lang.String
/// @param     issuedDistrict         java.lang.String
/// @param     dateOfIssue            java.lang.String
/// @param     profilePicture         net.jmp.spring.boot.react.learning.bankkycform.FormProfilePicture
public record Form(
        String fullName,
        String gender,
        String dateOfBirth,
        String fatherName,
        String grandFatherName,
        String maritalStatus,
        String occupation,
        String emailAddress,
        String contactNumber,
        String state,
        String district,
        String municipality,
        String wardNumber,
        String familyName,
        String documentType,
        String citizenshipNumber,
        String issuedDistrict,
        String dateOfIssue,
        FormProfilePicture profilePicture
) {
}
