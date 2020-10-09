package com.maven.chinapay7006.chinapay.util;


import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * ClassName: NetUtil <br/>
 * Function:  ADD FUNCTION. <br/>
 * date: 2017-6-8 下午2:44:20 <br/>
 *
 * @author dell
 * @version
 */
public class NetUtil {
	/**
	 * logger:(用一句话描述这个变量表示什么).
	 */
	private static final Logger logger = Logger.getLogger(NetUtil.class);

	public static String sendJson(String url, String reqContent,String charset) throws Exception{
		String result = "";
		// 字符集编码
		if (StringUtils.isBlank(charset)) {
			charset = "GBK";
		}

		// 创建http POST请求
		CloseableHttpClient httpclient = null;
		HttpPost httpPost = new HttpPost(url);

		CloseableHttpResponse response = null;

		try {
			httpPost.setHeader(HttpHeaders.CONTENT_ENCODING, charset);
			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset="+charset);

			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			customReqConf.setConnectTimeout(10000);
			customReqConf.setConnectTimeout(60000);

			StringEntity reqEntity = new StringEntity(reqContent,charset);
			httpPost.setEntity(reqEntity);
			if (url.startsWith("https")) {
				SSLContext context = buildSSLContext();
				// 执行Https请求
				httpclient = HttpClientBuilder.create().setDefaultRequestConfig(customReqConf.build())
						.setHostnameVerifier(new AllowAllHostnameVerifier())
						.setSslcontext(context).build();
			} else {
				httpclient = HttpClientBuilder.create().setDefaultRequestConfig(customReqConf.build()).build();
			}

			response = httpclient.execute(httpPost);

			if (response != null) {
				logger.info("返回状态码:"+response.getStatusLine().getStatusCode());
				// 判断返回状态是否为200
				if (response.getStatusLine().getStatusCode() == 200) {
					result = EntityUtils.toString(response.getEntity(),charset);
				}
			}else{
				logger.info("发送无结果");
			}

		} catch (Exception e) {
			logger.error("发送失败", e);
			throw e;
		} finally {
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(httpclient != null) {
				httpclient.close();
			}
		}

		return result;
	}

	/**
	 * 构建SSL上下文.
	 *
	 * @return  SSLContext
	 * @author cp
	 */
	private static SSLContext buildSSLContext() {
		try {
			SSLContext sslc = SSLContext.getInstance("TLS");
			sslc.init(null, createNonValidateTrustmanager(), null);

			return sslc;
		} catch (Exception ex) {
			logger.error("SSL上下文创建失败，请联系管理员.", ex);
		}
		return null;
	}

	/**
	 * 信任一切证书.
	 *
	 * @return  TrustManager[]
	 * @author cp
	 */
	private static TrustManager[] createNonValidateTrustmanager() {
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				}};
		return trustAllCerts;
	}

}
