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
	 * ��ѯ�б�
	 * ģ����ѯ
	 */
	public List<ProductClass> queryList(ProductClass productClass) {
		List<ProductClass> procList = new ArrayList<ProductClass>();
		procList = productClassDao.list(productClass);
		return procList;
	}

	/**
	 * ͨ��id
	 * ɾ��һ�����߶��
	 */
	public int delete(List<ProductClass> idList) {
		int row = productClassDao.delete(idList);
		return row;
	}

	
	/**
	 * ͨ��id
	 * ����
	 */
	public int update(ProductClass productclass) {
		int row = productClassDao.update(productclass);
		return row;
	}
	
	/**
	 * ��������
	 */
	public int add(ProductClass productclass) {
		int row = productClassDao.add(productclass);
		return row;
	}

	/**
	 * ͨ��id��ѯ����
	 */
	@Override
	public ProductClass selectById(int id) {
		ProductClass proc = new ProductClass();
		proc = productClassDao.selectById(id);
		return proc;
	}

}
