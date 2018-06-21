package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.ProductClass;

public interface ProductClassService {
	
	/**
	 * 查出列表集
	 */
	List<ProductClass> queryList(ProductClass productClass);
	
	/**
	 * 通过id删除
	 */
	int delete(List<ProductClass> ids);
	
	/**
	 * 传入对象更新数据
	 */
	int update(ProductClass productclass);
	
	/**
	 * 插入数据
	 */
	int add(ProductClass productclass); 
	
	/**
	 * 通过id查询数据
	 */
	ProductClass selectById(int id);
}
