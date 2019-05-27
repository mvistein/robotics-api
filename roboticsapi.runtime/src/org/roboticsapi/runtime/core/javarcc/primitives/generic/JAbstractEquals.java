/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.core.javarcc.primitives.generic;

import org.roboticsapi.runtime.core.types.RPIbool;
import org.roboticsapi.runtime.javarcc.JInPort;
import org.roboticsapi.runtime.javarcc.JOutPort;
import org.roboticsapi.runtime.javarcc.JParameter;
import org.roboticsapi.runtime.javarcc.JPrimitive;
import org.roboticsapi.runtime.rpi.types.Type;

public abstract class JAbstractEquals<T extends Type> extends JPrimitive {
	JInPort<T> inFirst = add("inFirst", new JInPort<T>());
	JInPort<T> inSecond = add("inSecond", new JInPort<T>());
	JOutPort<RPIbool> outValue = add("outValue", new JOutPort<RPIbool>());

	JParameter<T> propFirst = add("First", new JParameter<T>());
	JParameter<T> propSecond = add("Second", new JParameter<T>());
	JParameter<T> propEpsilon = add("Epsilon", new JParameter<T>());

	@Override
	public void checkParameters() throws IllegalArgumentException {
	}

	@Override
	public void updateData() {
		T first = inFirst.get(propFirst);
		T second = inSecond.get(propSecond);
		if (anyNull(first, second))
			return;
		outValue.set(equals(first, second, propEpsilon.get()));
	}

	protected abstract RPIbool equals(T first, T second, T epsilon);
}
