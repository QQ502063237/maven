package com.maven.chinapay7006.chinapay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.maven.chinapay7006.chinapay.util.FormatUtil;
import com.maven.chinapay7006.chinapay.util.NetUtil;
import net.sf.json.JSONObject;

import com.chinapay.secss.SecssUtil;



public class SingleTrade {
	public static final String MERID = "000001904159858";//"000001809125044";
	public static final String VERSION = "20180808";//
	public static final String METHOD = "2";//2：单笔代付
	public static final String TRANSDATE = new SimpleDateFormat("yyyyMMdd").format(new Date());
	public static final String ORDERNO = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	public static final String CURYID = "CNY";
	public static final String TRANSAMT = "100";
	public static final String OPENBANK = FormatUtil.GBK2Unicode("0102");
	public static final String SUBBANK = FormatUtil.GBK2Unicode("交通银行虹口支行");
	public static final String UNIONBANKNO = "123456781234";
	public static final String PROV = FormatUtil.GBK2Unicode("上海");
	public static final String CITY = FormatUtil.GBK2Unicode("上海");
	public static final String FLAG = "00";
	public static final String PURPOSE = FormatUtil.GBK2Unicode("测试");
	public static final String PRIV1 = FormatUtil.GBK2Unicode("{\"test\":\"测试\"}");
	public static final String PAYMODE = "1";

	public static final String ACCOUNTNO = "6222021001123456789";
	public static final String ACCOUNTNAME = FormatUtil.GBK2Unicode("三毛");
	public static final String MOBILEFORBANK = "12345678901";

	public static final String busiProFlag = "01";
	public static final String relAccountNo = "6222011228881234567";
	public static final String relAccountName = FormatUtil.GBK2Unicode("上海银联电子支付");
	public static final String TERMTYPE = "07";
	public static final String terminalId = "8888888";
	public static final String userId = "test001";
	public static final String userRegisterTime = "20190618153300";
	public static final String userMail = "zhangsan@163.com";
	public static final String userMobile = "18321666789";
	public static final String diskSn = "99999999";
	public static final String mac = "12345678901";
	public static final String imei = "12345678901";
	public static final String ip = "127.0.0.1";
	public static final String wifimac = "12345678901";
	public static final String imsi = "12345678901";
	public static final String iccid = "12345678901";
	public static final String coordinates = "12345678901";
	public static final String baseStationSn = "12345678901";
	public static final String codeInputType = "01";
	public static final String desc = FormatUtil.GBK2Unicode("商品信息");

	public static final String url = "https://sfj-test.chinapay.com/dac/SingleTrade";

	public static void main(String[] args) {
		Map<String, String> encryptionMap = new HashMap<String, String>();
		encryptionMap.put("accountNo", ACCOUNTNO);
		encryptionMap.put("accountName", ACCOUNTNAME);
		encryptionMap.put("mobileForBank", MOBILEFORBANK);

		encryptionMap.put("busiProFlag", busiProFlag);
		encryptionMap.put("relAccountNo", relAccountNo);
		encryptionMap.put("relAccountName", relAccountName);
		encryptionMap.put("termType", TERMTYPE);
		encryptionMap.put("terminalId", terminalId);
		encryptionMap.put("userId", userId);
		encryptionMap.put("userRegisterTime", userRegisterTime);
		encryptionMap.put("userMail", userMail);
		encryptionMap.put("userMobile", userMobile);
		encryptionMap.put("diskSn", diskSn);
		encryptionMap.put("mac", mac);
		encryptionMap.put("imei", imei);
		encryptionMap.put("ip", ip);
		encryptionMap.put("wifimac", wifimac);
		encryptionMap.put("imsi", imsi);
		encryptionMap.put("iccid", iccid);
		encryptionMap.put("coordinates", coordinates);
		encryptionMap.put("baseStationSn", baseStationSn);
		encryptionMap.put("codeInputType", codeInputType);
		encryptionMap.put("desc", desc);

		JSONObject jsonObject = JSONObject.fromObject(encryptionMap);
		String encryption = jsonObject.toString();
		System.out.println("加密明文：" + encryption);
		SecssUtil secssUtil = new SecssUtil();
		secssUtil.init();
		secssUtil.encryptData(encryption);
		String encStr = secssUtil.getEncValue();
		System.out.println("加密值：" + encStr);

		Map<String, String> contentMap = new HashMap<String, String>();
		contentMap.put("encryption", encStr);
		contentMap.put("transDate", TRANSDATE);
		contentMap.put("orderNo", ORDERNO);
		contentMap.put("curyId", CURYID);
		contentMap.put("transAmt", TRANSAMT);
		contentMap.put("openBank", OPENBANK);
		contentMap.put("subBank", SUBBANK);
		contentMap.put("unionBankNo", UNIONBANKNO);
		contentMap.put("prov", PROV);
		contentMap.put("city", CITY);
		contentMap.put("purpose", PURPOSE);
		contentMap.put("priv1", PRIV1);
		contentMap.put("flag", FLAG);
		contentMap.put("payMode", PAYMODE);
		jsonObject = JSONObject.fromObject(contentMap);
		String content = jsonObject.toString();
		System.out.println("请求参数：" + content);

		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("merId", MERID);
		requestMap.put("version", VERSION);
		requestMap.put("method", METHOD);
		requestMap.put("content", content);

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
		System.out.println("priv1="+FormatUtil.Unicode2GBK(contMap.get("priv1")));

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
