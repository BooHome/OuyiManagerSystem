package org.ouyikonggu.dao;

import java.util.List;
import java.util.Map;

import org.ouyikonggu.moudel.Count;
import org.ouyikonggu.moudel.SapCount;

public interface saproductDAO {
	List<SapCount> querySaproduct(Map map);
	
	int add(SapCount count);
	
	int update(SapCount count);

}
