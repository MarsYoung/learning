package com.marsyoung.learning.web.springmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoAction extends BaseAction {

	@RequestMapping(value = "/selectUser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView selectUser(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/jsp/selectUser");
		mv.addObject("Result","OK");
		mv.addObject("Message","获取成功");
		return mv;
	}
}
