package com.kchandrakant.learning.config;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class Config {

	private static final PersistenceManagerFactory PERSISTENCE_MANAGER_FACTORY = JDOHelper
			.getPersistenceManagerFactory("PersistenceUnit");

	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		return PERSISTENCE_MANAGER_FACTORY;
	}
}