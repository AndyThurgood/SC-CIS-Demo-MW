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
package org.rippleosi.patient.problems.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.problems.model.ProblemDetails;

/**
 */
public class ProblemDetailsTransformer implements Transformer<Map<String, Object>, ProblemDetails> {

    @Override
    public ProblemDetails transform(Map<String, Object> input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfOnset = null;
        try {
            dateOfOnset = sdf.parse(MapUtils.getString(input, "onsetDate"));
        }
        catch   (ParseException pe) { }

        ProblemDetails problem = new ProblemDetails();
        problem.setSource("Marand");
        problem.setSourceId(MapUtils.getString(input, "compositionID"));
        problem.setProblem(MapUtils.getString(input, "problem"));
        problem.setDateOfOnset(dateOfOnset);
        problem.setCode(MapUtils.getString(input, "problemCode"));
        problem.setTerminology(MapUtils.getString(input, "problemTerminology"));
        problem.setDescription(MapUtils.getString(input, "description"));
        problem.setAuthor(MapUtils.getString(input, "author"));

        String dateCreated = MapUtils.getString(input, "dateCreated");
        problem.setDateCreated(DateFormatter.toDate(dateCreated));

        return problem;
    }
}
