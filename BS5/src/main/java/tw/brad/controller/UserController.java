package tw.brad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.model.ResponseUser;
import tw.brad.model.User;
import tw.brad.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/isexist/{account}")
	public ResponseUser isExist(@PathVariable String account) {
		return userService.isExistUser(account);
	}
	
	@PostMapping("/register")
	public ResponseUser reg(@RequestBody User user) {
		return userService.regUser(user);
	}
	
	@PostMapping("/login")
	public ResponseUser login(@RequestBody User user) {
		return userService.loginUser(user);
	}
	
}
