package net.jmp.spring.boot.react.learning.bankkycform;

/*
 * (#)FormController.java   0.1.0   12/10/2025
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

import java.util.List;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

/// The person controller.
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/react/learning/api/bank-kyc-form")
public class FormController {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The repository for the form documents.
    private final FormDocumentRepository repository;

    /// The default constructor.
    ///
    /// @param  repository    net.jmp.spring.boot.react.learning.bankkycform.FormDocumentRepository
    public FormController(final FormDocumentRepository repository) {
        super();

        this.repository = repository;
    }

    /// The OK method.
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final ResponseEntity<String> result = new ResponseEntity<>("OK", HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get all method.
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.learning.bankkycform.FormDocument>>
    @GetMapping
    public ResponseEntity<List<FormDocument>> getAll() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<FormDocument> documents = this.repository.findAll();
        final ResponseEntity<List<FormDocument>> result = new ResponseEntity<>(documents, HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The save method.
    ///
    /// @param  form    net.jmp.spring.boot.react.learning.bankkycform.Form
    /// @return         org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.bankkycform.Form>
    @PostMapping
    public ResponseEntity<Form> save(final @RequestBody Form form) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(form));
        }

        final FormDocument document = new FormDocument();

        document.setFullName(form.fullName());
        document.setGender(form.gender());
        document.setDateOfBirth(form.dateOfBirth());
        document.setFatherName(form.fatherName());
        document.setGrandFatherName(form.grandFatherName());
        document.setMaritalStatus(form.maritalStatus());
        document.setOccupation(form.occupation());
        document.setEmailAddress(form.emailAddress());
        document.setContactNumber(form.contactNumber());
        document.setState(form.state());
        document.setDistrict(form.district());
        document.setMunicipality(form.municipality());
        document.setWardNumber(form.wardNumber());
        document.setFamilyName(form.familyName());
        document.setDocumentType(form.documentType());
        document.setCitizenshipNumber(form.citizenshipNumber());
        document.setIssuedDistrict(form.issuedDistrict());
        document.setDateOfIssue(form.dateOfIssue());
        document.setProfilePicture(form.profilePicture());

        final FormDocument saved = this.repository.save(document);

        this.logger.info("Saved form document: {}", saved);

        final ResponseEntity<Form> result = new ResponseEntity<>(form, HttpStatus.CREATED);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }
}
