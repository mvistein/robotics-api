/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.multijoint.action;

import org.roboticsapi.core.exception.RoboticsException;

public class JointJoggingException extends RoboticsException {
	private static final long serialVersionUID = 1L;

	public JointJoggingException(Exception innerException) {
		super(innerException);
	}

	public JointJoggingException(String string, Exception innerException) {
		super(string, innerException);
	}

	public JointJoggingException(String string) {
		super(string);
	}
}
