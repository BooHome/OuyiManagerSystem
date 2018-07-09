package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.Slides;

public interface ProductDAO {
	/**
	 * 添加
	 */
	public int add(Product product); 
	/**
	 * 删除
	 */
    public int delete(List<Product> idList); 
    /**
	 * 通过名字查询
	 */  
    public Product selectByName(Product product); 
    /**
	 * 通过id查询
	 */
    public Product  selectById(int id); 
    /**
	 * 通过产品名称查询
	 */
    public Product  selectByTitle(String pTitle); 
    /**
	 * 更新
	 */
    public int update(Product product);  
    /**
	 * 查询全部列表
	 */ 
    public List<Product> queryList(Product product);
    
    /**
	 * 根据分站id获取分站下的全部产品
	 */
    public List<Product> selectByPcId(int id);
}
