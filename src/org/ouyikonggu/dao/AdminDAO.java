package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Admin;

public interface AdminDAO {
	public Admin get(Admin loginAd);  
    public List<Admin> list();
}
