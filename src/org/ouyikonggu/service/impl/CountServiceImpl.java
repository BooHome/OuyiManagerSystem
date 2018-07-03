package org.ouyikonggu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ouyikonggu.dao.MemberDAO;
import org.ouyikonggu.dao.ProductDAO;
import org.ouyikonggu.dao.RegistDAO;
import org.ouyikonggu.dao.UserDAO;
import org.ouyikonggu.moudel.Count;
import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.moudel.Product;
import org.ouyikonggu.moudel.User;
import org.ouyikonggu.moudel.UserProduct;
import org.ouyikonggu.service.CountService;
import org.ouyikonggu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl implements CountService {

	@Autowired
	ProductDAO productDao;
	
	@Autowired
	MemberDAO memberDAo;
	
	@Autowired
	RegistDAO registDao;
	
	@Autowired
	UserDAO userDao;
	
	//实时统计产品的情况，并更新到数据库中
	@SuppressWarnings("deprecation")
	@Override
	public int queryCount() {
		int result=0;
		List<Product> proList = new ArrayList<Product>();
		List<Count> countList = new ArrayList<Count>();  //统计的结果集
		List<Count> exitsCountList = registDao.queryRegist(null);//库中已经有的结果集
		proList = productDao.queryList(null);  //查询所有的产品

		for(Iterator iter=proList.iterator();iter.hasNext();){
			Product pr = (Product) iter.next();
			Count count = new Count();
			int id = pr.getId();
			count.setCpid(id);
			count.setCpro(pr);
			count.setUV(memberDAo.queryUV(id));
			count.setPV(memberDAo.queryPV(id));
			countList.add(count);
		}
		
		//注意跳出双层循环的条件
		for (Count count : countList) {
			int flag=0;
			for (Count exitsCount : exitsCountList) {
				if (exitsCount.getCpid()==count.getCpid()
						&& DateUtil.getDate(exitsCount.getCAddTime()).equals(DateUtil.getDate(new Date())) ) {
					count.setCAddTime(new Date());
					result=update(count);
					flag=1;
					break;
				}
			}
			if (flag==1) {
				continue;
			}
			result=add(count);
		}
		return result;
	}


	//根据传入的查询条件查询产品的统计结果
	@Override
	public List<Count> queryRegist(Map map) {
		List<Count> countList = new ArrayList<Count>();
		countList=registDao.queryRegist(map);
		return countList;
	}
	
	//新增统计结果
	@Override
	public int add(Count count) {
		int row=registDao.add(count);
		return row;
	}

	//修改统计结果
	@Override
	public int update(Count count) {
		int row=registDao.update(count);
		return row;
	}

	
	//查询某产品下所有注册用户
	@Override
	public List<Member> selectMemberByPid(int pid) {
		 List<Member> mList=memberDAo.selectMemberByPid(pid);
		return mList;
	}

	//实时统计用户的情况，并更新到数据库中
	@Override
	public List<User> selectMember() {
		List<Member> mList=memberDAo.queryList();
		List<User> exitsUList=userDao.queryUser(null);//已存在的user
		List<User> uList=new ArrayList<>();
		//更新用户表中的mtel
		for (Member member : mList) {
			User user=new User();
			user.setUTel(member.getMTel());
			user.setUAddTime(member.getMAddTime());
			int pCount=memberDAo.countPidByPhone(member.getMTel());
			user.setPCount(pCount);
			System.out.println(user);
			uList.add(user);
		}
		
		for (User user : uList) {
			int flag=0;
			for (User exitsUser : exitsUList) {
				if (exitsUser.getUTel().equals(user.getUTel())){
					int result=userDao.update(user);
					flag=1;
					break;
				}
			}
			if (flag==1) {
				continue;
			}
			int result=userDao.add(user);
		}		
		return uList;
	}

	//根据传入的查询条件查询产品的统计结果
	@Override
	public List<User> queryUser(Map map) {
		List<User> uList=new ArrayList<>();
		uList=userDao.queryUser(map);
		return uList;
	}

   //用户访问产品详情
	@Override
	public List<UserProduct> selectMemberByPhone(String mTel) {
		List<Member> mList=memberDAo.selectPidByPhone(mTel);
		List<UserProduct> upList=new ArrayList<>();
		for (Member member : mList) {
			member.setMTel(mTel);
			UserProduct up=new UserProduct();
			Product p=new Product();
			p=productDao.selectById(member.getMProductID());
			if (p!=null) {
				up.setUPName(p.getPTitle());
			}
			up.setUCount(memberDAo.countPhoneByPid(member));
			upList.add(up);
		}
		
		return upList;
	}
	
	
}
