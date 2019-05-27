package org.roboticsapi.runtime.world.primitives;

import org.roboticsapi.runtime.rpi.InPort;
import org.roboticsapi.runtime.rpi.OutPort;
import org.roboticsapi.runtime.rpi.Parameter;
import org.roboticsapi.runtime.rpi.Primitive;

/**
 * A snapshot module (remembers the last value of inValue when inSnapshot was
 * true)
 */
public class RotationSnapshot extends Primitive {
	/** Type name of the primitive */
	public static final String PRIMITIVE_TYPE = "World::RotationSnapshot";

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
	private final Parameter<org.roboticsapi.runtime.world.types.RPIRotation> paramValue = new Parameter<org.roboticsapi.runtime.world.types.RPIRotation>(
			"Value", new org.roboticsapi.runtime.world.types.RPIRotation("{a:0,b:-0,c:0}"));

	public RotationSnapshot() {
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
	public RotationSnapshot(org.roboticsapi.runtime.world.types.RPIRotation paramValue) {
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
	public RotationSnapshot(String paramValue) {
		this(new org.roboticsapi.runtime.world.types.RPIRotation(paramValue));
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
	public final Parameter<org.roboticsapi.runtime.world.types.RPIRotation> getValue() {
		return this.paramValue;
	}

	/**
	 * Sets a parameter of the block: Initial value
	 * 
	 * @param value new value of the parameter
	 */
	public final void setValue(org.roboticsapi.runtime.world.types.RPIRotation value) {
		this.paramValue.setValue(value);
	}

	/**
	 * Sets a parameter of the block: Initial value
	 * 
	 * @param value new value of the parameter
	 */
	public final void setValue(String value) {
		this.setValue(new org.roboticsapi.runtime.world.types.RPIRotation(value));
	}

}
