package net.jmp.spring.boot.react.learning.bankkycform;

/*
 * (#)FormDocument.java 0.1.0   12/12/2025
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

import java.util.Objects;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/// A MongoDB form class from the bank_kyc_form collection.
///
/// @version    0.1.0
/// @since      0.1.0
@Document(collection = "bank_kyc_form")
public class FormDocument {
    /// The identifier.
    @Id
    private String id;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String fatherName;
    private String grandFatherName;
    private String maritalStatus;
    private String occupation;
    private String emailAddress;
    private String contactNumber;
    private String state;
    private String district;
    private String municipality;
    private String wardNumber;
    private String familyName;
    private String documentType;
    private String citizenshipNumber;
    private String issuedDistrict;
    private String dateOfIssue;
    private FormProfilePicture profilePicture;

    public FormDocument() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGrandFatherName() {
        return this.grandFatherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return this.municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getWardNumber() {
        return this.wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getCitizenshipNumber() {
        return this.citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
    }

    public String getIssuedDistrict() {
        return this.issuedDistrict;
    }

    public void setIssuedDistrict(String issuedDistrict) {
        this.issuedDistrict = issuedDistrict;
    }

    public String getDateOfIssue() {
        return this.dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public FormProfilePicture getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(FormProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FormDocument that)) return false;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.fullName, that.fullName) &&
                Objects.equals(this.gender, that.gender) &&
                Objects.equals(this.dateOfBirth, that.dateOfBirth) &&
                Objects.equals(this.fatherName, that.fatherName) &&
                Objects.equals(this.grandFatherName, that.grandFatherName) &&
                Objects.equals(this.maritalStatus, that.maritalStatus) &&
                Objects.equals(this.occupation, that.occupation) &&
                Objects.equals(this.emailAddress, that.emailAddress) &&
                Objects.equals(this.contactNumber, that.contactNumber) &&
                Objects.equals(this.state, that.state) &&
                Objects.equals(this.district, that.district) &&
                Objects.equals(this.municipality, that.municipality) &&
                Objects.equals(this.wardNumber, that.wardNumber) &&
                Objects.equals(this.familyName, that.familyName) &&
                Objects.equals(this.documentType, that.documentType) &&
                Objects.equals(this.citizenshipNumber, that.citizenshipNumber) &&
                Objects.equals(this.issuedDistrict, that.issuedDistrict) &&
                Objects.equals(this.dateOfIssue, that.dateOfIssue) &&
                Objects.equals(this.profilePicture, that.profilePicture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.fullName,
                this.gender,
                this.dateOfBirth,
                this.fatherName,
                this.grandFatherName,
                this.maritalStatus,
                this.occupation,
                this.emailAddress,
                this.contactNumber,
                this.state,
                this.district,
                this.municipality,
                this.wardNumber,
                this.familyName,
                this.documentType,
                this.citizenshipNumber,
                this.issuedDistrict,
                this.dateOfIssue,
                this.profilePicture
        );
    }

    @Override
    public String toString() {
        return "FormDocument{" +
                "id='" + this.id + '\'' +
                ", fullName='" + this.fullName + '\'' +
                ", gender='" + this.gender + '\'' +
                ", dateOfBirth='" + this.dateOfBirth + '\'' +
                ", fatherName='" + this.fatherName + '\'' +
                ", grandFatherName='" + this.grandFatherName + '\'' +
                ", maritalStatus='" + this.maritalStatus + '\'' +
                ", occupation='" + this.occupation + '\'' +
                ", emailAddress='" + this.emailAddress + '\'' +
                ", contactNumber='" + this.contactNumber + '\'' +
                ", state='" + this.state + '\'' +
                ", district='" + this.district + '\'' +
                ", municipality='" + this.municipality + '\'' +
                ", wardNumber='" + this.wardNumber + '\'' +
                ", familyName='" + this.familyName + '\'' +
                ", documentType='" + this.documentType + '\'' +
                ", citizenshipNumber='" + this.citizenshipNumber + '\'' +
                ", issuedDistrict='" + this.issuedDistrict + '\'' +
                ", dateOfIssue='" + this.dateOfIssue + '\'' +
                ", profilePicture='" + this.profilePicture + '\'' +
                '}';
    }
}
