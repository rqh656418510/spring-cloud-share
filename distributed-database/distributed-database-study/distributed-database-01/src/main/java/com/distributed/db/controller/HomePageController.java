package com.distributed.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/index.jsp")
	public String indexJsp() {
		return "index";
	}

	@RequestMapping("/index.html")
	public String indexHtml() {
		return "index";
	}

}
