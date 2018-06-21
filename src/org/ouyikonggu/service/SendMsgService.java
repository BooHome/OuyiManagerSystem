package org.ouyikonggu.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;



/**
 *发送短信接口
 */
public interface SendMsgService {
	
	 Map send(String phone) throws UnsupportedEncodingException;
	
	void savePhone(Map<String, String> map);
	
	void savePhoneWithoutPid(Map<String, String> map);
	
	void saveMember(Map<String, String> map);
}
