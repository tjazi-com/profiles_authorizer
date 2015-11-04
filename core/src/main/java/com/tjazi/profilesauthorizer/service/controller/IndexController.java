package com.tjazi.profilesauthorizer.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    public String displayStatusPage() {
        log.debug("Requesting status page...");

        return "status";
    }
}
