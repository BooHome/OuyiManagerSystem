package org.ouyikonggu.dao;

import org.ouyikonggu.moudel.Member;

public interface spaMemberDAO {

	int add(Member mem);
	/**
	 * ��ѯUV
	 */
	int queryUV(int id);
	
	/**
	 * ��ѯPV
	 */
	int queryPV(int id);
}
