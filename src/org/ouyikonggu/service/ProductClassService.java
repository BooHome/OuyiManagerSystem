package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.ProductClass;

public interface ProductClassService {
	
	/**
	 * ����б�
	 */
	List<ProductClass> queryList(ProductClass productClass);
	
	/**
	 * ͨ��idɾ��
	 */
	int delete(List<ProductClass> ids);
	
	/**
	 * ��������������
	 */
	int update(ProductClass productclass);
	
	/**
	 * ��������
	 */
	int add(ProductClass productclass); 
	
	/**
	 * ͨ��id��ѯ����
	 */
	ProductClass selectById(int id);
}
