package org.ouyikonggu.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.ouyikonggu.dao.MemberDAO;
import org.ouyikonggu.moudel.Member;
import org.ouyikonggu.service.SendMsgService;
import org.ouyikonggu.util.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

/**
 *发送短信接口
 */
@Service
public class SendMsgServiceImpl implements SendMsgService{
	
	@Autowired
	MemberDAO memberDAO;

	/**
	 * 执行http接口请求发送短信
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@Override
	public  Map send(String phone) throws UnsupportedEncodingException {
			//获取四位数的随机数
			Map m = new HashMap();
			Random r = new Random();
			int code = r.nextInt(9999-1000+1)+1000;
			Map<String, String> mapParam = new HashMap<String, String>();
			mapParam.put("dc", "15");
			mapParam.put("un", "800004");
			mapParam.put("pw", URLEncoder.encode("8040423", "utf-8"));
			mapParam.put("sm",  URLEncoder.encode(("【急贷之家】您的验证码为："+code), "utf-8"));
			mapParam.put("da", phone);
			mapParam.put("tf", "3");
			mapParam.put("rf", "2");
			String pathUrl = "http://61.129.57.37:7891/mt";
			String sendResult = HttpRequest.sendPost(pathUrl, mapParam);
			JSONObject jso = JSONObject.fromObject(sendResult);
			String s = jso.get("success").toString();
			if(s.equals("true")) {
				m.put("result", 1);
				m.put("msg", "验证码发送成功,请稍等");
				m.put("code", code);
			}else {
				m.put("result", 0);
				m.put("msg", "验证码发送失败");
			}
			
			return m;
	}

	/**
	 * 保存用户信息
	 */
	@Override
	public void savePhone(Map<String, String> map) {
		String phone = map.get("phone");
		String pid = map.get("pid");
		Member mem = new Member();
		mem.setMProductID(Integer.parseInt(pid));
		mem.setMTel(phone);
		int row2 = memberDAO.add(mem);
	}

	@Override
	public void saveMember(Map<String, String> map) {
		String phone = map.get("phone");
		if(phone!=null && phone.length()==11) {
			String pid = map.get("pid");
			Member mem = new Member();
			mem.setMProductID(Integer.parseInt(pid));
			mem.setMTel(phone);
			int row2 = memberDAO.add(mem);
		}
	}

	@Override
	public void savePhoneWithoutPid(Map<String, String> map) {
		String phone = map.get("phone");
		if(phone!=null && phone.length()==11) {
			Member mem = new Member();
			mem.setMTel(phone);
			int row2 = memberDAO.add(mem);
		}
	}
	
}
