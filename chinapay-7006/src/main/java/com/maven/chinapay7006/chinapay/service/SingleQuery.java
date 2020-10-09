package com.maven.chinapay7006.chinapay.service;

import java.util.HashMap;
import java.util.Map;

import com.chinapay.secss.SecssUtil;
import com.maven.chinapay7006.chinapay.util.FormatUtil;
import com.maven.chinapay7006.chinapay.util.NetUtil;
import net.sf.json.JSONObject;




public class SingleQuery {
	public static final String MERID = "000001809125044";
	public static final String VERSION = "20180808";
	public static final String METHOD = "3";//2：单笔代付，3：单笔查询
	public static final String ORDERNO = "20190626132629";
	public static final String TRANSDATE = "20190626";

	public static final String url = "https://sfj-test.chinapay.com/dac/SingleQuery";

	public static void main(String[] args) {
		Map<String, String> contentMap = new HashMap<String, String>();
		contentMap.put("orderNo", ORDERNO);
		contentMap.put("transDate", TRANSDATE);
		JSONObject jsonObject = JSONObject.fromObject(contentMap);
		String content = jsonObject.toString();
		System.out.println("请求参数：" + content);

		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("merId", MERID);
		requestMap.put("version", VERSION);
		requestMap.put("method", METHOD);
		requestMap.put("content", content);

		SecssUtil secssUtil = new SecssUtil();
		secssUtil.init();
		secssUtil.sign(requestMap);
		String signStr = secssUtil.getSign();
		System.out.println("签名值：" + signStr);
		requestMap.put("chkValue", signStr);
		jsonObject = JSONObject.fromObject(requestMap);
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
		System.out.println("responseMsg="+FormatUtil.Unicode2GBK(retMap.get("responseMsg")));
		System.out.println("msgCode=" + retMap.get("msgCode"));
		System.out.println("msgDesc="+FormatUtil.Unicode2GBK(retMap.get("msgDesc")));
		String contMsg = retMap.get("content");

		Map<String, String> contMap = (Map<String, String>)JSONObject.fromObject(contMsg);
		System.out.println("transDate=" + contMap.get("transDate"));
		System.out.println("orderNo=" + contMap.get("orderNo"));
		System.out.println("curyId=" + contMap.get("curyId"));
		System.out.println("transAmt=" + contMap.get("transAmt"));
		System.out.println("purpose="+FormatUtil.Unicode2GBK(contMap.get("purpose")));
		System.out.println("priv1="+ FormatUtil.Unicode2GBK(contMap.get("priv1")));

		String encMsg = contMap.get("encryption");

		secssUtil.decryptData (encMsg);
		if(!"00".equals(secssUtil.getErrCode())){
			System.out.println("敏感信息解密发生错误，错误信息为"+secssUtil.getErrMsg());
			return;
		}
		String decValue = secssUtil.getDecValue();
		System.out.println("敏感信息解密成功,敏感信息解密结果:"+decValue);
		Map<String, String> decMap = (Map<String, String>)JSONObject.fromObject(decValue);
		System.out.println("accountNo="+decMap.get("accountNo"));
		System.out.println("accountName="+FormatUtil.Unicode2GBK(decMap.get("accountName")));
	}

}
