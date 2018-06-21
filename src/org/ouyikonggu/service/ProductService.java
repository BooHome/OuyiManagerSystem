package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.Product;

public interface ProductService {
	
	/**
	 * ����б�
	 */
	List<Product> queryList(Product product);
	
	/**
	 * ͨ��idɾ��
	 */
	int delete(List<Product> ids);
	
	/**
	 * ��������������
	 */
	int update(Product product);
	
	/**
	 * ��������
	 */
	int add(Product product); 

	/**
	 * ͨ��id��ѯ����
	 */
	public Product selectById(int id); 
	
	/**
	 * ͨ����Ʒ���Ʋ�ѯ����
	 */
	public Product selectByTitle(String pTitle); 

    /**
	 * ���ݷ�վid��ȡ��վ�µ�ȫ����Ʒ
	 */
    public List<Product> selectByPcId(int id);
}
