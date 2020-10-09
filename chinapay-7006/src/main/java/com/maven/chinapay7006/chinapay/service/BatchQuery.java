package com.maven.chinapay7006.chinapay.service;
import java.util.HashMap;
import java.util.Map;

import com.maven.chinapay7006.chinapay.util.FormatUtil;
import com.maven.chinapay7006.chinapay.util.NetUtil;
import net.sf.json.JSONObject;

import com.chinapay.secss.SecssUtil;



public class BatchQuery {
	public static final String MERID = "000001809125044";//"000001809125044";
	public static final String VERSION = "20180808";//
	public static final String FILENAME = "";//


	public static final String url = "https://sfj-test.chinapay.com/dac/FileStatQuery";


	public static void main(String[] args) {

		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("merId", MERID);
		requestMap.put("version", VERSION);
		requestMap.put("fileName", FILENAME);

		SecssUtil secssUtil = new SecssUtil();
		boolean b = secssUtil.init();
		secssUtil.sign(requestMap);
		String signStr = secssUtil.getSign();
		System.out.println("签名值：" + signStr);
		requestMap.put("chkValue", signStr);
		JSONObject jsonObject = JSONObject.fromObject(requestMap);
		String request = jsonObject.toString();
		System.out.println("请求总参数：" + request);

		String result = "";
		try {
			result = NetUtil.sendJson(url, request, "GBK");
		} catch (Exception e) {
			System.out.println("发送异常：" + e);
		}
		System.out.println("result=" + result);

		Map<String, String> retMap = (Map<String, String>)JSONObject.fromObject(result);
		secssUtil.verify(retMap);
		String errorCode = secssUtil.getErrCode();

		if (!"00".equals(errorCode)) {
			System.out.println("验签失败");
		} else {
			System.out.println("验签成功");
		}

		System.out.println("responseCode=" + retMap.get("responseCode"));
		System.out.println("responseMsg="+ FormatUtil.Unicode2GBK(retMap.get("responseMsg")));
		System.out.println("msgCode=" + retMap.get("msgCode"));
		System.out.println("msgDesc="+FormatUtil.Unicode2GBK(retMap.get("msgDesc")));
		System.out.println("merId=" + retMap.get("merId"));
		System.out.println("fileName=" + retMap.get("fileName"));
		System.out.println("download=" + retMap.get("download"));
	}
}
