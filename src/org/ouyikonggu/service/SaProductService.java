package org.ouyikonggu.service;

import java.util.List;
import java.util.Map;

import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.moudel.SapCount;
import org.ouyikonggu.moudel.StandAloneProduct;

public interface SaProductService {
	/**
	 * 添加
	 */
	public int add(StandAloneProduct saProduct); 
	/**
	 * 删除
	 */
    public int delete(List<StandAloneProduct> sapList); 
    /**
	 * 通过id查询
	 */
    public StandAloneProduct  selectById(int id); 
    /**
	 * 更新
	 */
    public int update(StandAloneProduct saProduct);  
    /**
	 * 查询全部列表
	 */ 
    public List<StandAloneProduct> queryList(StandAloneProduct saProduct);
    /**
	 * 保存访问用户
	 */ 
    public int saveSapUser(Member sapMember);
    //更新统计结果
    public int querySapMember();
    
    public List<SapCount> querySaproduct(Map map);
}
