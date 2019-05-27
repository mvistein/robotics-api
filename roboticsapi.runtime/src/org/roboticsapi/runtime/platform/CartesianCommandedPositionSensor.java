/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.platform;

import org.roboticsapi.core.ActuatorDriver;
import org.roboticsapi.world.sensor.TransformationSensor;

public final class CartesianCommandedPositionSensor extends TransformationSensor {

	private final ActuatorDriver platformDriver;
	private final String deviceName;

	public CartesianCommandedPositionSensor(ActuatorDriver platformDriver, String deviceName) {
		super(platformDriver.getRuntime());
		this.platformDriver = platformDriver;
		this.deviceName = deviceName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	@Override
	public boolean equals(Object obj) {
		return classEqual(obj) && platformDriver.equals(((CartesianCommandedPositionSensor) obj).platformDriver)
				&& deviceName.equals(((CartesianCommandedPositionSensor) obj).deviceName);
	}

	@Override
	public int hashCode() {
		return classHash(platformDriver, deviceName);
	}

	@Override
	public boolean isAvailable() {
		return platformDriver.isPresent();
	}
}
