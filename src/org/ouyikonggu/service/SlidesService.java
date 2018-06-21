package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.Slides;

public interface SlidesService {
	
	/**
	 * 查出列表集
	 */
	List<Slides> queryList(Slides slides);
	
	/**
	 * 通过id删除
	 */
	int delete(List<Slides> ids);
	
	/**
	 * 传入对象更新数据
	 */
	int update(Slides slides);
	
	/**
	 * 插入数据
	 */
	int add(Slides slides); 
	
	/**
	 * 通过id查询数据
	 */
	Slides selectById(int id);
	
    public List<Slides> selectByScid(int scid);
}
