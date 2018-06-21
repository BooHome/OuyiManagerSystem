package org.ouyikonggu.service.impl;

import java.util.List;

import org.ouyikonggu.dao.AdminDAO;
import org.ouyikonggu.moudel.Admin;
import org.ouyikonggu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimpl implements AdminService{
	
	@Autowired
	AdminDAO admindao;
	
	@Override
	public Admin get(Admin loginAd) {
		return admindao.get(loginAd);
	}

	@Override
	public List<Admin> list() {
		return admindao.list();
	}

}
