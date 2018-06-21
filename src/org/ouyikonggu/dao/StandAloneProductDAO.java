package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.StandAloneProduct;

public interface StandAloneProductDAO {
	/**
	 * 增加
	 */
	public int add(StandAloneProduct saProduct); 
	/**
	 * 删除
	 */
    public int delete(List<StandAloneProduct> sapList); 
    /**
	 * 通过名字查询
	 */  
    public StandAloneProduct selectByName(StandAloneProduct saProduct); 
    /**
	 * 通过id查询
	 */
    public StandAloneProduct  selectById(int id); 
    /**
	 * 更新
	 */
    public int update(StandAloneProduct saProduct);  
    /**
	 * 查询全部
	 */ 
    public List<StandAloneProduct> queryList(StandAloneProduct saProduct);
    
}
