package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Member;


public interface MemberDAO {
		
	List<Member> queryList();
	
	int add(Member mem);
	
	/**
	 * ��ѯUV
	 */
	int queryUV(int id);
	
	/**
	 * ��ѯPV
	 */
	int queryPV(int id);
	
	
	List<Member> selectMemberByPid(int pid);
	
	int countPidByPhone(String mTel);
	
	List<Member> selectPidByPhone(String mTel);
	
	int countPhoneByPid(Member member);
	
}
