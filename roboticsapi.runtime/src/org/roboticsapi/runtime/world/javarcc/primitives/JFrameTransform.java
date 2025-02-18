/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.world.javarcc.primitives;

import org.roboticsapi.runtime.javarcc.JInPort;
import org.roboticsapi.runtime.javarcc.JOutPort;
import org.roboticsapi.runtime.javarcc.JPrimitive;
import org.roboticsapi.runtime.world.types.RPIFrame;
import org.roboticsapi.world.mutable.MutableTransformation;

/**
 * This class implements a frame transformer module
 */
public class JFrameTransform extends JPrimitive {
	private JInPort<RPIFrame> inFirst = add("inFirst", new JInPort<RPIFrame>());
	private JInPort<RPIFrame> inSecond = add("inSecond", new JInPort<RPIFrame>());
	private JOutPort<RPIFrame> outValue = add("outValue", new JOutPort<RPIFrame>());
	private MutableTransformation first = RPICalc.frameCreate();
	private MutableTransformation second = RPICalc.frameCreate();
	private RPIFrame value = RPICalc.rpiFrameCreate();

	@Override
	public void checkParameters() throws IllegalArgumentException {
		connected(inFirst, inSecond);
	}

	@Override
	public void updateData() {
		if (anyNull(inFirst, inSecond))
			return;
		RPICalc.rpiToFrame(inFirst.get(), first);
		RPICalc.rpiToFrame(inSecond.get(), second);
		first.multiply(second);
		RPICalc.frameToRpi(first, value);
		outValue.set(value);
	}
};
