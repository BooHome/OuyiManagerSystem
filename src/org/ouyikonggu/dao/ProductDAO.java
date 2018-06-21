package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.Slides;

public interface ProductDAO {
	/**
	 * ���
	 */
	public int add(Product product); 
	/**
	 * ɾ��
	 */
    public int delete(List<Product> idList); 
    /**
	 * ͨ�����ֲ�ѯ
	 */  
    public Product selectByName(Product product); 
    /**
	 * ͨ��id��ѯ
	 */
    public Product  selectById(int id); 
    /**
	 * ͨ����Ʒ���Ʋ�ѯ
	 */
    public Product  selectByTitle(String pTitle); 
    /**
	 * ����
	 */
    public int update(Product product);  
    /**
	 * ��ѯȫ���б�
	 */ 
    public List<Product> queryList(Product product);
    
    /**
	 * ���ݷ�վid��ȡ��վ�µ�ȫ����Ʒ
	 */
    public List<Product> selectByPcId(int id);
}
