package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	public UserController(UserRepository userRepository, HttpSession session) {
		this.userRepository = userRepository;
		this.session = session;
	}

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "auth/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@PostMapping("/auth/join")
	public String join(User user) {
		userRepository.save(user);
		return "redirect:/auth/loginForm";
	} 
	
	// RestController
	@PostMapping("/auth/login")
	public @ResponseBody String login(User user) {
		User userEntity =  userRepository.mLogin(user.getUsername(), user.getPassword());
		if(userEntity == null) {
			
			return Script.back("로그인 실패");
		}else {
			session.setAttribute("principal", userEntity);
			return Script.href("/");
		}
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}





