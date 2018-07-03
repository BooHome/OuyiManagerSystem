package org.ouyikonggu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ouyikonggu.moudel.Count;
import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.User;
import org.ouyikonggu.moudel.UserProduct;
import org.ouyikonggu.service.impl.CountServiceImpl;
import org.ouyikonggu.service.impl.ProductServiceImpl;
import org.ouyikonggu.util.CSVUtils;
import org.ouyikonggu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/count")
public class CountController {
	
	@Autowired
	CountServiceImpl csi;
	@Autowired
	ProductServiceImpl psi;
	
	//产品注册统计
	@RequestMapping("registerCount")
	public ModelAndView registerCount(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		ModelAndView mav = new ModelAndView();
		
		Map defultR=new HashMap<>();
		defultR.put("startTime", DateUtil.getDate(new Date()));
		defultR.put("CActivate", 1);
		
		
		// 导出下载文件数据
		List<Count> dcList=csi.queryRegist(defultR);
		request.getSession().setAttribute("download", dcList);
		  //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);  
	    
		List<Count> cList=csi.queryRegist(defultR);
		for (Count count : cList) {
			System.out.println(count);
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(cList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/registerCount");
		return mav;
	}
	
	//产品注册详情
	@RequestMapping("registerPhone")
	public ModelAndView registerPhone(HttpServletRequest req){
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		String pTitle=req.getParameter("pTitle");
		Product rProduct=psi.selectByTitle(pTitle);
		
		// 导出下载文件数据
		List<Member> dmList=csi.selectMemberByPid(rProduct.getId());
		req.getSession().setAttribute("download", dmList);
	    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);  
	    
		List<Member> mList=csi.selectMemberByPid(rProduct.getId());
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(mList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("pTitle",pTitle);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/registerPhone");
		return mav;
	}
	
	// 查询产品
	@RequestMapping(value = "searchregister", method = RequestMethod.GET)
	public ModelAndView searchRegister(HttpServletRequest requst) {
		//进来的查询条件
		String name = requst.getParameter("keyName");
		String startTime = requst.getParameter("startTime");
		String endTime = requst.getParameter("endTime");
		
		if (name.equals("")&&startTime.equals("") && endTime.equals("")) {
			startTime=DateUtil.getDate(new Date());
		}
		Map searchR=new HashMap<>();
		searchR.put("startTime", startTime);
		searchR.put("endTime", endTime);
		searchR.put("pTitle", name);
		
		// 导出下载文件数据
		List<Count> dcList=csi.queryRegist(searchR);
		requst.getSession().setAttribute("download", dcList);
		
	    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(1,19);  
	    
		List<Count> cList=csi.queryRegist(searchR);
		for (Count count : cList) {
			System.out.println(count);
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(cList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchR",searchR);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/registerCount");
		return mav;
	}
	
	//用户统计
	@RequestMapping("userCount")
	public ModelAndView userCount(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		ModelAndView mav = new ModelAndView();
		// 导出下载文件数据
		List<User> duList=csi.selectMember();
		request.getSession().setAttribute("download", duList);
		 //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);  
		List<User> uList=csi.selectMember();
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(uList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/userCount");
		return mav;
	}
	
	// 查询用户
	@RequestMapping(value = "searchuser", method = RequestMethod.GET)
	public ModelAndView searchUser(HttpServletRequest requst) {
		// 进来的查询条件
		String name = requst.getParameter("keyName");
		String startTime = requst.getParameter("startTime");
		String endTime = requst.getParameter("endTime");

		Map searchU = new HashMap<>();
		searchU.put("startTime", startTime);
		searchU.put("endTime", endTime);
		searchU.put("uTel", name);
		
		// 导出下载文件数据
		List<User> duList = csi.queryUser(searchU);
		requst.getSession().setAttribute("download", duList);
		 //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(1,19);  
	    
		List<User> uList = csi.queryUser(searchU);
		for (User user : uList) {
			System.out.println(user);
		}

		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(uList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("searchU",searchU);
		mav.setViewName("count/userCount");
		return mav;
	}
	
	// 产品注册详情
	@RequestMapping("userProduct")
	public ModelAndView userProduct(HttpServletRequest req) {
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		
	    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);  
		String uTel=req.getParameter("uTel");
		System.out.println(uTel);
		List<UserProduct> pList=csi.selectMemberByPhone(uTel);
		for (UserProduct userProduct : pList) {
			System.out.println(userProduct);
		}
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(pList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("uTel",uTel);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/userProduct");
		return mav;
	}
	
	 /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("downloadFile")  
    public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	List result=(List) request.getSession().getAttribute("download");
    	List<Count> cList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			cList.add((Count) result.get(i));
		}

    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "产品名称");  
        map.put("2", "产品访问量（PV）");  
        map.put("3", "访问用户量（UV）");  
        map.put("4", "数据统计时间");  
             		
        String fileds[] = new String[] { "countName", "pV" , "uV", "cAddTime"};// 设置列英文名（也就是实体类里面对应的列名）  
        
        File file = CSVUtils.createCSVFile(cList, fileds, map, outPutPath,  
        		filename); 
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(file));  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }
        bis.close();
        out.close(); 
        
        //删除生成的文件
        deleteFile(file.getPath());
    }
    
    /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("downloadFile2")  
    public void downloadFile2(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	List result=(List) request.getSession().getAttribute("download");
    	List<User> cList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			cList.add((User) result.get(i));
		}

    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "用户手机号码");  
        map.put("2", "访问产品数量");  
        map.put("3", "首次访问时间");  
             		
        String fileds[] = new String[] { "uTel", "pCount" , "uAddTime"};// 设置列英文名（也就是实体类里面对应的列名）  
        
        File file = CSVUtils.createCSVFile(cList, fileds, map, outPutPath,  
        		filename); 
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(file));  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }
        bis.close();
        out.close(); 
        
        //删除生成的文件
        deleteFile(file.getPath());
    }

    /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("downloadFile3")  
    public void downloadFile3(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	List result=(List) request.getSession().getAttribute("download");
    	List<Member> cList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			cList.add((Member) result.get(i));
		}

    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "访问用户的手机号码");  
        map.put("2", "最后一次访问的时间");  
             		
        String fileds[] = new String[] { "mTel", "mAddTime"};// 设置列英文名（也就是实体类里面对应的列名）  
        
        File file = CSVUtils.createCSVFile(cList, fileds, map, outPutPath,  
        		filename); 
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(file));  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }
        bis.close();
        out.close(); 
        
        //删除生成的文件
        deleteFile(file.getPath());
    }
    
	private void deleteFile(String path) {
		File deleteFile=new File(path);
        if (deleteFile!=null&&deleteFile.exists()) {
        	System.out.println(deleteFile.delete());
        	System.out.println("删除成功！");
		}
	}  
}
