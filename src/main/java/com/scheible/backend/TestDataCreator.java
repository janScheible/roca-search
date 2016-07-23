package com.scheible.backend;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author sj
 */
@Controller
public class TestDataCreator {

    @Autowired
    EntryRepository entryRepository;

    @PostConstruct
    @Transactional
    protected void create() {
        Entry dontFightTheWeb = new Entry();
        dontFightTheWeb.setName("Don't Fight The Web");
        dontFightTheWeb.setDescription("Most of the time its good to be close to the underlying characteristics of the system "
				+ "you're working with. It's good to know that your communication paths may be unreliable and that "
				+ "there's latency in your messages just as it's good to know that your user went on vacation for "
				+ "two weeks and only just picked up your application from where they left off.");
		dontFightTheWeb.setUrl("http://blog.iandavis.com/2007/07/dont-fight-the-web/");
        entryRepository.save(dontFightTheWeb);
		
        Entry doingItRocaStyle = new Entry();
        doingItRocaStyle.setName("Resource-Oriented Client Architecture (ROCA)");
        doingItRocaStyle.setDescription("ROCA is an attempt to define a set of recommendations — independent of any "
				+ "particular framework, programming language, or tooling — that embodies the principles of what we "
				+ "consider to be good web application architecture. Its purpose is to serve as a reference, one that "
				+ "can be implemented as-is or be compared to other approaches to highlight diverging design decisions.");
		doingItRocaStyle.setUrl("http://roca-style.org/");
        entryRepository.save(doingItRocaStyle);
		
        Entry selfContainmentAsArt = new Entry();
        selfContainmentAsArt.setName("Self-Contained Systems (SCS)");
        selfContainmentAsArt.setDescription("The Self-contained System (SCS) approach is an architecture that focuses "
				+ "on a separation of the functionality into many independent systems, making the complete logical system "
				+ "a collaboration of many smaller software systems. This avoids the problem of large monoliths that "
				+ "grow constantly and eventually become unmaintainable. Over the past few years, we have seen its "
				+ "benefits in many mid-sized and large-scale projects.");
		selfContainmentAsArt.setUrl("http://scs-architecture.org/");
        entryRepository.save(selfContainmentAsArt);		
    }
}
