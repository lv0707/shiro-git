package com.itheima.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class UserController {

	/**
	 * 测试方法
	 */
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		System.out.println("UserController.hello()");
		System.out.println();
		return "ok";
	}
	
	/**
	 * 测试thymeleaf
	 */
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model){
		//把数据存入model
		model.addAttribute("name", "黑马程序员");
		//返回test.html
		return "test";
	}
	
	//跳转到add
	@RequestMapping("/add")
	public String add() {
		return "/user/add";
	}
	
	// 跳转到add
	@RequestMapping("/update")
	public String update() {
		return "/user/update";
	}
	
	// 跳转到add
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/login";
	}
	
	// 跳转到add
	@RequestMapping("/unAuth")
	public String unAuth() {
		return "/unAuth";
	}

	@RequestMapping("/login")
	public String login(String name, String password, Model model) {
		/**
		 * 使用Shiro编写认证操作
		 */
		// 获取Subject
		Subject subject = SecurityUtils.getSubject();
		// 封装用户数据
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
		// 3.执行登录方法
		try {
			subject.login(usernamePasswordToken);

			// 登录成功
			// 跳转到test.html
			return "redirect:/testThymeleaf";
		} catch (UnknownAccountException e) {
			// e.printStackTrace();
			// 登录失败:用户名不存在
			model.addAttribute("msg", "用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			// e.printStackTrace();
			// 登录失败:密码错误
			model.addAttribute("msg", "密码错误");
			return "login";
		}

	}

}
