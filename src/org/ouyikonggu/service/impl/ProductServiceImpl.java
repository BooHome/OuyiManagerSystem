package org.ouyikonggu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ouyikonggu.dao.ProductDAO;
import org.ouyikonggu.dao.RegistDAO;
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
	 * 查询列表
	 * 
	 */
	public List<Product> queryList(Product product) {
		List<Product> proList = new ArrayList<Product>();
		proList = productDao.queryList(product);
		return proList;
	}

	/**
	 * 通过idList删除
	 */
	public int delete(List<Product> idList) {
		int row = productDao.delete(idList);
		for (Product product : idList) {
			registDao.delete(product.getId());
		}
		return row;
	}

	
	/**
	 * 更新
	 */
	public int update(Product product) {
		int row = productDao.update(product);
		return row;
	}
	
	/**
	 * 添加
	 */
	public int add(Product product) {
		int row = productDao.add(product);
		return row;
	}

	/**
	 * 通过Id查询产品
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
