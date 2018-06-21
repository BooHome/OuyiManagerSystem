package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.StandAloneProduct;

public interface StandAloneProductDAO {
	/**
	 * ����
	 */
	public int add(StandAloneProduct saProduct); 
	/**
	 * ɾ��
	 */
    public int delete(List<StandAloneProduct> sapList); 
    /**
	 * ͨ�����ֲ�ѯ
	 */  
    public StandAloneProduct selectByName(StandAloneProduct saProduct); 
    /**
	 * ͨ��id��ѯ
	 */
    public StandAloneProduct  selectById(int id); 
    /**
	 * ����
	 */
    public int update(StandAloneProduct saProduct);  
    /**
	 * ��ѯȫ��
	 */ 
    public List<StandAloneProduct> queryList(StandAloneProduct saProduct);
    
}
