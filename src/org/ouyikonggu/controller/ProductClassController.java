package org.ouyikonggu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ouyikonggu.moudel.ProductClass;
import org.ouyikonggu.service.impl.ProductClassServiceImpl;
import org.ouyikonggu.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/productClass")
public class ProductClassController {
	@Autowired
	ProductClassServiceImpl pcs;
	
	//分站管理
	@RequestMapping("classifyManager")
	public ModelAndView classifyManager(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		ModelAndView mav = new ModelAndView();
		//导出下载文件数据
		List<ProductClass> dpClasses=pcs.queryList(null);
		request.getSession().setAttribute("download", dpClasses);
		
	    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);  
		List<ProductClass> pClasses=pcs.queryList(null);
		for (ProductClass productClass : pClasses) {
			System.out.println(productClass);
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(pClasses,19);  
        
		
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("productClass/classifyManager");
		return mav;
	}
	
	//添加分站页面
	@RequestMapping("addClassifyPage")
	public ModelAndView addClassifyPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productClass/addClassify");
		
		return mav;
	}
	
	// 添加分站
	@RequestMapping(value = "addClassify", method = RequestMethod.POST)
	public void addClassify(@RequestParam("cName") String name, @RequestParam("cSort") String sort,
			HttpServletRequest requst, HttpServletResponse response) throws Exception {
		ProductClass insertPc = new ProductClass();
		insertPc.setCName(name);
		insertPc.setCSort(Integer.parseInt(sort));
		System.out.println(insertPc);
		int result = pcs.add(insertPc);
		if (result > 0) {
			requst.setAttribute("addStr", "true");
			requst.getRequestDispatcher("classifyManager?pageNum=1").forward(requst, response);
		} else {
			requst.setAttribute("addStr", "false");
			requst.getRequestDispatcher("addClassifyPage").forward(requst, response);
		}
	}
			
	// 查询分站
	@RequestMapping(value = "searchclassify", method = RequestMethod.GET)
	public ModelAndView searchClassify(HttpServletRequest requst) {
		String name = requst.getParameter("keyName");
		ModelAndView mav = new ModelAndView();
		ProductClass serchPc = new ProductClass();
		serchPc.setCName(name);
		System.out.println(serchPc);

		//导出下载文件数据
		List<ProductClass> dpClasses=pcs.queryList(serchPc);
		requst.getSession().setAttribute("download", dpClasses);
				
	    //	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(1,19);  
		List<ProductClass> pClasses = pcs.queryList(serchPc);
		for (ProductClass productClass : pClasses) {
			System.out.println(productClass);
		}
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(pClasses,19);
	    
		mav.addObject("serchPc", serchPc);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("productClass/classifyManager");
		return mav;
	}

	// 删除分站
	@RequestMapping(value = "deleteclassify", method = RequestMethod.GET)
	public void deleteClassify(@RequestParam("pc") int deletePcId, HttpServletResponse response,HttpServletRequest requst) throws Exception {
		ProductClass deletePc = new ProductClass();
		deletePc.setId(deletePcId);
		List<ProductClass> list = new ArrayList<>();
		list.add(deletePc);
		int result = pcs.delete(list);
		if (result>0) {
			requst.setAttribute("delStr", "true");
			requst.getRequestDispatcher("classifyManager?pageNum=1").forward(requst, response);
		}else{
			requst.setAttribute("delStr", "false");
			requst.getRequestDispatcher("classifyManager?pageNum=1").forward(requst, response);
		}
	}

	//修改分站跳转
	@RequestMapping(value = "updateClassifyJump", method = RequestMethod.GET)
	public ModelAndView updateClassifyJump(@RequestParam("pc") int updatePcId, HttpServletResponse response)
			throws Exception {
		ProductClass updatePc = pcs.selectById(updatePcId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("updatePc", updatePc);
		mav.setViewName("productClass/updateClassify");
		return mav;
	}

	//修改分站
	@RequestMapping(value = "updateclassify", method = RequestMethod.POST)
	public void updateClassify(@RequestParam("pcId") String id, @RequestParam("cName") String name,
			@RequestParam("cSort") String sort, HttpServletRequest requst, HttpServletResponse response)
			throws Exception {
		ProductClass updatePc = new ProductClass();
		updatePc.setId(Integer.parseInt(id));
		updatePc.setCName(name);
		updatePc.setCSort(Integer.parseInt(sort));

		System.out.println(updatePc);
		int result = pcs.update(updatePc);

		if (result > 0) {
			requst.setAttribute("updateStr", "true");
			requst.getRequestDispatcher("classifyManager?pageNum=1").forward(requst, response);
		} else {
			requst.setAttribute("updateStr", "false");
			requst.getRequestDispatcher("updateClassifyJump?"+id).forward(requst, response);
		}
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
    	List<ProductClass> pcList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			pcList.add((ProductClass) result.get(i));
		}
    	for (ProductClass productClass : pcList) {
			productClass.setCUrl("http://sj.ouyisms.com/WebH5/index.html?pcId="+productClass.getId());
		}
    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "名称");  
        map.put("2", "排序");  
        map.put("3", "推广地址");  
             		
        String fileds[] = new String[] { "cName", "cSort" , "cUrl"};// 设置列英文名（也就是实体类里面对应的列名）  
        
        File file = CSVUtils.createCSVFile(pcList, fileds, map, outPutPath,  
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
