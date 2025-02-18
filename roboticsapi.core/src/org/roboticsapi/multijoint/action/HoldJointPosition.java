/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.multijoint.action;

import org.roboticsapi.core.Action;
import org.roboticsapi.core.action.ProcessAction;
import org.roboticsapi.core.sensor.DoubleSensor;

/**
 * The HoldJointPosition Action lets an Actuator hold the joint positions given
 * by a set of Sensors. The joints follow changes to the target position
 * immediately (even if infeasible). The list of Sensors given is mapped to the
 * actuator's joints in the order they appear.
 */
public class HoldJointPosition extends Action implements ProcessAction, JointSpaceAction {
	private final DoubleSensor[] sensors;

	/**
	 * Instantiates a new HoldJointPosition action.
	 * 
	 * @param sensors the sensors that determine the axis target positions
	 */
	public HoldJointPosition(DoubleSensor... sensors) {
		super(0);
		if (sensors == null) {
			throw new IllegalArgumentException("sensors");
		}
		this.sensors = sensors;
	}

	/**
	 * Gets the sensors used in this HoldJointPosition.
	 * 
	 * @return the sensors
	 */
	public DoubleSensor[] getSensors() {
		return sensors;
	}
}
