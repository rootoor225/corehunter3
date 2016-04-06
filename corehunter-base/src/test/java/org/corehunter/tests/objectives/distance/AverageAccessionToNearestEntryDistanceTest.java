/*--------------------------------------------------------------*/
/* Licensed to the Apache Software Foundation (ASF) under one   */
/* or more contributor license agreements.  See the NOTICE file */
/* distributed with this work for additional information        */
/* regarding copyright ownership.  The ASF licenses this file   */
/* to you under the Apache License, Version 2.0 (the            */
/* "License"); you may not use this file except in compliance   */
/* with the License.  You may obtain a copy of the License at   */
/*                                                              */
/*   http://www.apache.org/licenses/LICENSE-2.0                 */
/*                                                              */
/* Unless required by applicable law or agreed to in writing,   */
/* software distributed under the License is distributed on an  */
/* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       */
/* KIND, either express or implied.  See the License for the    */
/* specific language governing permissions and limitations      */
/* under the License.                                           */
/*--------------------------------------------------------------*/

package org.corehunter.tests.objectives.distance;

import org.corehunter.data.DistanceMatrixData;
import org.corehunter.data.CoreHunterData;
import org.corehunter.data.simple.SimpleDistanceMatrixData;
import org.corehunter.data.simple.SimpleGenotypeVariantData;
import org.corehunter.objectives.distance.AverageAccessionToNearestEntryDistance;
import org.corehunter.objectives.distance.measures.CavalliSforzaEdwardsDistance;
import org.corehunter.objectives.distance.measures.GowerDistance;
import org.corehunter.objectives.distance.measures.ModifiedRogersDistance;
import org.corehunter.objectives.distance.measures.PrecomputedDistance;

import static org.corehunter.tests.TestData.ACCESSION_TO_NEAREST_ENTRY_CAVALLI_SFORZA_SUBSET2;
import static org.corehunter.tests.TestData.ACCESSION_TO_NEAREST_ENTRY_CAVALLI_SFORZA_SUBSET3;
import static org.corehunter.tests.TestData.ACCESSION_TO_NEAREST_ENTRY_GOWER_SUBSET2;
import static org.corehunter.tests.TestData.ACCESSION_TO_NEAREST_ENTRY_GOWER_SUBSET3;
import static org.corehunter.tests.TestData.ACCESSION_TO_NEAREST_ENTRY_MODIFIED_ROGERS_SUBSET2;
import static org.corehunter.tests.TestData.ACCESSION_TO_NEAREST_ENTRY_MODIFIED_ROGERS_SUBSET3;
import static org.corehunter.tests.TestData.ALLELE_FREQUENCIES;
import static org.corehunter.tests.TestData.ALLELE_NAMES;
import static org.corehunter.tests.TestData.HEADERS_NON_UNIQUE_NAMES;
import static org.corehunter.tests.TestData.HEADERS_UNIQUE_NAMES;
import static org.corehunter.tests.TestData.MARKER_NAMES;
import static org.corehunter.tests.TestData.MODIFIED_ROGERS_DISTANCES;
import static org.corehunter.tests.TestData.NAME;
import static org.corehunter.tests.TestData.PHENOTYPIC_TRAIT_FEATURES;
import static org.corehunter.tests.TestData.PHENOTYPIC_TRAIT_VALUES_WITH_HEADERS;
import static org.corehunter.tests.TestData.PRECISION;
import static org.corehunter.tests.TestData.SUBSET2;
import static org.corehunter.tests.TestData.SUBSET3;
import static org.corehunter.tests.TestData.SUBSET_EMPTY;
import static org.corehunter.tests.TestData.SUBSET_FULL;

import org.corehunter.tests.objectives.EvaluationTest;
import org.jamesframework.core.problems.objectives.evaluations.SimpleEvaluation;

import org.jamesframework.core.subset.SubsetSolution;
import org.junit.Test;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.feature.array.ArrayFeatureData;

/**
 * @author Herman De Beukelaer
 */
public class AverageAccessionToNearestEntryDistanceTest extends EvaluationTest {

    @Test
    public void testModifiedRogers() {
        
        SimpleGenotypeVariantData geno = new SimpleGenotypeVariantData(
                NAME, HEADERS_NON_UNIQUE_NAMES, MARKER_NAMES, ALLELE_NAMES, ALLELE_FREQUENCIES
        );
        CoreHunterData data = new CoreHunterData(geno);
        
        AverageAccessionToNearestEntryDistance objective = new AverageAccessionToNearestEntryDistance(
                new ModifiedRogersDistance()
        );

        assertEquals(
                "Evaluation for subset 2 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_MODIFIED_ROGERS_SUBSET2,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET2), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for subset 3 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_MODIFIED_ROGERS_SUBSET3,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET3), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for full subset is not correct!",
                SimpleEvaluation.WITH_VALUE(0.0),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_FULL), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for empty subset is not correct!",
                SimpleEvaluation.WITH_VALUE(Double.POSITIVE_INFINITY),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_EMPTY), data),
                PRECISION
        );
        
    }
    
    @Test
    public void testCavalliSforzaEdwards() {
        
        SimpleGenotypeVariantData geno = new SimpleGenotypeVariantData(
                NAME, HEADERS_NON_UNIQUE_NAMES, MARKER_NAMES, ALLELE_NAMES, ALLELE_FREQUENCIES
        );
        CoreHunterData data = new CoreHunterData(geno);
        
        AverageAccessionToNearestEntryDistance objective = new AverageAccessionToNearestEntryDistance(
                new CavalliSforzaEdwardsDistance()
        );

        assertEquals(
                "Evaluation for subset 2 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_CAVALLI_SFORZA_SUBSET2,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET2), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for subset 3 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_CAVALLI_SFORZA_SUBSET3,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET3), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for full subset is not correct!",
                SimpleEvaluation.WITH_VALUE(0.0),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_FULL), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for empty subset is not correct!",
                SimpleEvaluation.WITH_VALUE(Double.POSITIVE_INFINITY),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_EMPTY), data),
                PRECISION
        );
        
    }
    
    @Test
    public void testGower() {
        
        FeatureData pheno = new ArrayFeatureData(
                NAME, PHENOTYPIC_TRAIT_FEATURES, PHENOTYPIC_TRAIT_VALUES_WITH_HEADERS
        );
        CoreHunterData data = new CoreHunterData(pheno);
        
        AverageAccessionToNearestEntryDistance objective = new AverageAccessionToNearestEntryDistance(
                new GowerDistance()
        );

        assertEquals(
                "Evaluation for subset 2 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_GOWER_SUBSET2,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET2), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for subset 3 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_GOWER_SUBSET3,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET3), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for full subset is not correct!",
                SimpleEvaluation.WITH_VALUE(0.0),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_FULL), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for empty subset is not correct!",
                SimpleEvaluation.WITH_VALUE(Double.POSITIVE_INFINITY),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_EMPTY), data),
                PRECISION
        );
        
    }
    
    @Test
    public void testPrecomputedModifiedRogers() {

        DistanceMatrixData dist = new SimpleDistanceMatrixData(HEADERS_UNIQUE_NAMES, MODIFIED_ROGERS_DISTANCES);
        CoreHunterData data = new CoreHunterData(dist);
        
        AverageAccessionToNearestEntryDistance objective = new AverageAccessionToNearestEntryDistance(
                new PrecomputedDistance()
        );

        assertEquals(
                "Evaluation for subset 2 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_MODIFIED_ROGERS_SUBSET2,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET2), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for subset 3 is not correct!",
                ACCESSION_TO_NEAREST_ENTRY_MODIFIED_ROGERS_SUBSET3,
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET3), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for full subset is not correct!",
                SimpleEvaluation.WITH_VALUE(0.0),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_FULL), data),
                PRECISION
        );
        
        assertEquals(
                "Evaluation for empty subset is not correct!",
                SimpleEvaluation.WITH_VALUE(Double.POSITIVE_INFINITY),
                objective.evaluate(new SubsetSolution(data.getIDs(), SUBSET_EMPTY), data),
                PRECISION
        );
        
    }

}
