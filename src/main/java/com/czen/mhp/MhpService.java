/*
 * Copyright 2006-2016 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package com.czen.mhp;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czen.portalframework.Attribute;
import com.czen.portalframework.Module;
import com.czen.portalframework.Page;
import com.czen.portalframework.Deploy;
import com.czen.portalframework.PortalService;

@EnableAutoConfiguration
@RestController
public class MhpService {
	private static final Logger logger = Logger.getLogger(MhpService.class
			.getName());

	@RequestMapping("/getPage/{pageType}/{memberType}/{memberId}")
	public Page getPage(@PathVariable("pageType") String pageType,
			@PathVariable("memberType") String memberType,
			@PathVariable("memberId") Long memberId) {
		logger.log(Level.INFO, "Page requested: " + pageType + memberType
				+ " for memberId: " + memberId);
		PortalService ps = PortalService.getInstance();
		Page page = ps.getPage(pageType + memberType + "Page");
		// Custom page processing / attributes
		page.addAttribute(new Attribute("page.attribute.name2", "Long", new Long(100)));
        Module module = ps.getModule(page, "module-1");
		module.addAttribute(new Attribute("module1.heading", "String", "My Module 1"));
        return page;
    }

	@RequestMapping("/runDeploy/{deployId}")
	public Deploy runDeploy(@PathVariable("deployId") String deployId) {
		logger.log(Level.INFO, "Deploy requested: " + 
				 " for deployId: " + deployId);
		PortalService ps = PortalService.getInstance();
		Deploy deploy = ps.runDeploy(deployId);
        return deploy;
    }

	public static void main(String[] args) throws Exception {
        SpringApplication.run(MhpService.class, args);
    }
}