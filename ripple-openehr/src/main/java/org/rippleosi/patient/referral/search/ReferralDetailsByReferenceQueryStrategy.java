package org.rippleosi.patient.referral.search;


import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.common.exception.DataNotFoundException;
import org.rippleosi.common.exception.InvalidDataException;
import org.rippleosi.common.service.strategies.query.details.AbstractDetailsGetQueryStrategy;
import org.rippleosi.patient.referral.model.ReferralDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ReferralDetailsByReferenceQueryStrategy extends AbstractDetailsGetQueryStrategy<ReferralDetails> {

    private String referralRef;

    public ReferralDetailsByReferenceQueryStrategy(String patientId, String referralRef) {
        super(patientId);
        this.referralRef = referralRef;
    }

    @Override
    public String getQuery(String namespace, String patientId) {
        return "select a/composer/name as author, " +
                "a/uid/value as compositionId, " +
                "a/context/start_time/value as date, " +
                "b_a/activities[at0001]/description[at0009]/items[at0121]/value/value as type, " +
                "b_a/activities[at0001]/description[at0009]/items[at0062]/value/value as reason, " +
                "b_a/activities[at0001]/description[at0009]/items[at0064]/value/value as summary, " +
                "b_a/protocol[at0008]/items[openEHR-EHR-CLUSTER.individual_person_uk.v1, " +
                "'Requestor']/items[openEHR-EHR-CLUSTER.person_name.v1]/items[at0001]/value/value as referralFrom, " +
                "b_a/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v1, " +
                "'Receiver']/items[at0001]/value/value as referralTo, " +
                "b_a/protocol[at0008]/items[at0011]/value/value as referral_ref, " +
                "b_d/description[at0001]/items[at0011]/value/value as Service_Service_name, " +
                "b_d/description[at0001]/items[at0028]/value/value as Outcome, " +
                "b_d/time/value as dateOfState, " +
                "b_d/ism_transition/current_state/value as state, " +
                "b_d/ism_transition/current_state/defining_code/code_string as stateCode, " +
                "b_d/ism_transition/careflow_step/value as careflow, " +
                "b_d/ism_transition/careflow_step/defining_code/code_string as careflowCode " +
                "from EHR e contains COMPOSITION a[openEHR-EHR-COMPOSITION.request.v1] " +
                "contains ( INSTRUCTION b_a[openEHR-EHR-INSTRUCTION.request.v0] or " +
                "ACTION b_d[openEHR-EHR-ACTION.service.v0]) " +
                /*
                "where a/name/value='Request for service' and " +
                " (e/ehr_status/subject/external_ref/namespace='" + namespace + "' and " +
                "e/ehr_status/subject/external_ref/id/value='" + patientId + "') and " + */
                " where b_a/protocol[at0008]/items[at0011]/value/value='" + referralRef + "'";
    }

    @Override
    public ReferralDetails transform(List<Map<String, Object>> resultSet) {

        if (resultSet.isEmpty()) {
            throw new DataNotFoundException("No results found");
        }

        Collection<Map<String, Object>> filtered = CollectionUtils.select(resultSet, new ReferralOnlyPredicate());

        if (filtered.size() > 1) {
            throw new InvalidDataException("Too many results found");
        }

        Map<String, Object> data = resultSet.get(0);

        return new ReferralDetailsTransformer().transform(data);

    }
}

