package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Member;


public interface MemberDAO {
		
	List<Member> queryList();
	
	int add(Member mem);
	
	/**
	 * ≤È—ØUV
	 */
	int queryUV(int id);
	
	/**
	 * ≤È—ØPV
	 */
	int queryPV(int id);
	
	
	List<Member> selectMemberByPid(int pid);
	
	int countPidByPhone(String mTel);
	
	List<Member> selectPidByPhone(String mTel);
	
	int countPhoneByPid(Member member);
	
}
