package org.ouyikonggu.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.ProductClass;
import org.ouyikonggu.moudel.Slides;
import org.ouyikonggu.service.impl.ProductClassServiceImpl;
import org.ouyikonggu.service.impl.ProductServiceImpl;
import org.ouyikonggu.service.impl.SendMsgServiceImpl;
import org.ouyikonggu.service.impl.SlidesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class WebController{
	
	@Autowired
	ProductClassServiceImpl pcsi;
	@Autowired
	ProductServiceImpl psi;
	@Autowired	
	SlidesServiceImpl ssi;
	@Autowired	
	SendMsgServiceImpl smi;
	
	//获取数据库数据到H5页面
	@RequestMapping("getdata")
	@ResponseBody
	public  Map<String, Object> getData(HttpServletRequest req){
		System.out.println("响应成功");
		//获取分站Id，改标题，根据分站id，获取幻灯，产品，renturn List<Product>,List<Slide>
		String pcId=req.getParameter("pcId");
		System.out.println(pcId);
		ProductClass pc=pcsi.selectById(Integer.parseInt(pcId));
		List<Product> pList=psi.selectByPcId(Integer.parseInt(pcId));
		if (pList!=null) {
			for (Product product : pList) {
				System.out.println(product);
			}
		}

		List<Slides> sList=ssi.selectByScid(Integer.parseInt(pcId));
		if (sList!=null) {
			for (Slides slides : sList) {
				System.out.println(slides);
			}
		}
	
		Map<String,Object> map=new HashMap<>();
		map.put("msg", "获取数据成功！");
		map.put("pc", pc.getCName());
		map.put("pList", pList);
		map.put("sList", sList);
		return map;
	}
	
	
	//发送,验证及保存验证码
	@RequestMapping("sendandsave")
	@ResponseBody
	public Map sendAndSave(HttpServletRequest req) throws Exception{
		Map resultMap=new HashMap<>();
		String phone=req.getParameter("phone");
		String pid=req.getParameter("pid");  //获取的产品ID
		String op=req.getParameter("op");
		switch (op) {
			case "sendcode":
				System.out.println("获取到用户的电话号码是："+phone);
				try {
					resultMap=smi.send(phone);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.out.println("短信接口返回的信息："+resultMap.get("result")+"："+resultMap.get("msg")+"："+resultMap.get("code"));
				break;
	
			case "save":
				if (pid!=null) {
					Product clickP=psi.selectById(Integer.parseInt(pid));
					System.out.println(clickP);
					Map<String, String> saveMap=new HashMap<>();
					saveMap.put("phone", phone);
					saveMap.put("pid", pid);
					smi.savePhone(saveMap);
					HttpSession session=req.getSession();
					session.setAttribute("phone", phone);
					resultMap.put("url", clickP.getPUrl());
				}else {
					Map<String, String> saveMap=new HashMap<>();
					saveMap.put("phone", phone);
					smi.savePhoneWithoutPid(saveMap);;
					HttpSession session=req.getSession();
					session.setAttribute("phone", phone);
					resultMap.put("url", null);
				}
				break;
		}
		return resultMap;
	}
	
		//判断用户是否已经的注册保存过
		@RequestMapping("issaveduser")
		@ResponseBody
		public  Map<String, Object> isSaveduser(HttpServletRequest req){
			String pid=req.getParameter("pid");  //获取的产品ID
			HttpSession session=req.getSession();
			//获取session的Id  
	        String phone = (String) session.getAttribute("phone"); 
			Map<String,Object> map=new HashMap<>();
			if (phone!=null) {
				map.put("result", 1);
				if (pid!=null) {
					Map<String,String> phoneMap=new HashMap<>();
					phoneMap.put("phone", phone);
					phoneMap.put("pid",pid);
					smi.saveMember(phoneMap);
				}
			}else {
				map.put("result", 0);
			}
			return map;
		}
	
}
