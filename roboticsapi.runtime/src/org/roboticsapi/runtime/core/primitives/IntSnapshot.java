package org.roboticsapi.runtime.core.primitives;

import org.roboticsapi.runtime.rpi.InPort;
import org.roboticsapi.runtime.rpi.OutPort;
import org.roboticsapi.runtime.rpi.Parameter;
import org.roboticsapi.runtime.rpi.Primitive;

/**
 * A snapshot module (remembers the last value of inValue when inSnapshot was
 * true)
 */
public class IntSnapshot extends Primitive {
	/** Type name of the primitive */
	public static final String PRIMITIVE_TYPE = "Core::IntSnapshot";

	/** Activation port */
	private final InPort inActive = new InPort("inActive");

	/** Reset port */
	private final InPort inReset = new InPort("inReset");

	/** Snapshot activator */
	private final InPort inSnapshot = new InPort("inSnapshot");

	/** Value to watch */
	private final InPort inValue = new InPort("inValue");

	/** Value of inValue when inSnapshot was true */
	private final OutPort outValue = new OutPort("outValue");

	/** Initial value */
	private final Parameter<org.roboticsapi.runtime.core.types.RPIint> paramValue = new Parameter<org.roboticsapi.runtime.core.types.RPIint>(
			"Value", new org.roboticsapi.runtime.core.types.RPIint("0"));

	public IntSnapshot() {
		super(PRIMITIVE_TYPE);

		// Add all ports
		add(inActive);
		add(inReset);
		add(inSnapshot);
		add(inValue);
		add(outValue);

		// Add all parameters
		add(paramValue);
	}

	/**
	 * Creates a snapshot module (remembers the last value of inValue when
	 * inSnapshot was true)
	 *
	 * @param paramValue Initial value
	 */
	public IntSnapshot(org.roboticsapi.runtime.core.types.RPIint paramValue) {
		this();

		// Set the parameters
		setValue(paramValue);
	}

	/**
	 * Creates a snapshot module (remembers the last value of inValue when
	 * inSnapshot was true)
	 *
	 * @param paramValue Initial value
	 */
	public IntSnapshot(Integer paramValue) {
		this(new org.roboticsapi.runtime.core.types.RPIint(paramValue));
	}

	/**
	 * Activation port
	 *
	 * @return the input port of the block
	 */
	public final InPort getInActive() {
		return this.inActive;
	}

	/**
	 * Reset port
	 *
	 * @return the input port of the block
	 */
	public final InPort getInReset() {
		return this.inReset;
	}

	/**
	 * Snapshot activator
	 *
	 * @return the input port of the block
	 */
	public final InPort getInSnapshot() {
		return this.inSnapshot;
	}

	/**
	 * Value to watch
	 *
	 * @return the input port of the block
	 */
	public final InPort getInValue() {
		return this.inValue;
	}

	/**
	 * Value of inValue when inSnapshot was true
	 * 
	 * @return the output port of the block
	 */
	public final OutPort getOutValue() {
		return this.outValue;
	}

	/**
	 * Initial value
	 * 
	 * @return the parameter of the block
	 */
	public final Parameter<org.roboticsapi.runtime.core.types.RPIint> getValue() {
		return this.paramValue;
	}

	/**
	 * Sets a parameter of the block: Initial value
	 * 
	 * @param value new value of the parameter
	 */
	public final void setValue(org.roboticsapi.runtime.core.types.RPIint value) {
		this.paramValue.setValue(value);
	}

	/**
	 * Sets a parameter of the block: Initial value
	 * 
	 * @param value new value of the parameter
	 */
	public final void setValue(Integer value) {
		this.setValue(new org.roboticsapi.runtime.core.types.RPIint(value));
	}

}
