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
import org.ouyikonggu.moudel.SapCount;
import org.ouyikonggu.moudel.StandAloneProduct;
import org.ouyikonggu.service.SaProductService;
import org.ouyikonggu.util.CSVUtils;
import org.ouyikonggu.util.DateUtil;
import org.ouyikonggu.util.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/saproduct")
public class SaproductController {
	
	@Autowired
	SaProductService spsi;
	
	//独立产品管理
	@RequestMapping("saproductManager")
	public ModelAndView saproductManager(HttpServletRequest request){
			int pageNum=Integer.parseInt(request.getParameter("pageNum"));		

			// 导出下载文件数据
			List<StandAloneProduct> dsapList=spsi.queryList(null);
			for (StandAloneProduct standAloneProduct : dsapList) {
				standAloneProduct.setViewUrl("http://sj.ouyisms.com/OuyiManagerSystem/saproduct/standProductJump?sapId="+standAloneProduct.getId());
			}
			request.getSession().setAttribute("download", dsapList);
			
		    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
		    PageHelper.startPage(pageNum,12);  
			List<StandAloneProduct> sapList=spsi.queryList(null);
			for (StandAloneProduct standAloneProduct : sapList) {
				System.out.println(standAloneProduct);
			}
			
			//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
		    PageInfo pageInfo = new PageInfo<>(sapList,12);  
			ModelAndView mav = new ModelAndView();
			mav.addObject("pageInfo",pageInfo);
			mav.setViewName("standproduct/saproductManager");
			return mav;
	}
	
	//添加独立页面
	@RequestMapping("addSaproductPage")
	public ModelAndView addSaproductPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("standproduct/addSaproduct");
		
		return mav;
	}
	
	// 添加独立产品
	@RequestMapping(value = "addSaproduct", method = RequestMethod.POST)
	public void addSaproduct(HttpServletRequest requst, HttpServletResponse response) throws Exception {
		String sapName=requst.getParameter("sapName");
		String url=requst.getParameter("url");
		String sapActivate=requst.getParameter("sapActivate");
		
		StandAloneProduct sap=new StandAloneProduct();
		sap.setSapName(sapName);
		sap.setUrl(url);
		sap.setSapActivate(Integer.parseInt(sapActivate));
		
		int result = spsi.add(sap);
		if (result > 0) {
			requst.setAttribute("addStr", "true");
			requst.getRequestDispatcher("saproductManager?pageNum=1").forward(requst, response);
		} else {
			requst.setAttribute("addStr", "false");
			requst.getRequestDispatcher("addSaproductPage").forward(requst, response);
		}
	}
	
	// 查询独立产品
	@RequestMapping(value = "searchSaproduct", method = RequestMethod.GET)
	public ModelAndView searchSaproduct(HttpServletRequest requst) {
		String name = requst.getParameter("keyName");
		ModelAndView mav = new ModelAndView();

		StandAloneProduct searchSap = new StandAloneProduct();
		searchSap.setSapName(name);
		
		System.out.println(searchSap);
		// 导出下载文件数据
		List<StandAloneProduct> dsapList=spsi.queryList(searchSap);
		for (StandAloneProduct standAloneProduct : dsapList) {
			standAloneProduct.setViewUrl("http://sj.ouyisms.com/OuyiManagerSystem/saproduct/standProductJump?sapId="+standAloneProduct.getId());
		}
		requst.getSession().setAttribute("download", dsapList);
		
		// 引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录
		PageHelper.startPage(1, 12);
		List<StandAloneProduct> sapList = spsi.queryList(searchSap);
		for (StandAloneProduct standAloneProduct : sapList) {
			System.out.println("湿哒哒所"+standAloneProduct);
		}
		// 使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
		PageInfo pageInfo = new PageInfo<>(sapList, 12);

		mav.addObject("searchSap", searchSap);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("standproduct/saproductManager");
		return mav;
	}

	// 删除独立产品
	@RequestMapping(value = "deleteSaproduct", method = RequestMethod.GET)
	public void deleteSaproduct(@RequestParam("sapid") int deleteSapId, HttpServletResponse response) throws Exception {
		StandAloneProduct sap=new StandAloneProduct();
		sap.setId(deleteSapId);
		List<StandAloneProduct> list = new ArrayList<>();
		list.add(sap);
		int result = spsi.delete(list);
		response.sendRedirect("saproductManager?pageNum=1");
	}
	
	// 修改独立产品跳转
	@RequestMapping(value = "updateSaproductJump", method = RequestMethod.GET)
	public ModelAndView updateSaproductJump(@RequestParam("sapid") int updateSapid, HttpServletResponse response)
			throws Exception {
		StandAloneProduct updateSap=spsi.selectById(updateSapid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("updateSap", updateSap);
		mav.setViewName("standproduct/updateSaproduct");
		return mav;
	}

	// 修改独立产品
	@RequestMapping(value = "updateSaproduct", method = RequestMethod.POST)
	public void updateSaproduct(@RequestParam("sapid") int updateSapid,HttpServletRequest requst, HttpServletResponse response)
			throws Exception {
		String sapName=requst.getParameter("sapName");
		String url=requst.getParameter("url");
		String sapActivate=requst.getParameter("sapActivate");

		StandAloneProduct sap=new StandAloneProduct();
		sap.setId(updateSapid);
		sap.setSapName(sapName);
		sap.setUrl(url);
		sap.setSapActivate(Integer.parseInt(sapActivate));
		
		System.out.println(sap);
		int result = spsi.update(sap);

		if (result > 0) {
			requst.setAttribute("updateStr", "true");
			requst.getRequestDispatcher("saproductManager?pageNum=1").forward(requst, response);
		} else {
			requst.setAttribute("updateStr", "false");
			requst.getRequestDispatcher("updateSaproductJump?"+updateSapid).forward(requst, response);
		}
	}
	
	// 独立产品统计
	@RequestMapping("standProductCount")
	public ModelAndView standProductCount(HttpServletRequest request) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		ModelAndView mav = new ModelAndView();

		Map defultR = new HashMap<>();
		defultR.put("startTime", DateUtil.getDate(new Date()));
		defultR.put("CActivate", 1);
		
		// 导出下载文件数据
		List<SapCount> dcountList=spsi.querySaproduct(defultR);
		for (SapCount sapCount : dcountList) {
			sapCount.setSpaName(sapCount.getSap().getSapName());
			sapCount.setUrl(sapCount.getSap().getUrl());
		}
		request.getSession().setAttribute("download", dcountList);
		
		// 引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录
		PageHelper.startPage(pageNum, 19);
		List<SapCount> countList=spsi.querySaproduct(defultR);
		// 使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
		PageInfo pageInfo = new PageInfo<>(countList, 19);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("standproduct/standProductCount");
		return mav;
	}
	
	// 独立产品跳转
	@RequestMapping("standProductJump")
	public void standProductJump(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String sapId=request.getParameter("sapId");
		StandAloneProduct jumpSap=spsi.selectById(Integer.parseInt(sapId)); 
		String ipAdress=HttpRequest.getIpAdrress(request);
		
		Member sapMember=new Member();
		sapMember.setMProductID(Integer.parseInt(sapId));
		sapMember.setMTel(ipAdress);
		
		int result=spsi.saveSapUser(sapMember);
		
		response.sendRedirect(jumpSap.getUrl());
	}
	
	// 查询独立产品统计
	@RequestMapping(value = "searchStandProduct", method = RequestMethod.GET)
	public ModelAndView searchStandProduct(HttpServletRequest requst) {
		int pageNum = Integer.parseInt(requst.getParameter("pageNum"));
		//进来的查询条件
		String name = requst.getParameter("keyName");
		String startTime = requst.getParameter("startTime");
		String endTime = requst.getParameter("endTime");
		
		Map searchR=new HashMap<>();
		searchR.put("startTime", startTime);
		searchR.put("endTime", endTime);
		searchR.put("countName", name);
		searchR.put("CActivate", 1);
		
		// 导出下载文件数据
		List<SapCount> dcountList=spsi.querySaproduct(searchR);
		for (SapCount sapCount : dcountList) {
			sapCount.setSpaName(sapCount.getSap().getSapName());
			sapCount.setUrl(sapCount.getSap().getUrl());
		}
		requst.getSession().setAttribute("download", dcountList);
	    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);  
	    
	    List<SapCount> countList=spsi.querySaproduct(searchR);
	    
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(countList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchR",searchR);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("standproduct/searchSproductCount");
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
    	List<StandAloneProduct> cList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			cList.add((StandAloneProduct) result.get(i));
		}

    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "独立产品编号");  
        map.put("2", "独立产品名称");  
        map.put("3", "独立产品地址");  
        map.put("4", "访问地址");  
             		
        String fileds[] = new String[] { "id", "sapName" , "url", "viewUrl"};// 设置列英文名（也就是实体类里面对应的列名）  
        
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
    	List<SapCount> cList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			cList.add((SapCount) result.get(i));
		}

    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "产品名称");  
        map.put("2", "产品链接");  
        map.put("3", "产品访问量（PV）");  
        map.put("4", "访问用户量（UV）");  
        map.put("5", "数据统计时间");  
             		
        String fileds[] = new String[] { "spaName", "url" , "PV" , "UV", "CAddTime"};// 设置列英文名（也就是实体类里面对应的列名）  
        
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
		}
	}  
}
