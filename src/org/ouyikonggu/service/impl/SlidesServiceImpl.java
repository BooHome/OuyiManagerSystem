package org.ouyikonggu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ouyikonggu.dao.SlidesDAO;
import org.ouyikonggu.moudel.Slides;
import org.ouyikonggu.service.SlidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlidesServiceImpl implements SlidesService {

	@Autowired
	SlidesDAO slidesDao;
	
	/**
	 * 查询列表
	 * 模糊查询
	 */
	public List<Slides> queryList(Slides Slides) {
		List<Slides> procList = new ArrayList<Slides>();
		procList = slidesDao.queryList(Slides);
		return procList;
	}

	/**
	 * 通过id
	 * 删除一个或者多个
	 */
	public int delete(List<Slides> idList) {
		int row = slidesDao.delete(idList);
		return row;
	}

	
	/**
	 * 通过id
	 * 更新
	 */
	public int update(Slides slides) {
		int row = slidesDao.update(slides);
		return row;
	}
	
	/**
	 * 新增数据
	 */
	public int add(Slides slides) {
		int row = slidesDao.add(slides);
		return row;
	}

	/**
	 * 通过id查询数据
	 */
	@Override
	public Slides selectById(int id) {
		Slides proc = new Slides();
		proc = slidesDao.selectById(id);
		return proc;
	}

	@Override
	public List<Slides> selectByScid(int scid) {
		List<Slides> procList = new ArrayList<Slides>();
		procList=slidesDao.selectByScid(scid);
		return procList;
	}

}
