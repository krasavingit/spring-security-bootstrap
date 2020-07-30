package ru.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "/login.html";
	}

}
