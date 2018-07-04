package org.ouyikonggu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ouyikonggu.dao.StandAloneProductDAO;
import org.ouyikonggu.dao.saproductDAO;
import org.ouyikonggu.dao.spaMemberDAO;
import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.moudel.SapCount;
import org.ouyikonggu.moudel.StandAloneProduct;
import org.ouyikonggu.service.SaProductService;
import org.ouyikonggu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaProductServiceimpl implements SaProductService{

	@Autowired
	StandAloneProductDAO sapDao;
	
	@Autowired
	spaMemberDAO sapMDao;
	
	@Autowired
	saproductDAO sapCountDao;
	
	@Override
	public int add(StandAloneProduct saProduct) {
		int row = sapDao.add(saProduct);
		return row;
	}

	@Override
	public int delete(List<StandAloneProduct> sapList) {
		int row = sapDao.delete(sapList);
		return row;
	}

	@Override
	public StandAloneProduct selectById(int id) {
		StandAloneProduct saProduct=sapDao.selectById(id);
		return saProduct;
	}

	@Override
	public int update(StandAloneProduct saProduct) {
		int row = sapDao.update(saProduct);
		return row;
	}

	@Override
	public List<StandAloneProduct> queryList(StandAloneProduct saProduct) {
		List<StandAloneProduct> saProList = new ArrayList<StandAloneProduct>();
		saProList = sapDao.queryList(saProduct);
		return saProList;
	}

	@Override
	public int saveSapUser(Member sapMember) {
		int row=sapMDao.add(sapMember);
		return row;
	}

	@Override
	public int querySapMember() {
		int result=0;
		List<StandAloneProduct> saplist=new ArrayList<>();
		List<SapCount> countList = new ArrayList<SapCount>();  //统计的结果集
		List<SapCount> exitsCountList = sapCountDao.querySaproduct(null);//库中已经有的结果集
		saplist=sapDao.queryList(null);
		
		for(Iterator iter=saplist.iterator();iter.hasNext();){
			StandAloneProduct sapr = (StandAloneProduct) iter.next();
			SapCount count = new SapCount();
			int id = sapr.getId();
			count.setUV(sapMDao.queryUV(id));
			count.setPV(sapMDao.queryPV(id));
			count.setSapId(sapr.getId());
			count.setSap(sapr);
			countList.add(count);
		}
		
		// 注意跳出双层循环的条件
		for (SapCount count : countList) {
			int flag = 0;
			for (SapCount exitsCount : exitsCountList) {
				if (exitsCount!=null && count!=null) {		
					if (exitsCount.getSap().getUrl().equals(count.getSap().getUrl())
							&& DateUtil.getDate(exitsCount.getCAddTime()).equals(DateUtil.getDate(new Date()))) {
						count.setCAddTime(new Date());
						result = sapCountDao.update(count);
						flag = 1;
						break;
					}
				}
			}
			if (flag == 1) {
				continue;
			}
			result = sapCountDao.add(count);
		}
		return result;
	}

	@Override
	public List<SapCount> querySaproduct(Map map) {
		List countList = new ArrayList<SapCount>();
		countList=sapCountDao.querySaproduct(map);
		return countList;
	}
	

}
