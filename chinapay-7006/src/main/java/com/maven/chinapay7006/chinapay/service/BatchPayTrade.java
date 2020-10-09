package com.maven.chinapay7006.chinapay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chinapay.secss.SecssUtil;
import com.maven.chinapay7006.chinapay.util.FormatUtil;
import com.maven.chinapay7006.chinapay.util.NetUtil;
import net.sf.json.JSONObject;




public class BatchPayTrade {
	public static final String MERID = "000001904159858";//"000001809125044";
	public static final String VERSION = "20180808";//

	public static final String MOBILEFORBANK = "12345678901";

	public static final String busiProFlag = "01";
	public static final String relAccountNo = "6222011228881234567";
	public static final String relAccountName = "上海银联电子支付";
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
	public static final String Desc = "商品信息";


	public static final String url = "https://sfj-test.chinapay.com/dac/BatchPayTrade";

	public static void main(String[] args) throws Exception {

		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("HHmmss");
		Date dt = new Date();

		String MerSeqId = sf2.format(dt); // 批次号
		String TransDate = sf1.format(dt); // 交易日期
		String fileName = MERID+"_"+TransDate+"_"+MerSeqId+".txt";

		String fileContent = MERID + "|" + MerSeqId + "|"+3+"|3000";
		System.out.println("文件头：" + fileContent);
		fileContent += "\r\n"
				+ TransDate
				+ "|"
				+ TransDate + MerSeqId+1
				+ "|6228481190350963516|张三1|CNY|1000|民生银行|浦东支行||上海|上海|00|付款1|{\"test\":\"测试\"}|1||||07|||||||||||||||||";
		fileContent += "\r\n"
				+ TransDate
				+ "|"
				+ TransDate + MerSeqId + 2
				+ "|6228481190350963516|张三2|CNY|1000|民生银行|浦东支行||上海|上海|00|付款1|{\"test\":\"测试\"}|1||||07|||||||||||||||||";
		fileContent += "\r\n"
				+ TransDate
				+ "|"
				+ TransDate + MerSeqId + 3
				+ "|6228481190350963516|张三3|CNY|1000|民生银行|浦东支行||上海|上海|00|付款1|{\"test\":\"测试\"}|1||||07|||||||||||||||||";


		System.out.println(fileContent);

		SecssUtil secssUtil = new SecssUtil();
		boolean b = secssUtil.init();
		String contentStr = secssUtil.encodeEnvelope(fileContent.getBytes("GBK"));

		Map<String,String> signMap = new HashMap<String,String>();
		signMap.put("merId", MERID);
		signMap.put("fileContent", contentStr);
		signMap.put("version", VERSION);
		signMap.put("fileName", fileName);
		secssUtil.sign(signMap);
		String chkValue = secssUtil.getSign();
		signMap.put("chkValue", chkValue);

		try {
			String data = JSONObject.fromObject(signMap).toString();
			String result = NetUtil.sendJson(url, data, "GBK");
			System.out.println("返回数据" + "[" + result + "]");

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

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}
}
