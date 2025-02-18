/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.core.types;

import java.util.regex.Pattern;

import org.roboticsapi.runtime.rpi.types.PrimitiveType;

public class RPIstring extends PrimitiveType {

	static Pattern stringPattern = Pattern.compile("\\s*\"(.*?)\"\\s*(.*)");

	private java.lang.String value;

	public RPIstring(String value) {
		super(stringPattern);
		this.value = value;
	}

	public String get() {
		return value;
	}

	@Override
	protected void consumeValue(String string) {
		this.value = string;
	}

	@Override
	public void appendString(StringBuilder ret) {
		ret.append(value == null ? "" : value.replaceAll("\"", "\\\""));
	}

}
