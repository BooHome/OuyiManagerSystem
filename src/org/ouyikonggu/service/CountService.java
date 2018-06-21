package org.ouyikonggu.service;

import java.util.List;
import java.util.Map;

import org.ouyikonggu.moudel.Count;
import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.moudel.User;
import org.ouyikonggu.moudel.UserProduct;

public interface CountService {

	public int queryCount();
	
	List<Count> queryRegist(Map map );
	
	int add(Count count);
	
	int update(Count count);
	
	List<Member> selectMemberByPid(int pid);
	
	List<User> selectMember();
	
	List<User> queryUser(Map map);
	
	List<UserProduct> selectMemberByPhone(String mTel);
	
}
