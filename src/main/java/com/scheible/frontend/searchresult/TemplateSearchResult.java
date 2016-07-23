package com.scheible.frontend.searchresult;

import com.scheible.frontend.entry.TemplateEntry;
import com.google.common.collect.ImmutableList;
import com.scheible.backend.Entry;
import com.scheible.backend.SearchResult;
import com.scheible.frontend.common.ResourceUrlSupplier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sj
 */
public class TemplateSearchResult {
	
	public final String query;
	public final List<TemplateEntry> entries;
	public final String resourceUrl;

	public TemplateSearchResult(SearchResult searchResult, String resourceUrl, ResourceUrlSupplier resourceUrlSupplier) {
		query = searchResult.getQuery();
		this.resourceUrl = resourceUrl;
		
		List<TemplateEntry> templateEntries = new ArrayList<>();
		for(Entry entry : searchResult.getEntries()) {
			templateEntries.add(new TemplateEntry(entry, resourceUrlSupplier.get(entry.getId())));
		}
		entries = ImmutableList.copyOf(templateEntries);
	}	
	
	
}
