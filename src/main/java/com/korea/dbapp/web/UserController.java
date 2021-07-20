 package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;
import com.korea.dbapp.util.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;

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
	public @ResponseBody String login(@RequestBody User user) {
		try {
			User userEntity =  userRepository.mLogin(user.getUsername(), user.getPassword());
			if(userEntity == null) {
				
				return "fail";
			}else {
				session.setAttribute("principal", userEntity);
				return "ok";
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		

	}
	
	@GetMapping("/user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		// 1. 인증과 권한을 검사해야함.
		// 2. 세션값 사용하면 됨.
		return "user/updateForm";
	}
	
	@PostMapping("/user/{id}") // 원래는 Put으로 해야한다. 나중에 자바스크립트로 Put 요청하기!!
	public String update(@PathVariable int id, String password, String address) {
		
		// 공통관심사
		User principal = (User) session.getAttribute("principal");
		
		if(principal != null && id == principal.getId()) {
			User userEntity = userRepository.findById(id).get();
			userEntity.setPassword(password);
			userEntity.setAddress(address);
			userRepository.save(userEntity);
			session.setAttribute("principal", userEntity);
			return "redirect:/";
		}
		
		return "redirect:/auth/loginForm";	
	}
	

	@GetMapping("/juso")
	public String jusoRequest() {
		return "juso/jusoPopup";
	}
	
	@PostMapping("/juso")
	public String jusoResponse(String roadFullAddr, String inputYn, Model model) {
		System.out.println("주소 : "+roadFullAddr);
		model.addAttribute("roadFullAddr", roadFullAddr);
		model.addAttribute("inputYn", inputYn);
		return "juso/jusoPopup";
	}
}





