
package com.scheible.frontend.entry;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import com.scheible.backend.Entry;
import com.scheible.backend.EntryRepository;
import com.scheible.frontend.exception.ResourceNotFoundException;
import static com.scheible.frontend.searchresult.SearchResultResource.RESOURCE_NAME;
import com.scheible.frontend.searchresult.TemplateSearchResult;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sj
 */
@Controller
@Transactional(readOnly = true)
public class EntryResource {
	
	public final static String RESOURCE_NAME = "entry";
	public final static String TEMPLATE_NAME = "resource/" + RESOURCE_NAME;
	
	private final EntryRepository entryRepository;

	@Autowired
	public EntryResource(EntryRepository entryRepository) {
		this.entryRepository = entryRepository;
	}
	
	@RequestMapping(path = "/" + RESOURCE_NAME + "/{id}.html", method = GET, produces = "text/html")
	public String get(@PathVariable long id,
			@RequestParam(name = "resource-only", required = false) Boolean resourceOnly,
			Map<String, Object> model) {
		Entry entry = entryRepository.findOne(id);
		if(entry != null) {
			model.put("resourceOnly", Boolean.TRUE.equals(resourceOnly));
			model.put(LOWER_HYPHEN.to(LOWER_CAMEL, RESOURCE_NAME), new TemplateEntry(entry, getResourceUrl(id)));
			
			return TEMPLATE_NAME;
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	public static String getResourceUrl(long id) {
		return "/" + RESOURCE_NAME + "/" + id + ".html";
	}	
}
