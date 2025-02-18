/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.core;

import org.roboticsapi.core.runtime.CommandRealtimeException;

public class ActionRealtimeException extends CommandRealtimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Action action;

	public ActionRealtimeException(Action action) {
		super("Exception in Action " + action);
		this.action = action;
	}

	public ActionRealtimeException(Action action, String exceptionMessage) {
		super("Exception in Action " + action + " (" + exceptionMessage + ")");
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

	// Method hashCode() is inherited from CommandRealtimeException
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		if (this.action == null || ((ActionRealtimeException) obj).action == null) {
			return true;
		}
		return this.action.equals(((ActionRealtimeException) obj).action);
	}

}
