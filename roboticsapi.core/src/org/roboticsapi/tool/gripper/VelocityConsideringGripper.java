/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.tool.gripper;

import org.roboticsapi.core.sensor.DoubleSensor;
import org.roboticsapi.core.sensor.SensorReadException;
import org.roboticsapi.tool.gripper.parameters.VelocityParameter;

/**
 * This interface represents a gripper with velocity skills.
 * <p>
 * This interface can be implemented by any gripper which can cope with velocity
 * data - in particular as device parameter for gripping activities (cf.
 * {@link VelocityParameter}).
 * 
 * @see VelocityParameter
 */
public interface VelocityConsideringGripper extends Gripper {

	/**
	 * Returns the gripper's min. permitted velocity in [m/s].
	 * 
	 * @return the min. permitted velocity in [m/s].
	 */
	double getMinimumVelocity();

	/**
	 * Returns the gripper's max. permitted velocity in [m/s].
	 * 
	 * @return the max. permitted velocity in [m/s].
	 */
	double getMaximumVelocity();

	/**
	 * Returns the gripper's current velocity in [m/s].
	 * 
	 * @return the current velocity in [m/s].
	 * @throws SensorReadException if a force sensor is not available or the current
	 *                             value cannot be retrieved.
	 */
	double getCurrentVelocity() throws SensorReadException;

	/**
	 * Returns a sensor for the gripper's velocity.
	 * 
	 * @return a sensor for velocity. It might be <code>null</code> if sensor is not
	 *         available.
	 */
	DoubleSensor getVelocitySensor();

}
