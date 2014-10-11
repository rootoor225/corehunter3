/*******************************************************************************
 * Copyright Herman De Beukelaer, 2014 Guy Davenport Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *******************************************************************************/
package org.corehunter.simple;

import java.util.Set;

import org.corehunter.data.DistanceMatrixData;

/**
 * @author Guy Davenport
 */
public class SimpleDistanceMatrixData implements DistanceMatrixData
{
	private double[][]	       distances;
	
	private final Set<Integer>	ids;

	public SimpleDistanceMatrixData(Set<Integer> ids, double[][] distances)
	{
		this.ids = ids;
		this.distances = distances;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jamesframework.core.problems.datatypes.SubsetData#getIDs()
	 */
	@Override
	public Set<Integer> getIDs()
	{
		return ids;
	}

	/*
	 * (non-Javadoc)
	 * @see org.corehunter.DistanceMatrixData#getDistance(int, int)
	 */
	@Override
	public double getDistance(int index1, int index2)
	{
		return distances[index1][index2];
	}
}