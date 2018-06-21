package org.ouyikonggu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ouyikonggu.dao.ProductClassDAO;
import org.ouyikonggu.moudel.ProductClass;
import org.ouyikonggu.service.ProductClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductClassServiceImpl implements ProductClassService {

	@Autowired
	ProductClassDAO productClassDao;
	
	/**
	 * 查询列表
	 * 模糊查询
	 */
	public List<ProductClass> queryList(ProductClass productClass) {
		List<ProductClass> procList = new ArrayList<ProductClass>();
		procList = productClassDao.list(productClass);
		return procList;
	}

	/**
	 * 通过id
	 * 删除一个或者多个
	 */
	public int delete(List<ProductClass> idList) {
		int row = productClassDao.delete(idList);
		return row;
	}

	
	/**
	 * 通过id
	 * 更新
	 */
	public int update(ProductClass productclass) {
		int row = productClassDao.update(productclass);
		return row;
	}
	
	/**
	 * 新增数据
	 */
	public int add(ProductClass productclass) {
		int row = productClassDao.add(productclass);
		return row;
	}

	/**
	 * 通过id查询数据
	 */
	@Override
	public ProductClass selectById(int id) {
		ProductClass proc = new ProductClass();
		proc = productClassDao.selectById(id);
		return proc;
	}

}
