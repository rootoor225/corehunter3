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

package org.corehunter.tests.data.simple;

import static org.corehunter.tests.TestData.ALLELES;
import static org.corehunter.tests.TestData.ALLELE_NAMES;
import static org.corehunter.tests.TestData.MARKER_NAMES;
import static org.corehunter.tests.TestData.NAME;
import static org.corehunter.tests.TestData.NAMES;
import static org.corehunter.tests.TestData.SET;

import java.io.IOException;
import java.util.Iterator;

import org.corehunter.data.simple.SimpleGenotypeVariantData;
import org.junit.Test;

import uno.informatics.common.io.FileProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Guy Davenport, Herman De Beukelaer
 */
public class SimpleGenotypeVariantDataTest {

    private static final String TXT_FILE = "/multiallelic.txt";

    @Test
    public void inMemoryTest() {
        testData(new SimpleGenotypeVariantData(NAME, NAMES, MARKER_NAMES, ALLELE_NAMES, ALLELES));
    }

    @Test
    public void loadFromFileTest() {
        try {
            testData(SimpleGenotypeVariantData.readData(new FileProperties(
                    SimpleGenotypeVariantDataTest.class.getResource(TXT_FILE).getFile()
            )));
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private void testData(SimpleGenotypeVariantData data) {
        assertEquals("Ids not correct!", SET, data.getIDs());

        assertEquals("Number of marker is not correct!", MARKER_NAMES.length, data.getNumberOfMarkers());

        int size = data.getIDs().size();

        for (int markerIndex = 0; markerIndex < data.getNumberOfMarkers(); markerIndex++) {
            assertEquals("Marker name for " + markerIndex + " is not correct!",
                         MARKER_NAMES[markerIndex], data.getMarkerName(markerIndex));

            assertEquals("Number of alelles for marker index : " + markerIndex + " is not correct!",
                         ALLELE_NAMES[markerIndex].length, data.getNumberOfAlleles(markerIndex));

            for (int alleleIndex = 0; alleleIndex < data.getNumberOfAlleles(markerIndex); alleleIndex++) {
                assertEquals("Allele name for marker " + markerIndex + " allele " + alleleIndex + " is not correct!",
                             ALLELE_NAMES[markerIndex][alleleIndex], data.getAlleleName(markerIndex, alleleIndex));
            }
        }

        Iterator<Integer> iterator = data.getIDs().iterator();

        int index;
        int i = 0;

        while (iterator.hasNext()) {
            index = iterator.next();

            assertEquals("Name for " + index + " is not correct!", NAMES[i], data.getName(index));

            for (int markerIndex = 0; markerIndex < size; markerIndex++) {
                for (int alleleIndex = 0; alleleIndex < data.getNumberOfAlleles(markerIndex); alleleIndex++) {
                    fail();
                }
            }

            ++i;
        }
    }
}
