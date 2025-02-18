/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.core.javarcc.primitives;

import org.roboticsapi.runtime.core.types.RPIdouble;
import org.roboticsapi.runtime.javarcc.JInPort;
import org.roboticsapi.runtime.javarcc.JOutPort;
import org.roboticsapi.runtime.javarcc.JParameter;
import org.roboticsapi.runtime.javarcc.JPrimitive;

public class JTimeHistory extends JPrimitive {
	JInPort<Long> inValue = add("inValue", new JInPort<Long>());
	JInPort<Long> inTime = add("inTime", new JInPort<Long>());
	JOutPort<RPIdouble> outAge = add("outAge", new JOutPort<RPIdouble>());
	JParameter<RPIdouble> propMaxAge = add("MaxAge", new JParameter<RPIdouble>());
	Long[] history;
	int count, current;

	@Override
	public void checkParameters() throws IllegalArgumentException {
		connected(inValue, inTime);
		count = (int) (propMaxAge.get().get() / getNet().getCycleTime());
		current = 0;
		if (count < 1)
			throw new IllegalArgumentException("MaxAge");
		history = new Long[count];
	}

	@Override
	public void updateData() {
		if (anyNull(inTime))
			return;
		history[current % count] = inValue.get();
		for (int i = current; i > current - count && i >= 0; i--) {
			if (history[i % count] != null && history[i % count] <= inTime.get()) {
				outAge.set(new RPIdouble((current - i) * getNet().getCycleTime()));
				break;
			}
		}
		current++;
	}

}
