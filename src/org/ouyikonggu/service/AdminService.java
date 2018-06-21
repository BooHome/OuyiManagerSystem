package org.ouyikonggu.service;

import java.util.List;

import org.ouyikonggu.moudel.Admin;

public interface AdminService {
	 Admin get(Admin loginAd);  
	 List<Admin> list();
}
