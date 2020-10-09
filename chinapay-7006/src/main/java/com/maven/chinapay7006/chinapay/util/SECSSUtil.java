package com.maven.chinapay7006.chinapay.util;


import com.chinapay.secss.SecssUtil;

/**
 * The Class SECSSUtil.
 */
public class SECSSUtil {

	public static void main(String[] args) throws Exception {

		SecssUtil secssUtil = new SecssUtil();
		secssUtil.init();
		secssUtil.verifyFile("E://000001809125044_20190401_094000.txt");
		System.out.println(secssUtil.getErrCode());

				
	}

}
