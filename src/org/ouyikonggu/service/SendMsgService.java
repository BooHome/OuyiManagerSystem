package org.ouyikonggu.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;



/**
 *���Ͷ��Žӿ�
 */
public interface SendMsgService {
	
	 Map send(String phone) throws UnsupportedEncodingException;
	
	void savePhone(Map<String, String> map);
	
	void savePhoneWithoutPid(Map<String, String> map);
	
	void saveMember(Map<String, String> map);
}
