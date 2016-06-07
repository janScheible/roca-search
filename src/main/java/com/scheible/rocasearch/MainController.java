package com.scheible.rocasearch;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author sj
 */
@Controller
public class MainController {
	
	@RequestMapping(path = "/", method = GET)
	public String redirectToIndex() {
		return "redirect:/index.html";
	}

	@RequestMapping(path = "/index.html", method = GET)
	public String index(Map<String, Object> model) {
		return "index";
	}
}
