package org.ouyikonggu.service;

import java.util.List;
import java.util.Map;

import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.moudel.SapCount;
import org.ouyikonggu.moudel.StandAloneProduct;

public interface SaProductService {
	/**
	 * ���
	 */
	public int add(StandAloneProduct saProduct); 
	/**
	 * ɾ��
	 */
    public int delete(List<StandAloneProduct> sapList); 
    /**
	 * ͨ��id��ѯ
	 */
    public StandAloneProduct  selectById(int id); 
    /**
	 * ����
	 */
    public int update(StandAloneProduct saProduct);  
    /**
	 * ��ѯȫ���б�
	 */ 
    public List<StandAloneProduct> queryList(StandAloneProduct saProduct);
    /**
	 * ��������û�
	 */ 
    public int saveSapUser(Member sapMember);
    //����ͳ�ƽ��
    public int querySapMember();
    
    public List<SapCount> querySaproduct(Map map);
}
