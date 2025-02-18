package org.roboticsapi.runtime.world.types;

import org.roboticsapi.runtime.rpi.types.ArrayType;

/**
 * Array of RPIWorld::Vector a.k.a World::Vector[]
 */
public class RPIVectorArray extends ArrayType<org.roboticsapi.runtime.world.types.RPIVector> {

	/**
	 * Creates an array of the given size and initializes it with the given contents
	 *
	 * @param capacity size of the array
	 * @param value    initial value (in RPI syntax)
	 */
	public RPIVectorArray(int capacity, String value) {
		this(capacity);
		consumeString(value);
	}

	/**
	 * Creates an array and initializes it with the given contents
	 *
	 * @param value initial value (in RPI syntax)
	 */
	public RPIVectorArray(String value) {
		super(value);
	}

	/**
	 * Creates an array of the given size
	 *
	 * @param capacity size of the array
	 */
	public RPIVectorArray(int capacity) {
		super(capacity);
	}

	@Override
	protected org.roboticsapi.runtime.world.types.RPIVector getInitialValue() {
		return new org.roboticsapi.runtime.world.types.RPIVector();
	}
}
