package com.maven.chinapay7006.chinapay.service;

import java.util.HashMap;
import java.util.Map;


import com.chinapay.secss.SecssUtil;
import com.maven.chinapay7006.chinapay.util.FormatUtil;
import com.maven.chinapay7006.chinapay.util.NetUtil;
import net.sf.json.JSONObject;

/**
 * @author
 */
public class BalanceQuery{

	public static void main(String[] args) throws Exception {
		//查询
		new BalanceQuery().query();
	}

	@SuppressWarnings({ "unchecked" })
	public void query() throws Exception{
		String merId = "000001809125044";//商户号
		String queryDate = "20190409"; // 8位

		SecssUtil secssUtil = new SecssUtil();
		secssUtil.init();

		Map<String,String> signMap = new HashMap<String,String>();
		signMap.put("merId", merId);
		signMap.put("queryDate", queryDate);
		secssUtil.sign(signMap);
		String chkValue = secssUtil.getSign();
		signMap.put("chkValue", chkValue);
		System.out.println("签名内容：" + chkValue);

		String data = JSONObject.fromObject(signMap).toString();
		System.out.println("data=" + data);

		String url = "https://sfj-test.chinapay.com/sfjinterface/DepositHisBalanceQueryServlet";
		String result = NetUtil.sendJson(url, data, "GBK");
		System.out.println("result=" + result);

		if(result != null && result.length() > 0) {
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap = (Map<String, String>) JSONObject.fromObject(result);
			secssUtil.verify(resultMap);
			String errorCode = secssUtil.getErrCode();

			if (!"00".equals(errorCode)) {
				System.out.println("验签失败");
			} else {
				System.out.println("验签成功");
			}

			System.out.println("responseCode=" + resultMap.get("responseCode"));
			System.out.println("responseMsg="+ FormatUtil.Unicode2GBK(resultMap.get("responseMsg")));
			System.out.println("msgCode=" + resultMap.get("msgCode"));
			System.out.println("msgDesc="+FormatUtil.Unicode2GBK(resultMap.get("msgDesc")));
			System.out.println("余额：" + resultMap.get("balance"));
		}
	}

}
