/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. 
 *
 * Copyright 2010-2017 ISSE, University of Augsburg 
 */

package org.roboticsapi.startup.launcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.roboticsapi.core.Predicate;
import org.roboticsapi.core.RoboticsContext;
import org.roboticsapi.core.RoboticsContextImpl;
import org.roboticsapi.core.RoboticsObject;
import org.roboticsapi.core.RoboticsRuntime;
import org.roboticsapi.core.exception.InitializationException;
import org.roboticsapi.extension.Extension;
import org.roboticsapi.extension.ExtensionHandler;
import org.roboticsapi.startup.configuration.util.ConfigurationHook;
import org.roboticsapi.startup.configuration.util.ConfigurationHookAdapter;
import org.roboticsapi.startup.configuration.util.IllegalConfigurationException;
import org.roboticsapi.startup.configuration.util.MultipleIllegalConfigurationsException;
import org.roboticsapi.startup.configuration.xml.ApplicationConfigParser;
import org.roboticsapi.world.World;

/**
 */
public abstract class Rapi {

	private static final Set<Rapi> instances = new HashSet<Rapi>();

	private final RoboticsContext context;
	private final World world;
	private boolean destroyed = false;
	private final RoboticsObjectBuilderHandler builderHandler;

	protected Rapi() {
		this(null);
	}

	/**
	 * Creates a new robotics api instance and prefills it with a world and its
	 * origin.
	 *
	 * @param name
	 */
	protected Rapi(String name) {
		synchronized (instances) {
			if (name == null) {
				name = createUniqueName(instances);
			} else if ("".equals(name.trim())) {
				throw new IllegalArgumentException("Unsupported name <empty>");
			} else if (nameExists(name, instances)) {
				throw new IllegalArgumentException("Duplicate name '" + name + "'");
			}

			this.context = new RoboticsContextImpl(name);

			try {
				this.world = createWorld(name + " world", this.context);
			} catch (InitializationException e) {
				// Does hopefully never happen on an empty context
				throw new RuntimeException(e);
			}

			this.builderHandler = new RoboticsObjectBuilderHandler();
			context.addExtensionHandler(this.builderHandler);

			instances.add(this);
		}
	}

	private final static String createUniqueName(Set<Rapi> instances) {
		String num = "" + (instances.size() + 1);
		if (nameExists("Robotics World (" + num + ")", instances)) {
			int _num = 1;
			while (nameExists("Robotics World (" + num + "_" + _num + ")", instances)) {
				_num++;
			}
			num += "_" + _num;
		}
		return "Robotics World (" + num + ")";
	}

	private final static boolean nameExists(String name, Set<Rapi> instances) {
		for (Rapi rapi : instances) {
			if (rapi.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	private final static World createWorld(String name, RoboticsContext context) throws InitializationException {
		World world = new World();
		world.setName(name);
		context.initialize(world);
		context.register(world);
		context.register(world.getOrigin());
		return world;
	}

	public final static Set<Rapi> getAllInstances() {
		synchronized (instances) {
			return Collections.unmodifiableSet(instances);
		}
	}

	public final synchronized String getName() {
		checkDestroyed();
		return context.getName();
	}

	private final void checkDestroyed() {
		if (destroyed) {
			throw new IllegalStateException("Rapi is already destroyed");
		}
	}

	/**
	 * Loads all {@link RoboticsObject}s of the given configuration file into this
	 * robotics api instance.
	 *
	 * @param configFile
	 * @throws MultipleIllegalConfigurationsException
	 */
	public final synchronized void loadConfigFile(String configFile) throws MultipleIllegalConfigurationsException {
		loadConfigFile(new File(configFile));
	}

	/**
	 * Loads all {@link RoboticsObject}s of the given configuration file into this
	 * robotics api instance.
	 *
	 * @param configFile
	 * @throws MultipleIllegalConfigurationsException
	 */
	public final synchronized void loadConfigFile(File configFile) throws MultipleIllegalConfigurationsException {
		InternalHook hook = new InternalHook();
		loadConfigFile(configFile, hook);
		if (!hook.exceptions.isEmpty()) {
			throw new MultipleIllegalConfigurationsException(hook.exceptions);
		}
	}

	/**
	 * Loads all {@link RoboticsObject}s of the given configuration file into this
	 * robotics api instance.
	 *
	 * @param configFile
	 * @param hook
	 * @throws MultipleIllegalConfigurationsException
	 */
	public final synchronized void loadConfigFile(String configFile, ConfigurationHook hook)
			throws MultipleIllegalConfigurationsException {
		loadConfigFile(new File(configFile), hook);
	}

	/**
	 * Loads all {@link RoboticsObject}s of the given configuration file into this
	 * robotics api instance.
	 *
	 * @param configFile
	 * @param hook
	 * @throws MultipleIllegalConfigurationsException
	 */
	public final synchronized void loadConfigFile(File configFile, ConfigurationHook hook)
			throws MultipleIllegalConfigurationsException {
		List<RoboticsObject> objects = ApplicationConfigParser.build(configFile, builderHandler, context, hook);
		for (RoboticsObject ro : objects) {
			if (ro.getName() != null) {
				context.register(ro);
			}
		}
	}

	/**
	 * Stores all named {@link RoboticsObject}s of this robotics api instance into
	 * the given configuration file.
	 *
	 * @param filename
	 */
	public final synchronized void storeAsConfigFile(String filename) {
		// TODO:
		throw new RuntimeException("Not yet implemented: storeAsConfigFile");
	}

	/**
	 * Returns a list containing all {@link RoboticsObject}s which have been added
	 * explicitly. Other objects (such as implicit, maybe even unnamed frames) are
	 * not concerned.
	 *
	 * @return
	 */
	public final synchronized List<RoboticsObject> getRegistered() {
		checkDestroyed();
		return context.getRegistered(RoboticsObject.class);
	}

	/**
	 * Returns all {@link RoboticsObject}s which are known by this robotics api
	 * instance.
	 *
	 * @return
	 */
	public final synchronized List<RoboticsObject> getAll() {
		checkDestroyed();
		return context.getAll(RoboticsObject.class);
	}

	/**
	 * Returns all {@link RoboticsObject}s of the given type which are known by this
	 * robotics api instance.
	 *
	 * @param type
	 * @return
	 */
	public final synchronized <T extends RoboticsObject> List<T> getAll(Class<T> type) {
		checkDestroyed();
		return context.getAll(type);
	}

	/**
	 * Returns all {@link RoboticsObject}s of the given type and fulfilling the
	 * given predicate which are known by this robotics api instance.
	 *
	 * @param type
	 * @param predicate
	 * @return
	 */
	public final synchronized <T extends RoboticsObject> List<T> getAll(Class<T> type, Predicate<T> predicate) {
		checkDestroyed();
		List<T> result = new ArrayList<T>();
		for (T ro : context.getAll(type)) {
			if (predicate == null || predicate.appliesTo(ro)) {
				result.add(ro);
			}
		}
		return result;
	}

	/**
	 * Returns the one {@link RoboticsObject} with the given name.
	 *
	 * @param name
	 * @return
	 */
	public final synchronized RoboticsObject getSingle(String name) {
		checkDestroyed();
		return getSingle(name, RoboticsObject.class);
	}

	/**
	 * Returns the one {@link RoboticsObject} with the given name and having the
	 * given type.
	 *
	 * @param name
	 * @param type
	 * @return
	 */
	public final synchronized <T extends RoboticsObject> T getSingle(String name, Class<T> type) {
		checkDestroyed();
		return context.getRegistered(name, type);
	}

	/**
	 * Returns whether a {@link RoboticsObject} with the given name exists.
	 *
	 * @param name
	 * @param type
	 * @return
	 */
	public final synchronized boolean hasSingle(String name) {
		checkDestroyed();
		return hasSingle(name, RoboticsObject.class);
	}

	/**
	 * Returns whether a {@link RoboticsObject} with the given name exists of the
	 * given type.
	 *
	 * @param name
	 * @param type
	 * @return
	 */
	public final synchronized <T extends RoboticsObject> boolean hasSingle(String name, Class<T> type) {
		checkDestroyed();
		return context.isRegistered(name, type);
	}

	/**
	 * Adds a {@link RoboticsObject} to this robotics api instance.
	 *
	 * @param roboticsObject
	 * @throws InitializationException
	 */
	public final synchronized void add(RoboticsObject roboticsObject) throws InitializationException {
		checkDestroyed();
		context.initialize(roboticsObject);
		context.register(roboticsObject);
	}

	/**
	 * Removes a {@link RoboticsObject} from this robotics api instance.
	 *
	 * @param roboticsObject
	 * @throws InitializationException
	 */
	public final synchronized void remove(RoboticsObject roboticsObject) throws InitializationException {
		checkDestroyed();
		context.unregister(roboticsObject);
		context.uninitialize(roboticsObject);
	}

	/**
	 * Returns the world origin of this robotics api instance.
	 *
	 * @return
	 */
	public final synchronized World getWorld() {
		checkDestroyed();
		return world;
	}

	public final synchronized void setGlobalOverride(double override) {
		checkDestroyed();
		for (RoboticsRuntime r : context.getAll(RoboticsRuntime.class)) {
			r.setOverride(override);
			// TODO: Realtime!
		}
	}

	public final synchronized Logger getLogger() {
		checkDestroyed();
		return context.getLogger();
	}

	/**
	 * Removes all {@link RoboticsObject}s from this robotics api instance except
	 * the world and its origin.
	 */
	public final synchronized void clearRoboticsObjects() {
		checkDestroyed();

		// unregister all
		// uninitialize all
		List<RoboticsObject> all = context.getAll(RoboticsObject.class);
		all.remove(getWorld());
		all.remove(getWorld().getOrigin());
		while (all.size() > 0) {
			boolean change = false;
			for (RoboticsObject ro : new ArrayList<RoboticsObject>(all)) {
				try {
					context.unregister(ro);
				} catch (Throwable e) {
				}
				try {
					context.uninitialize(ro);
					all.remove(ro);
					change = true;
				} catch (InitializationException e) {
				}
			}
			if (!change) {
				throw new IllegalStateException(
						"Can't destroy instance since some related objects can not be uninitialized");
			}
		}

	}

	/**
	 * This method is called before this robotics api instance gets destroyed.
	 */
	protected abstract void beforeDestroy();

	/**
	 * Destroys this robotics api instance. It can not be reused afterwards.
	 */
	public final synchronized void destroy() {
		checkDestroyed();
		beforeDestroy();
		context.removeExtensionHandler(builderHandler);
		synchronized (instances) {
			instances.remove(this);
		}
		context.destroy();
		destroyed = true;
	}

	public final synchronized void registerExtension(Extension extension) {
		checkDestroyed();
		context.addExtension(extension);
	}

	public final synchronized boolean hasExtension(Extension extension) {
		checkDestroyed();
		return context.hasExtension(extension);
	}

	public final synchronized void unregisterExtension(Extension extension) {
		checkDestroyed();
		context.removeExtension(extension);
	}

	protected final synchronized <T extends Extension> void registerExtensionHandler(
			ExtensionHandler<T> extensionHandler) {
		checkDestroyed();
		context.addExtensionHandler(extensionHandler);
	}

	protected final synchronized <T extends Extension> void unregisterExtensionHandler(
			ExtensionHandler<T> extensionHandler) {
		checkDestroyed();
		context.removeExtensionHandler(extensionHandler);
	}

	private static class InternalHook extends ConfigurationHookAdapter {

		private final List<IllegalConfigurationException> exceptions = new ArrayList<IllegalConfigurationException>();

		@Override
		public void onInputReadingFailed(String message, Exception e) {
			exceptions.add(new IllegalConfigurationException("", message, e));
		}

		@Override
		public void onBuildingFailed(String id, String type, String message) {
			exceptions.add(new IllegalConfigurationException(id, type, "", message, null));
		}

		@Override
		public void onConfiguringFailed(String id, String type, String key, String message) {
			exceptions.add(new IllegalConfigurationException(id, type, key, message, null));
		}

		@Override
		public void onInitializingFailed(String id, String type, String message, Exception exception) {
			exceptions.add(new IllegalConfigurationException(id, type, "", message, exception));
		}
	}

}
