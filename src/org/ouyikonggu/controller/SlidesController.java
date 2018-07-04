package org.ouyikonggu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.ouyikonggu.moudel.ProductClass;
import org.ouyikonggu.moudel.Slides;
import org.ouyikonggu.service.impl.ProductClassServiceImpl;
import org.ouyikonggu.service.impl.SlidesServiceImpl;
import org.ouyikonggu.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/slides")
public class SlidesController {
	@Autowired
	SlidesServiceImpl ssi;
	@Autowired
	ProductClassServiceImpl pcs;
	
	//幻灯管理
	@RequestMapping("slidesManager")
	public ModelAndView slidesManager(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		ModelAndView mav = new ModelAndView();
		
		// 导出下载文件数据
		List<Slides> dsList=ssi.queryList(null);
		request.getSession().setAttribute("download", dsList);
//		引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);
	    
		List<Slides> sList=ssi.queryList(null);
		for (Slides slides : sList) {
			System.out.println(slides);
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(sList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("slides/slidesManager");
		return mav;
	}
			
	//新增幻灯页面
	@RequestMapping("addSlidesPage")
	public ModelAndView addSlidesPage(){
		ModelAndView mav = new ModelAndView();
		List<ProductClass> pClasses = pcs.queryList(null);
		mav.addObject("pClasses", pClasses);
		mav.setViewName("slides/addSlides");
		return mav;
	}
			
	//新增幻灯
	@RequestMapping(value="addslides",method=RequestMethod.POST)
	public void addSlides(@RequestParam("sTitleImg") CommonsMultipartFile file,HttpServletRequest requst,HttpServletResponse response) throws Exception{
		System.out.println("上传过来的图片"+file.getOriginalFilename());
		String sTitle=requst.getParameter("sTitle");
		String sSort=requst.getParameter("sSort");
		String scid=requst.getParameter("scid");
		Slides slide=new Slides();
		slide.setScid(Integer.parseInt(scid));
		slide.setProductClass(pcs.selectById(Integer.parseInt(scid)));
		slide.setSTitle(sTitle);
		slide.setSSort(sSort);
		System.out.println("获取过来的数据："+slide);
		
		//上传选择图片
		String webPath=requst.getSession().getServletContext().getRealPath("data/slide/");
		String fileName=new Date().getTime() + file.getOriginalFilename();
		String path = webPath + fileName;
		File uploadFile=new File(path);
        file.transferTo(uploadFile);
        System.out.println("图片上传成功:"+path);
        
        slide.setSTitleImg("http://sj.ouyisms.com/OuyiManagerSystem/data/slide/"+fileName);
		int result=ssi.add(slide);
		if(result>0) {
			requst.setAttribute("addStr", "true");
			requst.getRequestDispatcher("slidesManager?pageNum=1").forward(requst, response);
		}else {
			requst.setAttribute("addStr", "false");
			requst.getRequestDispatcher("addSlidesPage").forward(requst, response);		
		}
	}

	//查询页面
	@RequestMapping(value="searchslides",method=RequestMethod.GET)
	public ModelAndView searchSlides(HttpServletRequest requst){
		String name=requst.getParameter("keyName");
		ModelAndView mav = new ModelAndView();
		Slides sd=new Slides();
		sd.setSTitle(name);
		
		// 导出下载文件数据
		List<Slides> dsList=ssi.queryList(sd);
		requst.getSession().setAttribute("download", dsList);
		//	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(1,19);  
		List<Slides> sList=ssi.queryList(sd);
		for (Slides slides : sList) {
			System.out.println(slides);
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(sList,19);
		mav.addObject("sd",sd);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("slides/slidesManager");
		return mav;
	}
			
	//删除幻灯
	@RequestMapping(value="deleteslides",method=RequestMethod.GET)
	public void deleteSlides(@RequestParam("s")int deleteSId,HttpServletRequest requst, HttpServletResponse response) throws Exception{
		Slides sd=new Slides();
		sd=ssi.selectById(deleteSId);
		
		//删除已经上传的文件
		String webPath=requst.getSession().getServletContext().getRealPath("data/slide/");
		String fileName=StringUtils.substringAfter(sd.getSTitleImg(), "http://sj.ouyisms.com/OuyiManagerSystem/data/slide/");
		System.out.println(webPath+fileName);
		File deleteFile=new File(webPath+fileName);
		if (deleteFile!=null&&deleteFile.exists()) {
			deleteFile.delete();
		}

		List<Slides> list=new ArrayList<>();
		list.add(sd);
		int result=ssi.delete(list);
		response.sendRedirect("slidesManager?pageNum=1");
	}
			
	//修改页面跳转
	@RequestMapping(value="updateSlidesJump",method=RequestMethod.GET)
	public ModelAndView updateSlidesJump(@RequestParam("s")int updateSId,HttpServletResponse response) throws Exception{
		Slides updateS=ssi.selectById(updateSId);
		System.out.println(updateS);
		List<ProductClass> pClasses = pcs.queryList(null);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pClasses", pClasses);
		mav.addObject("updateS", updateS);
		mav.setViewName("slides/updateSlides");
		return mav;
	}
			
	//修改幻灯
	@RequestMapping(value="updateslides",method=RequestMethod.POST)
	public void updateSlides(@RequestParam("sTitleImg") CommonsMultipartFile file,HttpServletRequest requst,HttpServletResponse response) throws Exception{
		System.out.println("上传过来的图片"+file.getOriginalFilename());
		String id=requst.getParameter("id");
		String sTitle=requst.getParameter("sTitle");
		String sSort=requst.getParameter("sSort");
		String scid=requst.getParameter("scid");
		String orgImg=requst.getParameter("orgImg");
		Slides slide=new Slides();
		slide.setId(Integer.parseInt(id));
		slide.setScid(Integer.parseInt(scid));
		slide.setProductClass(pcs.selectById(Integer.parseInt(scid)));
		slide.setSTitle(sTitle);
		slide.setSSort(sSort);
		
		if (file.getSize()==0) {
			slide.setSTitleImg(orgImg);
		}else{			
			//上传选择图片
			String webPath=requst.getSession().getServletContext().getRealPath("data/slide/");
			String fileName=new Date().getTime() + file.getOriginalFilename();
			String path = webPath + fileName;
			File uploadFile=new File(path);
	        file.transferTo(uploadFile);
	        System.out.println("图片上传成功:"+path);
	        
	        slide.setSTitleImg("http://sj.ouyisms.com/OuyiManagerSystem/data/slide/"+fileName);
		}
		int result=ssi.update(slide);
		
		if(result>0) {
			requst.setAttribute("updateStr", "true");
			requst.getRequestDispatcher("slidesManager?pageNum=1").forward(requst, response);
		}else {
			requst.setAttribute("updateStr", "false");
			requst.getRequestDispatcher("updateSlidesJump?"+id).forward(requst, response);		
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
    	List<Slides> sList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			sList.add((Slides) result.get(i));
		}
    	
    	for (Slides slides : sList) {
			slides.setPcName(slides.getProductClass().getCName());
		}

    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "所属分站");  
        map.put("2", "名称");  
        map.put("3", "图片");  
        map.put("4", "时间");  
             		
        String fileds[] = new String[] { "pcName", "sTitle" , "sTitleImg", "sAddTime"};// 设置列英文名（也就是实体类里面对应的列名）  
        
        File file = CSVUtils.createCSVFile(sList, fileds, map, outPutPath,  
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
