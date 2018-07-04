package org.ouyikonggu.util;

import org.ouyikonggu.service.CountService;
import org.ouyikonggu.service.SaProductService;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 *  
 * 项目名称：OuyiManagerSystem <br /> 
 * 类名称：Initialization <br /> 
 * 类描述： 实时更新统计数据 <br /> 
 * 创建人：huafeng <br /> 
 * 创建时间：2018-6-12 上午09:42:58 <br /> 
 *  
 * @version 
 */  
public class Initialization{

	@Autowired
	CountService csi;
	@Autowired
	SaProductService spsi;
	
	 public void init() {
		//在这里开启线程，执行操作
	    ThreadProduct tp = new ThreadProduct();
	    ThreadSaProduct tsp = new ThreadSaProduct();
	    tp.start();
	    tsp.start();
		 System.out.println("更新数据线程启动成功！");
	 }
	 
	//内部类
	private class ThreadProduct extends Thread{
	     @Override
	 	public void run() {
			try {
				while (true) {
					int i = csi.queryCount();// 更新统计结果
					if (i > 0) {
						System.out.println("产品统计结果更新成功！");
					}
					Thread.sleep(1000 * 30);
				}
	 		} catch (InterruptedException e) {
	 			e.printStackTrace();
	 		}
	 	}
	 }
	
	private class ThreadSaProduct extends Thread{
	     @Override
	 	public void run() {
	 		try {
	 			 while (true) {
		 			int result=spsi.querySapMember();
		 			if (result>0) {
		 				System.out.println("独立产品统计结果更新成功！");
		 			}
					Thread.sleep(1000 * 30);
				 }
	 		} catch (InterruptedException e) {
	 			e.printStackTrace();
	 		}
	 	}
	 }

}
