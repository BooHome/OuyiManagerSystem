package org.ouyikonggu.dao;

import java.util.List;
import java.util.Map;

import org.ouyikonggu.moudel.Count;


public interface RegistDAO {
	
	List<Count> queryRegist(Map map );
	
	int add(Count count);
	
	int update(Count count);
	
	//同步删除产品统计数据
	int delete(int deleteId);

}
