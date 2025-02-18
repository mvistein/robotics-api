package org.roboticsapi.runtime.core.primitives;

import org.roboticsapi.runtime.rpi.InPort;
import org.roboticsapi.runtime.rpi.OutPort;
import org.roboticsapi.runtime.rpi.Parameter;
import org.roboticsapi.runtime.rpi.Primitive;

/**
 * Reads an array value.
 */
public class DoubleArrayGet extends Primitive {
	/** Type name of the primitive */
	public static final String PRIMITIVE_TYPE = "Core::DoubleArrayGet";

	/** Input array */
	private final InPort inArray = new InPort("inArray");

	/** Extracted value */
	private final OutPort outValue = new OutPort("outValue");

	/** Size of the array */
	private final Parameter<org.roboticsapi.runtime.core.types.RPIint> paramSize = new Parameter<org.roboticsapi.runtime.core.types.RPIint>(
			"Size", new org.roboticsapi.runtime.core.types.RPIint("1"));

	/** Item index */
	private final Parameter<org.roboticsapi.runtime.core.types.RPIint> paramIndex = new Parameter<org.roboticsapi.runtime.core.types.RPIint>(
			"Index", new org.roboticsapi.runtime.core.types.RPIint("0"));

	public DoubleArrayGet() {
		super(PRIMITIVE_TYPE);

		// Add all ports
		add(inArray);
		add(outValue);

		// Add all parameters
		add(paramSize);
		add(paramIndex);
	}

	/**
	 * Creates reads an array value.
	 *
	 * @param size  Size of the array
	 * @param index Item index
	 */
	public DoubleArrayGet(org.roboticsapi.runtime.core.types.RPIint paramSize,
			org.roboticsapi.runtime.core.types.RPIint paramIndex) {
		this();

		// Set the parameters
		setSize(paramSize);
		setIndex(paramIndex);
	}

	/**
	 * Creates reads an array value.
	 *
	 * @param size  Size of the array
	 * @param index Item index
	 */
	public DoubleArrayGet(Integer paramSize, Integer paramIndex) {
		this(new org.roboticsapi.runtime.core.types.RPIint(paramSize),
				new org.roboticsapi.runtime.core.types.RPIint(paramIndex));
	}

	/**
	 * Input array
	 *
	 * @return the input port of the block
	 */
	public final InPort getInArray() {
		return this.inArray;
	}

	/**
	 * Extracted value
	 * 
	 * @return the output port of the block
	 */
	public final OutPort getOutValue() {
		return this.outValue;
	}

	/**
	 * Size of the array
	 * 
	 * @return the parameter of the block
	 */
	public final Parameter<org.roboticsapi.runtime.core.types.RPIint> getSize() {
		return this.paramSize;
	}

	/**
	 * Sets a parameter of the block: Size of the array
	 * 
	 * @param value new value of the parameter
	 */
	public final void setSize(org.roboticsapi.runtime.core.types.RPIint value) {
		this.paramSize.setValue(value);
	}

	/**
	 * Sets a parameter of the block: Size of the array
	 * 
	 * @param value new value of the parameter
	 */
	public final void setSize(Integer value) {
		this.setSize(new org.roboticsapi.runtime.core.types.RPIint(value));
	}

	/**
	 * Item index
	 * 
	 * @return the parameter of the block
	 */
	public final Parameter<org.roboticsapi.runtime.core.types.RPIint> getIndex() {
		return this.paramIndex;
	}

	/**
	 * Sets a parameter of the block: Item index
	 * 
	 * @param value new value of the parameter
	 */
	public final void setIndex(org.roboticsapi.runtime.core.types.RPIint value) {
		this.paramIndex.setValue(value);
	}

	/**
	 * Sets a parameter of the block: Item index
	 * 
	 * @param value new value of the parameter
	 */
	public final void setIndex(Integer value) {
		this.setIndex(new org.roboticsapi.runtime.core.types.RPIint(value));
	}

}
