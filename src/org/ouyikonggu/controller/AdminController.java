package org.ouyikonggu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ouyikonggu.moudel.Count;
import org.ouyikonggu.service.CountService;
import org.ouyikonggu.service.impl.AdminServiceimpl;
import org.ouyikonggu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("")
public class AdminController {
	@Autowired
	AdminServiceimpl adminservice;
	
	@Autowired
	CountService countService;
	
	//登录
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest requst){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		HttpSession session=requst.getSession();
		session.setAttribute("user", "admin");
		return mav;
	}
	
	//主页
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest requst){
		ModelAndView mav = new ModelAndView();
		HttpSession session=requst.getSession();
		System.out.println(session.getAttribute("user"));
	    if(session.getAttribute("user") == null) {  
	        mav.addObject("tip","<script>alert('请先登陆');window.location.href='login'</script>");
	    } 
		mav.setViewName("index");
		return mav;
	}
	
	//欢迎页
	@RequestMapping("welcome")
	public ModelAndView welcome(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
		return mav;
	}
	
	//退出登录
	@RequestMapping("loginout")
	public void loginOut(HttpServletRequest requst,HttpServletResponse response) throws Exception{
		HttpSession session=requst.getSession();
		session.removeAttribute("user");
		System.out.println("退出登录之后seeion的值："+session.getAttribute("user"));
		requst.getRequestDispatcher("login").forward(requst, response);
	}
	
	//图表测试
		@RequestMapping("ajax/getChartData")
		@ResponseBody
		public String getChartData(@RequestParam(value = "startTime", defaultValue = "") String startTime,
				@RequestParam(value = "endTime", defaultValue = "")String endTime) throws Exception{
			Map searchR=new HashMap();
			if(startTime==null || "".trim().equals(startTime)) {
				searchR.put("startTime", DateUtil.getDate(new Date()));
			}else {
				searchR.put("startTime", startTime);
				searchR.put("endTime", endTime);
			}
			
			List<Count> cList= countService.queryRegist(searchR);
			JSONArray array = JSONArray.fromObject(cList);  
			String result = array.toString();
			return result;
		}
}
