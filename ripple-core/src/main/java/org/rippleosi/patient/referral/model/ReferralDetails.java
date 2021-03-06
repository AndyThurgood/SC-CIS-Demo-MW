/*
 * Copyright 2015 Ripple OSI
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.rippleosi.patient.referral.model;

import org.rippleosi.common.util.UUIDGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class ReferralDetails implements Serializable {

    private String sourceId;
    private String source;
    private Date dateOfReferral;
    private String referralFrom;
    private String referralTo;
    private String reason;
    private String clinicalSummary;
    private String author;
    private Date dateCreated;
    private String reference;
    private String referralState;
    private String referralType;
    private String referralOutcome;
    private Date dateResponded;
    private String responseSourceId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(Date dateOfReferral) {
        this.dateOfReferral = dateOfReferral;
    }

    public String getReferralFrom() {
        return referralFrom;
    }

    public void setReferralFrom(String referralFrom) {
        this.referralFrom = referralFrom;
    }

    public String getReferralTo() {
        return referralTo;
    }

    public void setReferralTo(String referralTo) {
        this.referralTo = referralTo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getClinicalSummary() {
        return clinicalSummary;
    }

    public void setClinicalSummary(String clinicalSummary) {
        this.clinicalSummary = clinicalSummary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public String getReference() { return reference; }

    public void setReference(String reference) { this.reference = reference; }

    public String getReferralState() { return referralState; }

    public void setReferralState(String referralState) { this.referralState = referralState; }

    public String getReferralType() { return referralType; }

    public void setReferralType(String referralType) { this.referralType = referralType; }

    public String getReferralOutcome() { return referralOutcome; }

    public void setReferralOutcome(String referralOutcome) { this.referralOutcome = referralOutcome; }

    public Date getDateResponded() { return dateResponded; }

    public void setDateResponded(Date dateResponded) { this.dateResponded = dateResponded; }

    public void generateReference()
    {
        this.setReference(UUIDGenerator.generate());
    }

    public String getResponseSourceId() { return responseSourceId; }

    public void setResponseSourceId(String responseSourceId) { this.responseSourceId = responseSourceId; }
}
