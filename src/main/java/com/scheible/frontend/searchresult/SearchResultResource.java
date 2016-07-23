package com.scheible.frontend.searchresult;

import static com.google.common.base.CaseFormat.*;
import com.google.common.collect.Sets;
import com.scheible.backend.Entry;
import com.scheible.backend.EntryRepository;
import com.scheible.backend.SearchResult;
import com.scheible.backend.SearchResultRepository;
import com.scheible.frontend.entry.EntryResource;
import com.scheible.frontend.common.ResourceNotFoundException;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sj
 */
@Controller
@Transactional(readOnly = true)
public class SearchResultResource {

	public final static String RESOURCE_NAME = "search-result";
	public final static String TEMPLATE_NAME = "resource/" + RESOURCE_NAME;

	private final EntryRepository entryRepository;
	private final SearchResultRepository searchResultRepository;

	@Autowired
	public SearchResultResource(EntryRepository entryRepository, SearchResultRepository searchResultRepository) {
		this.entryRepository = entryRepository;
		this.searchResultRepository = searchResultRepository;
	}

	@RequestMapping(path = "/" + RESOURCE_NAME + "/{id}.html", method = GET, produces = "text/html")
	public String get(@PathVariable long id,
			@RequestParam(name = "resource-only", required = false) Boolean resourceOnly,
			Map<String, Object> model) {
		SearchResult result = searchResultRepository.findOne(id);

		if (result != null) {
			model.put("resourceOnly", Boolean.TRUE.equals(resourceOnly));
			model.put(LOWER_HYPHEN.to(LOWER_CAMEL, RESOURCE_NAME), new TemplateSearchResult(result, getResourceUrl(result.getId()), EntryResource::getResourceUrl));
			
			return TEMPLATE_NAME;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@RequestMapping(path = "/" + RESOURCE_NAME + ".html", method = POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
	@Transactional(readOnly = false)
	public String create(@RequestParam String query,
			@RequestHeader(name = "resource-only", required = false) Boolean resourceOnly,
			Map<String, Object> model) {
		resourceOnly = Boolean.TRUE.equals(resourceOnly);

		SearchResult result = search(query);

		model.put("resourceOnly", resourceOnly);
		model.put(LOWER_HYPHEN.to(LOWER_CAMEL, RESOURCE_NAME), new TemplateSearchResult(result, getResourceUrl(result.getId()), EntryResource::getResourceUrl));

		return resourceOnly ? TEMPLATE_NAME : "redirect:" + getResourceUrl(result.getId());
	}

	public static String getResourceUrl(long id) {
		return "/" + RESOURCE_NAME + "/" + id + ".html";
	}

	private SearchResult search(String query) {
		SearchResult result = new SearchResult();
		result.setQuery(query);

		// TODO Currently no search logic at all. Simply all entries are returned.
		Iterable<Entry> entries = entryRepository.findAll();
		result.setEntries(Sets.newHashSet(entries));

		searchResultRepository.save(result);
		return result;
	}
}
