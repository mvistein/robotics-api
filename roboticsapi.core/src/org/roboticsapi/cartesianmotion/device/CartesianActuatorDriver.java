/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.cartesianmotion.device;

import org.roboticsapi.core.ActuatorDriver;
import org.roboticsapi.world.Frame;
import org.roboticsapi.world.Relation;

public interface CartesianActuatorDriver extends ActuatorDriver {

	Frame getReferenceFrame();

	Frame getMovingFrame();

	Relation createRelation();

	void setup(Frame referenceFrame, Frame movingFrame);

}
