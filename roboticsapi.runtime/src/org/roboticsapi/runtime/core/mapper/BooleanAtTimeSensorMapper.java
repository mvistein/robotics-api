/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.core.mapper;

import org.roboticsapi.core.sensor.BooleanAtTimeSensor;
import org.roboticsapi.runtime.SoftRobotRuntime;
import org.roboticsapi.runtime.core.primitives.BooleanAtTime;
import org.roboticsapi.runtime.mapping.MappingException;
import org.roboticsapi.runtime.mapping.net.DataflowOutPort;
import org.roboticsapi.runtime.mapping.net.DoubleDataflow;
import org.roboticsapi.runtime.mapping.net.NetFragment;
import org.roboticsapi.runtime.mapping.net.StateDataflow;
import org.roboticsapi.runtime.mapping.parts.SensorMapper;
import org.roboticsapi.runtime.mapping.parts.SensorMappingContext;
import org.roboticsapi.runtime.mapping.parts.SensorMappingPorts;
import org.roboticsapi.runtime.mapping.result.SensorMapperResult;
import org.roboticsapi.runtime.mapping.result.impl.BooleanSensorMapperResult;

public class BooleanAtTimeSensorMapper implements SensorMapper<SoftRobotRuntime, Boolean, BooleanAtTimeSensor> {

	@Override
	public SensorMapperResult<Boolean> map(SoftRobotRuntime runtime, BooleanAtTimeSensor sensor,
			SensorMappingPorts ports, SensorMappingContext context) throws MappingException {

		NetFragment result = new NetFragment("BooleanAtTime");
		SensorMapperResult<Boolean> innerResult = runtime.getMapperRegistry().mapSensor(runtime,
				sensor.getOtherSensor(), result, context);
		SensorMapperResult<Double> ageResult = runtime.getMapperRegistry().mapSensor(runtime, sensor.getAge(), result,
				context);

		BooleanAtTime not = result.add(new BooleanAtTime(sensor.getMaxAge()));
		result.connect(innerResult.getSensorPort(),
				result.addInPort(innerResult.getSensorPort().getType(), true, not.getInValue()));
		result.connect(ageResult.getSensorPort(), not.getInAge(), new DoubleDataflow());

		DataflowOutPort resultPort = result.addOutPort(new StateDataflow(), false, not.getOutValue());
		return new BooleanSensorMapperResult(result, resultPort);
	}
}
