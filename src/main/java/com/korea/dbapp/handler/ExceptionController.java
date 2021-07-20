package com.korea.dbapp.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Controller
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String test1(Exception e, Model model) {
		model.addAttribute("msg", e.getMessage());
		return "error";
	}
	
	
}
