/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.runtime.core.javarcc.primitives;

import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractArray;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractArraySet;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractArraySlice;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractEquals;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractStrictBinaryOp;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractStrictBinaryPredicate;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JAbstractUnaryOp;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericArrayGet;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericArrayNetcommIn;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericArrayNetcommOut;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericAtTime;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericConditional;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericInterNetcommIn;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericInterNetcommOut;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericIsNull;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericNetcommIn;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericNetcommOut;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericPre;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericSetNull;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericSnapshot;
import org.roboticsapi.runtime.core.javarcc.primitives.generic.JGenericValue;
import org.roboticsapi.runtime.core.types.RPIbool;
import org.roboticsapi.runtime.core.types.RPIdouble;
import org.roboticsapi.runtime.core.types.RPIint;
import org.roboticsapi.runtime.core.types.RPIintArray;
import org.roboticsapi.runtime.javarcc.JParameter;
import org.roboticsapi.runtime.rpi.types.ArrayType;

public class PrimitivesInt {

	public static class JIntAdd extends JAbstractStrictBinaryOp<RPIint> {
		@Override
		protected RPIint op(RPIint first, RPIint second) {
			return new RPIint(first.get() + second.get());
		}
	}

	public static class JIntMultiply extends JAbstractStrictBinaryOp<RPIint> {
		@Override
		protected RPIint op(RPIint first, RPIint second) {
			return new RPIint(first.get() * second.get());
		}
	}

	public static class JIntDivide extends JAbstractStrictBinaryOp<RPIint> {
		@Override
		protected RPIint op(RPIint first, RPIint second) {
			return new RPIint(first.get() / second.get());
		}
	}

	public static class JIntEquals extends JAbstractEquals<RPIint> {
		@Override
		protected RPIbool equals(RPIint first, RPIint second, RPIint epsilon) {
			return new RPIbool(Math.abs(first.get() - second.get()) <= epsilon.get());
		}
	}

	public static class JIntGreater extends JAbstractStrictBinaryPredicate<RPIint> {
		@Override
		protected RPIbool pred(RPIint first, RPIint second) {
			return new RPIbool(first.get() > second.get());
		}
	}

	public static class JIntFromDouble extends JAbstractUnaryOp<RPIdouble, RPIint> {
		@Override
		protected RPIint op(RPIdouble value) {
			if (value == null)
				return null;
			return new RPIint((int) value.get());
		}
	}

	public static class JIntArray extends JAbstractArray<RPIint, RPIintArray> {
		@Override
		protected RPIintArray createArray(int size) {
			return new RPIintArray(size);
		}
	}

	public static class JIntArrayGet extends JGenericArrayGet<RPIint, RPIintArray> {
	}

	public static class JIntArraySet extends JAbstractArraySet<RPIint, RPIintArray> {
		@Override
		protected RPIintArray createArray(int size) {
			return new RPIintArray(size);
		}
	}

	public static class JIntArraySlice extends JAbstractArraySlice<RPIint, RPIintArray> {
		@Override
		protected RPIintArray createArray(int size) {
			return new RPIintArray(size);
		}
	}

	public static class JIntArrayInterNetcommIn extends JGenericInterNetcommIn<RPIintArray> {
	}

	public static class JIntArrayInterNetcommOut extends JGenericInterNetcommOut<RPIintArray> {
	}

	public static class JIntArrayNetcommIn extends JGenericArrayNetcommIn<RPIint, RPIintArray> {
	}

	public static class JIntArrayNetcommOut extends JGenericArrayNetcommOut<RPIint, RPIintArray> {
	}

	public static class JIntAtTime extends JGenericAtTime<RPIint> {
	}

	public static class JIntConditional extends JGenericConditional<RPIint> {
	}

	public static class JIntIsNull extends JGenericIsNull<RPIint> {
	}

	public static class JIntPre extends JGenericPre<RPIint> {
	}

	public static class JIntSetNull extends JGenericSetNull<RPIint> {
	}

	public static class JIntArraySetNull extends JGenericSetNull<ArrayType<RPIint>> {
		JParameter<Integer> size = add("Size", new JParameter<Integer>());
	}

	public static class JIntArrayIsNull extends JGenericSetNull<ArrayType<RPIint>> {
	}

	public static class JIntSnapshot extends JGenericSnapshot<RPIint> {
		public JIntSnapshot() {
			super(new RPIint());
		}
	}

	public static class JIntValue extends JGenericValue<RPIint> {
	}

	public static class JIntInterNetcommIn extends JGenericInterNetcommIn<RPIint> {
	}

	public static class JIntInterNetcommOut extends JGenericInterNetcommOut<RPIint> {
	}

	public static class JIntNetcommIn extends JGenericNetcommIn<RPIint> {
	}

	public static class JIntNetcommOut extends JGenericNetcommOut<RPIint> {
	}

}
