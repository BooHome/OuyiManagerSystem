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
import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.ProductClass;
import org.ouyikonggu.service.impl.ProductClassServiceImpl;
import org.ouyikonggu.service.impl.ProductServiceImpl;
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
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductServiceImpl psi;
	@Autowired
	ProductClassServiceImpl pcs;

	// 产品管理
	@RequestMapping("productManager")
	public ModelAndView prductManager(HttpServletRequest request) {
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		ModelAndView mav = new ModelAndView();
		
		// 导出下载文件数据
		List<Product> dpList=psi.queryList(null);
		request.getSession().setAttribute("download", dpList);

//		引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(pageNum,19);		
	    
		List<Product> pList=psi.queryList(null);
		
		for (Product product : pList) {
			//过滤集合当中空的信息为空的产品
			if (product.getPTitle()!=null) {
				System.out.println(product);
			}
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(pList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("product/productManager");
		return mav;
	}

	// 新增产品页面
	@RequestMapping("addProductPage")
	public ModelAndView addProductPage() {
		ModelAndView mav = new ModelAndView();
		List<ProductClass> pClasses = pcs.queryList(null);
		mav.addObject("pClasses", pClasses);
		mav.setViewName("product/addProduct");
		return mav;
	}

	// 新增产品
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public void addProduct(@RequestParam("pTitleImg") CommonsMultipartFile file,HttpServletRequest requst,HttpServletResponse response) throws Exception {
		System.out.println("上传过来的图片"+file.getOriginalFilename());
		String pTitle=requst.getParameter("pTitle");
		String pRemark=requst.getParameter("pRemark");
		String pcid=requst.getParameter("pcid");
		String pED=requst.getParameter("pED");
		String pNum=requst.getParameter("pNum");
		String pFDSJ=requst.getParameter("pFDSJ");
		String pLX=requst.getParameter("pLX");
		String pJKQX=requst.getParameter("pJKQX");
		String pUrl=requst.getParameter("pUrl");
		String pSortID=requst.getParameter("pSortID");
		String pActivate=requst.getParameter("pActivate");
		String pState=requst.getParameter("pState");
		
		Product insertP = new Product();
		insertP.setPcid(Integer.parseInt(pcid));
		insertP.setPTitle(pTitle);
		insertP.setPRemark(pRemark);
		insertP.setPED(pED);
		insertP.setPFDSJ(pFDSJ);
		insertP.setPLX(pLX);
		insertP.setPJKQX(pJKQX);
		insertP.setPUrl(pUrl);
		insertP.setPNum(Integer.parseInt(pNum));
		insertP.setPSortID(Integer.parseInt(pSortID));
		insertP.setPActivate(Integer.parseInt(pActivate));
		insertP.setPState(Integer.parseInt(pState));
		
		// 上传选择图片
		String webPath = requst.getSession().getServletContext().getRealPath("data/prod/");
		String fileName=new Date().getTime() + file.getOriginalFilename();
		String path = webPath + fileName;
		File uploadFile = new File(path);
		file.transferTo(uploadFile);
		System.out.println("图片上传成功:" + path);
		
		insertP.setPTitleImg("http://sj.ouyisms.com/OuyiManagerSystem/data/prod/"+fileName);
		System.out.println(insertP);
		int result = psi.add(insertP);
		if (result > 0) {
			requst.setAttribute("addStr", "true");
			requst.getRequestDispatcher("productManager?pageNum=1").forward(requst, response);
		} else {
			requst.setAttribute("addStr", "false");
			requst.getRequestDispatcher("addProductPage").forward(requst, response);		
		}
	}

	// 查询产品
	@RequestMapping(value = "searchproduct", method = RequestMethod.GET)
	public ModelAndView searchProduct(HttpServletRequest requst) {
		String name = requst.getParameter("keyName");
		Product searchp=new Product();
		searchp.setPTitle(name);
		ModelAndView mav = new ModelAndView();
		
		// 导出下载文件数据
		List<Product> dpList=psi.queryList(searchp);
		requst.getSession().setAttribute("download", dpList);
		
		//	引入分页查询，使用PageHelper分页功能 ;在查询之前传入当前页，然后多少记录  
	    PageHelper.startPage(1,19);	
	    
		List<Product> pList=psi.queryList(searchp);
		for (Product product : pList) {
			System.out.println(product);
		}
		
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	    PageInfo pageInfo = new PageInfo<>(pList,19);  
	    
		mav.addObject("searchp",searchp);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("product/productManager");
		return mav;
	}

	// 删除产品
	@RequestMapping(value = "deleteProduct", method = RequestMethod.GET)
	public void deleteProduct(@RequestParam("p") int deletePcId,HttpServletRequest requst, HttpServletResponse response) throws Exception {
		Product deletep=new Product();
		deletep=psi.selectById(deletePcId);
		
		//删除已经上传的文件
		String webPath=requst.getSession().getServletContext().getRealPath("data/prod/");
		String fileName=StringUtils.substringAfter(deletep.getPTitleImg(), "http://sj.ouyisms.com/OuyiManagerSystem/data/prod/");
		System.out.println(webPath+fileName);
		File deleteFile=new File(webPath+fileName);
		if (deleteFile!=null&&deleteFile.exists()) {
			deleteFile.delete();
		}
		
		List<Product> list = new ArrayList<>();
		list.add(deletep);
		int result = psi.delete(list);
		response.sendRedirect("productManager?pageNum=1");
	}

	// 修改产品跳转
	@RequestMapping(value = "updateProductJump", method = RequestMethod.GET)
	public ModelAndView updateProductJump(@RequestParam("p") int updatePId, HttpServletResponse response)
			throws Exception {
		Product updateP=psi.selectById(updatePId);
		System.out.println(updateP);
		List<ProductClass> pClasses = pcs.queryList(null);
		ModelAndView mav = new ModelAndView();
		mav.addObject("updateP",updateP);
		mav.addObject("pClasses", pClasses);
		mav.setViewName("product/updateProduct");
		return mav;
	}

	// 修改产品
	@RequestMapping(value = "updateproduct", method = RequestMethod.POST)
	public void updateProduct(@RequestParam("pTitleImg") CommonsMultipartFile file,HttpServletRequest requst,HttpServletResponse response)
			throws Exception {
		System.out.println("上传过来的图片"+file.getOriginalFilename());
		String pTitle=requst.getParameter("pTitle");
		String pRemark=requst.getParameter("pRemark");
		String pcid=requst.getParameter("pcid");
		String pED=requst.getParameter("pED");
		String pFDSJ=requst.getParameter("pFDSJ");
		String pLX=requst.getParameter("pLX");
		String pJKQX=requst.getParameter("pJKQX");
		String pUrl=requst.getParameter("pUrl");
		String pNum=requst.getParameter("pNum");
		String pSortID=requst.getParameter("pSortID");
		String pState=requst.getParameter("pState");
		String id=requst.getParameter("id");
		String pActivate=requst.getParameter("pActivate");
		String orgUrl=requst.getParameter("orgUrl");
		
		Product updateP = new Product();
		updateP.setId(Integer.parseInt(id));
		updateP.setPcid(Integer.parseInt(pcid));
		updateP.setPTitle(pTitle);
		updateP.setPRemark(pRemark);
		updateP.setPED(pED);
		updateP.setPFDSJ(pFDSJ);
		updateP.setPLX(pLX);
		updateP.setPJKQX(pJKQX);
		updateP.setPUrl(pUrl);
		updateP.setPNum(Integer.parseInt(pNum));
		updateP.setPSortID(Integer.parseInt(pSortID));
		updateP.setPState(Integer.parseInt(pState));
		updateP.setPActivate(Integer.parseInt(pActivate));
		
		if(file.getSize()==0) {
			updateP.setPTitleImg(orgUrl);
		}else {
			String dWebPath=requst.getSession().getServletContext().getRealPath("data/prod/");
			String dFileName=StringUtils.substringAfter(orgUrl, "http://sj.ouyisms.com/OuyiManagerSystem/data/prod/");
			//删除原有的图片
			File deleteFile=new File(dWebPath+dFileName);
			if (deleteFile!=null&&deleteFile.exists()) {
				deleteFile.delete();
			}
			// 上传选择图片
			String webPath = requst.getSession().getServletContext().getRealPath("data/prod/");
			String fileName=new Date().getTime() + file.getOriginalFilename();
			String path = webPath + fileName;
			File uploadFile = new File(path);
			file.transferTo(uploadFile);
			System.out.println("图片上传成功:" + path);
			
			updateP.setPTitleImg("http://sj.ouyisms.com/OuyiManagerSystem/data/prod/"+fileName);
		}
		
		System.out.println(updateP);		
		int result=psi.update(updateP);
		if (result > 0) {
			requst.setAttribute("updateStr", "true");
			requst.getRequestDispatcher("productManager?pageNum=1").forward(requst, response);
		} else {
			requst.setAttribute("updateStr", "false");
			requst.getRequestDispatcher("updateProductJump?"+id).forward(requst, response);		
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
    	List<Product> pList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			pList.add((Product) result.get(i));
		}
    	for (Product product : pList) {
			product.setPcName(product.getProductClass().getCName());
		}
    	 
        //模拟文件，源文件路径名  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //下载文件名  
        String filename = "downloadCSV";  
        //表头数据
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "所属分站");  
        map.put("2", "名称");  
        map.put("3", "最高额度");  
        map.put("4", "利息");  
        map.put("5", "借款期限");  
        map.put("6", "注册量");  
        map.put("7", "跳转地址");  
        map.put("8", "时间");  
             		
        String fileds[] = new String[] { "pcName", "pTitle" , "pED", "pLX" , "pJKQX", "pRegNum" , "pUrl", "pAddTime"};// 设置列英文名（也就是实体类里面对应的列名）  
        
        File file = CSVUtils.createCSVFile(pList, fileds, map, outPutPath,  
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
