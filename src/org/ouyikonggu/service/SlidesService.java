package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.Slides;

public interface SlidesService {
	
	/**
	 * ����б�
	 */
	List<Slides> queryList(Slides slides);
	
	/**
	 * ͨ��idɾ��
	 */
	int delete(List<Slides> ids);
	
	/**
	 * ��������������
	 */
	int update(Slides slides);
	
	/**
	 * ��������
	 */
	int add(Slides slides); 
	
	/**
	 * ͨ��id��ѯ����
	 */
	Slides selectById(int id);
	
    public List<Slides> selectByScid(int scid);
}
