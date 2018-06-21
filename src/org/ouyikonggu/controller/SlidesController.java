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
	
	//�õƹ���
	@RequestMapping("slidesManager")
	public ModelAndView slidesManager(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		ModelAndView mav = new ModelAndView();
		
		// ���������ļ�����
		List<Slides> dsList=ssi.queryList(null);
		request.getSession().setAttribute("download", dsList);
//		�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(pageNum,19);
	    
		List<Slides> sList=ssi.queryList(null);
		for (Slides slides : sList) {
			System.out.println(slides);
		}
		
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(sList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("slides/slidesManager");
		return mav;
	}
			
	//�����õ�ҳ��
	@RequestMapping("addSlidesPage")
	public ModelAndView addSlidesPage(){
		ModelAndView mav = new ModelAndView();
		List<ProductClass> pClasses = pcs.queryList(null);
		mav.addObject("pClasses", pClasses);
		mav.setViewName("slides/addSlides");
		return mav;
	}
			
	//�����õ�
	@RequestMapping(value="addslides",method=RequestMethod.POST)
	public void addSlides(@RequestParam("sTitleImg") CommonsMultipartFile file,HttpServletRequest requst,HttpServletResponse response) throws Exception{
		System.out.println("�ϴ�������ͼƬ"+file.getOriginalFilename());
		String sTitle=requst.getParameter("sTitle");
		String sSort=requst.getParameter("sSort");
		String scid=requst.getParameter("scid");
		Slides slide=new Slides();
		slide.setScid(Integer.parseInt(scid));
		slide.setProductClass(pcs.selectById(Integer.parseInt(scid)));
		slide.setSTitle(sTitle);
		slide.setSSort(sSort);
		System.out.println("��ȡ���������ݣ�"+slide);
		
		//�ϴ�ѡ��ͼƬ
		String webPath=requst.getSession().getServletContext().getRealPath("data/slide/");
		String fileName=new Date().getTime() + file.getOriginalFilename();
		String path = webPath + fileName;
		File uploadFile=new File(path);
        file.transferTo(uploadFile);
        System.out.println("ͼƬ�ϴ��ɹ�:"+path);
        
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

	//��ѯҳ��
	@RequestMapping(value="searchslides",method=RequestMethod.GET)
	public ModelAndView searchSlides(HttpServletRequest requst){
		String name=requst.getParameter("keyName");
		ModelAndView mav = new ModelAndView();
		Slides sd=new Slides();
		sd.setSTitle(name);
		
		// ���������ļ�����
		List<Slides> dsList=ssi.queryList(sd);
		requst.getSession().setAttribute("download", dsList);
		//	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(1,19);  
		List<Slides> sList=ssi.queryList(sd);
		for (Slides slides : sList) {
			System.out.println(slides);
		}
		
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(sList,19);
		mav.addObject("sd",sd);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("slides/slidesManager");
		return mav;
	}
			
	//ɾ���õ�
	@RequestMapping(value="deleteslides",method=RequestMethod.GET)
	public void deleteSlides(@RequestParam("s")int deleteSId,HttpServletRequest requst, HttpServletResponse response) throws Exception{
		Slides sd=new Slides();
		sd=ssi.selectById(deleteSId);
		
		//ɾ���Ѿ��ϴ����ļ�
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
			
	//�޸�ҳ����ת
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
			
	//�޸Ļõ�
	@RequestMapping(value="updateslides",method=RequestMethod.POST)
	public void updateSlides(@RequestParam("sTitleImg") CommonsMultipartFile file,HttpServletRequest requst,HttpServletResponse response) throws Exception{
		System.out.println("�ϴ�������ͼƬ"+file.getOriginalFilename());
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
			//�ϴ�ѡ��ͼƬ
			String webPath=requst.getSession().getServletContext().getRealPath("data/slide/");
			String fileName=new Date().getTime() + file.getOriginalFilename();
			String path = webPath + fileName;
			File uploadFile=new File(path);
	        file.transferTo(uploadFile);
	        System.out.println("ͼƬ�ϴ��ɹ�:"+path);
	        
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
     * �ļ����ع���  
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

    	 
        //ģ���ļ���Դ�ļ�·����  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //�����ļ���  
        String filename = "downloadCSV";  
        //��ͷ����
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "������վ");  
        map.put("2", "����");  
        map.put("3", "ͼƬ");  
        map.put("4", "ʱ��");  
             		
        String fileds[] = new String[] { "pcName", "sTitle" , "sTitleImg", "sAddTime"};// ������Ӣ������Ҳ����ʵ���������Ӧ��������  
        
        File file = CSVUtils.createCSVFile(sList, fileds, map, outPutPath,  
        		filename); 
        //��ȡ������  
        InputStream bis = new BufferedInputStream(new FileInputStream(file));  
        //ת�룬����ļ�����������  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //�����ļ�����ͷ  
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());    
        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }
        bis.close();
        out.close(); 
        
        //ɾ�����ɵ��ļ�
        deleteFile(file.getPath());
    }

	private void deleteFile(String path) {
		File deleteFile=new File(path);
        if (deleteFile!=null&&deleteFile.exists()) {
        	System.out.println(deleteFile.delete());
		}
	}  
	
}
