package org.ouyikonggu.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.ouyikonggu.dao.MemberDAO;
import org.ouyikonggu.dao.ProductClassDAO;
import org.ouyikonggu.dao.ProductDAO;
import org.ouyikonggu.dao.StandAloneProductDAO;
import org.ouyikonggu.moudel.ProductClass;
import org.ouyikonggu.moudel.StandAloneProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"file:src/springMVC.xml",  
        "file:src/applicationContext.xml"})  
public class mybatisTest {
	
	@Autowired
	ProductClassDAO pcd;

	@Autowired
	ProductDAO pd;
	
	@Autowired
	MemberDAO md;
	
	@Autowired
	StandAloneProductDAO sapDao;
	
	@Test
	public void dbTest() {
		StandAloneProduct sap=new StandAloneProduct();
		sap.setSapName("»õ´úÍø");
		sap.setUrl("https://www.baidu.com");
		
		int result=sapDao.add(sap);
		System.out.println(result);
	}
	
}
