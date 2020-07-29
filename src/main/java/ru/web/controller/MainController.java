package ru.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

//	private final UserService userService;
//
//	@Autowired
//	public MainController(UserService userService) {
//		this.userService = userService;
//	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "/hello.html";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "/login.html";
	}

//	@PostMapping(value = "loginCustom")
//	public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, Model model) {
//		User user = (User) userService.loadUserByUsername(username);
//		if (user.getAuthorities().contains(Role.ROLE_ADMIN)) {
//			return "redirect:/admin/panel";
//		} else {
//			return "myinfo";
//		}
//	}
}

//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	public String loginPage(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){
//		User user = (User) userService.loadUserByUsername(username);
//		if(user.getAuthorities().contains(Role.ROLE_ADMIN)){
//			return "redirect:/admin/panel";
//		} else {
//			return "myinfo";
//		}
//	}