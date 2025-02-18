/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.multijoint;

import java.util.List;
import java.util.Map;

import org.roboticsapi.core.Actuator;
import org.roboticsapi.core.exception.RoboticsException;
import org.roboticsapi.core.sensor.DoubleSensor;
import org.roboticsapi.multijoint.link.Link;
import org.roboticsapi.world.Frame;
import org.roboticsapi.world.PhysicalObject;
import org.roboticsapi.world.Relation;
import org.roboticsapi.world.sensor.RelationSensor;

/**
 * Base interface for robot arms consisting of multiple {@link AbstractJoint}s.
 */
public interface MultiJointDevice extends Actuator, PhysicalObject {

	/**
	 * Gets the joint home position.
	 *
	 * @return the home position
	 */
	double[] getHomePosition();

	/**
	 * Gets the {@link AbstractJoint} with the given number (zero-based).
	 *
	 * @param nr the joint number
	 * @return the joint
	 */
	Joint getJoint(int nr);

	/**
	 * Gets the angles of all robot {@link AbstractJoint}s.
	 *
	 * @return the joint angles
	 * @throws RoboticsException if the angles could not be retrieved
	 */
	double[] getJointAngles() throws RoboticsException;

	/**
	 * Gets sensors for the commanded position of all robot {@link AbstractJoint}s.
	 *
	 * @return the joint sensors
	 */
	DoubleSensor[] getJointSensors() throws RoboticsException;

	/**
	 * Gets the total number of {@link AbstractJoint}s of this robot.
	 *
	 * @return the joint count
	 */
	int getJointCount();

	/**
	 * Gets all of this Robot's {@link AbstractJoint}s.
	 *
	 * @return the joints
	 */
	List<? extends Joint> getJoints();

	/**
	 * Gets the measured joint angles.
	 *
	 * @return the measured joint angles
	 * @throws RoboticsException the robotics exception
	 */
	double[] getMeasuredJointAngles() throws RoboticsException;

	/**
	 * Gets the {@link Link} with the given number (zero-based).
	 *
	 * @param nr the link number
	 * @return the link
	 */
	Link getLink(int nr);

	/**
	 * Gets all of this Robot's {@link Link}s.
	 *
	 * @return the links
	 */
	Link[] getLinks();

	/**
	 * Creates the relation map for given joint positions
	 *
	 * @param joints joint positions
	 * @return relation map for the given joint position (for use with
	 *         {@link Frame#getRelationSensor(Frame, Map, Relation...)})
	 */
	Map<Relation, RelationSensor> getForwardKinematicsRelationMap(double[] joints);

	/**
	 * Creates the relation map for given joint positions
	 *
	 * @param joints joint positions
	 * @return relation map for the given joint position (for use with
	 *         {@link Frame#getRelationSensor(Frame, Map, Relation...)})
	 */
	Map<Relation, RelationSensor> getForwardKinematicsRelationMap(DoubleSensor[] joints);

	@Override
	public MultiJointDeviceDriver getDriver();

}