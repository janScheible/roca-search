package com.scheible.frontend.entry;

import com.scheible.backend.Entry;

/**
 *
 * @author sj
 */
public class TemplateEntry {
	
	public final String name;
	public final String description;
	public final String resourceUrl;
	
	public TemplateEntry(Entry entry, String resourceUrl) {
		name = entry.getName();
		description = entry.getDescription() + " [" + entry.getUrl() + "]";
		this.resourceUrl = resourceUrl;
	}
}
