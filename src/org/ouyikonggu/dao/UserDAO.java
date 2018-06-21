package org.ouyikonggu.dao;

import java.util.List;
import java.util.Map;

import org.ouyikonggu.moudel.User;

public interface UserDAO {
	
	List<User> queryUser(Map map );
	
	int add(User user);
	
	int update(User user);
}
