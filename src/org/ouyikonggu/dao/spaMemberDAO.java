package org.ouyikonggu.dao;

import org.ouyikonggu.moudel.Member;

public interface spaMemberDAO {

	int add(Member mem);
	/**
	 * ≤È—ØUV
	 */
	int queryUV(int id);
	
	/**
	 * ≤È—ØPV
	 */
	int queryPV(int id);
}
