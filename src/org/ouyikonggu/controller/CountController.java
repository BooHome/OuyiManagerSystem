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
	
	//��Ʒע��ͳ��
	@RequestMapping("registerCount")
	public ModelAndView registerCount(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		ModelAndView mav = new ModelAndView();
		
		Map defultR=new HashMap<>();
		defultR.put("startTime", DateUtil.getDate(new Date()));
		defultR.put("CActivate", 1);
		
		
		// ���������ļ�����
		List<Count> dcList=csi.queryRegist(defultR);
		request.getSession().setAttribute("download", dcList);
		  //	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(pageNum,19);  
	    
		List<Count> cList=csi.queryRegist(defultR);
		for (Count count : cList) {
			System.out.println(count);
		}
		
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(cList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/registerCount");
		return mav;
	}
	
	//��Ʒע������
	@RequestMapping("registerPhone")
	public ModelAndView registerPhone(HttpServletRequest req){
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		String pTitle=req.getParameter("pTitle");
		Product rProduct=psi.selectByTitle(pTitle);
		
		// ���������ļ�����
		List<Member> dmList=csi.selectMemberByPid(rProduct.getId());
		req.getSession().setAttribute("download", dmList);
	    //	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(pageNum,19);  
	    
		List<Member> mList=csi.selectMemberByPid(rProduct.getId());
		
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(mList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("pTitle",pTitle);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/registerPhone");
		return mav;
	}
	
	// ��ѯ��Ʒ
	@RequestMapping(value = "searchregister", method = RequestMethod.GET)
	public ModelAndView searchRegister(HttpServletRequest requst) {
		//�����Ĳ�ѯ����
		String name = requst.getParameter("keyName");
		String startTime = requst.getParameter("startTime");
		String endTime = requst.getParameter("endTime");
		
		if (name.equals("")&&startTime.equals("")) {
			startTime=DateUtil.getDate(new Date());
		}
		Map searchR=new HashMap<>();
		searchR.put("startTime", startTime);
		searchR.put("endTime", endTime);
		searchR.put("pTitle", name);
		
		// ���������ļ�����
		List<Count> dcList=csi.queryRegist(searchR);
		requst.getSession().setAttribute("download", dcList);
		
	    //	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(1,19);  
	    
		List<Count> cList=csi.queryRegist(searchR);
		for (Count count : cList) {
			System.out.println(count);
		}
		
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(cList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchR",searchR);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/registerCount");
		return mav;
	}
	
	//�û�ͳ��
	@RequestMapping("userCount")
	public ModelAndView userCount(HttpServletRequest request){
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		ModelAndView mav = new ModelAndView();
		// ���������ļ�����
		List<User> duList=csi.selectMember();
		request.getSession().setAttribute("download", duList);
		 //	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(pageNum,19);  
		List<User> uList=csi.selectMember();
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(uList,19);  
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/userCount");
		return mav;
	}
	
	// ��ѯ�û�
	@RequestMapping(value = "searchuser", method = RequestMethod.GET)
	public ModelAndView searchUser(HttpServletRequest requst) {
		// �����Ĳ�ѯ����
		String name = requst.getParameter("keyName");
		String startTime = requst.getParameter("startTime");
		String endTime = requst.getParameter("endTime");

		Map searchU = new HashMap<>();
		searchU.put("startTime", startTime);
		searchU.put("endTime", endTime);
		searchU.put("uTel", name);
		
		// ���������ļ�����
		List<User> duList = csi.queryUser(searchU);
		requst.getSession().setAttribute("download", duList);
		 //	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(1,19);  
	    
		List<User> uList = csi.queryUser(searchU);
		for (User user : uList) {
			System.out.println(user);
		}

		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(uList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("count/userCount");
		return mav;
	}
	
	// ��Ʒע������
	@RequestMapping("userProduct")
	public ModelAndView userProduct(HttpServletRequest req) {
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		
	    //	�����ҳ��ѯ��ʹ��PageHelper��ҳ���� ;�ڲ�ѯ֮ǰ���뵱ǰҳ��Ȼ����ټ�¼  
	    PageHelper.startPage(pageNum,19);  
		String uTel=req.getParameter("uTel");
		System.out.println(uTel);
		List<UserProduct> pList=csi.selectMemberByPhone(uTel);
		for (UserProduct userProduct : pList) {
			System.out.println(userProduct);
		}
		//ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���  
	    PageInfo pageInfo = new PageInfo<>(pList,19);  
		ModelAndView mav = new ModelAndView();
		mav.addObject("uTel",uTel);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName("count/userProduct");
		return mav;
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
    	List<Count> cList=new ArrayList<>();
    	for (int i = 0; i < result.size(); i++) {
			cList.add((Count) result.get(i));
		}

    	 
        //ģ���ļ���Դ�ļ�·����  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //�����ļ���  
        String filename = "downloadCSV";  
        //��ͷ����
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "��Ʒ����");  
        map.put("2", "��Ʒ��������PV��");  
        map.put("3", "�����û�����UV��");  
        map.put("4", "����ͳ��ʱ��");  
             		
        String fileds[] = new String[] { "countName", "pV" , "uV", "cAddTime"};// ������Ӣ������Ҳ����ʵ���������Ӧ��������  
        
        File file = CSVUtils.createCSVFile(cList, fileds, map, outPutPath,  
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
    
    /**  
     * �ļ����ع���  
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

    	 
        //ģ���ļ���Դ�ļ�·����  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //�����ļ���  
        String filename = "downloadCSV";  
        //��ͷ����
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "�û��ֻ�����");  
        map.put("2", "���ʲ�Ʒ����");  
        map.put("3", "�״η���ʱ��");  
             		
        String fileds[] = new String[] { "uTel", "pCount" , "uAddTime"};// ������Ӣ������Ҳ����ʵ���������Ӧ��������  
        
        File file = CSVUtils.createCSVFile(cList, fileds, map, outPutPath,  
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

    /**  
     * �ļ����ع���  
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

    	 
        //ģ���ļ���Դ�ļ�·����  
        String outPutPath = request.getSession().getServletContext().getRealPath("data/csvFile");  
        //�����ļ���  
        String filename = "downloadCSV";  
        //��ͷ����
        LinkedHashMap map = new LinkedHashMap();  
        map.put("1", "�����û����ֻ�����");  
        map.put("2", "���һ�η��ʵ�ʱ��");  
             		
        String fileds[] = new String[] { "mTel", "mAddTime"};// ������Ӣ������Ҳ����ʵ���������Ӧ��������  
        
        File file = CSVUtils.createCSVFile(cList, fileds, map, outPutPath,  
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
        	System.out.println("ɾ���ɹ���");
		}
	}  
}
