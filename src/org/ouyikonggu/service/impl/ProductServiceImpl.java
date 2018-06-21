package org.ouyikonggu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ouyikonggu.dao.ProductDAO;
import org.ouyikonggu.dao.RegistDAO;
import org.ouyikonggu.moudel.Count;
import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDao;
	
	@Autowired
	RegistDAO registDao;
	
	/**
	 * ��ѯ�б�
	 * 
	 */
	public List<Product> queryList(Product product) {
		List<Product> proList = new ArrayList<Product>();
		proList = productDao.queryList(product);
		return proList;
	}

	/**
	 * ͨ��idListɾ��
	 */
	public int delete(List<Product> idList) {
		int row = productDao.delete(idList);
		return row;
	}

	
	/**
	 * ����
	 */
	public int update(Product product) {
		Count proCount=new Count();
		proCount.setCountName(product.getPTitle());
		proCount.setCActivate(product.getPActivate());
		proCount.setCAddTime(new Date());
		int result=registDao.update(proCount);
		int row = productDao.update(product);
		return row;
	}
	
	/**
	 * ���
	 */
	public int add(Product product) {
		int row = productDao.add(product);
		return row;
	}

	/**
	 * ͨ��Id��ѯ��Ʒ
	 */
	public Product selectById(int id) {
		Product pro = new Product();
		pro = productDao.selectById(id);
		return pro;
	}

	@Override
	public List<Product> selectByPcId(int id) {
		List<Product> proList = new ArrayList<Product>();
		proList=productDao.selectByPcId(id);
		return proList;
	}

	@Override
	public Product selectByTitle(String pTitle) {
		Product pro = new Product();
		pro = productDao.selectByTitle(pTitle);
		return pro;
	}


}
