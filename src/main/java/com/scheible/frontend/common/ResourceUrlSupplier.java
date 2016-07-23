package com.scheible.frontend.common;

/**
 *
 * @author sj
 */
@FunctionalInterface
public interface ResourceUrlSupplier {
	
	String get(long id);	
}
