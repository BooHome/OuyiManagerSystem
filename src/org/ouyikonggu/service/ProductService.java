package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.Product;

public interface ProductService {
	
	/**
	 * 查出列表集
	 */
	List<Product> queryList(Product product);
	
	/**
	 * 通过id删除
	 */
	int delete(List<Product> ids);
	
	/**
	 * 传入对象更新数据
	 */
	int update(Product product);
	
	/**
	 * 插入数据
	 */
	int add(Product product); 

	/**
	 * 通过id查询数据
	 */
	public Product selectById(int id); 
	
	/**
	 * 通过产品名称查询数据
	 */
	public Product selectByTitle(String pTitle); 

    /**
	 * 根据分站id获取分站下的全部产品
	 */
    public List<Product> selectByPcId(int id);
}
