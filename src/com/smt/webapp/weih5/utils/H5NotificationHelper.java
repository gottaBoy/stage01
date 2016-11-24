package com.smt.webapp.weih5.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

public class H5NotificationHelper {

	private static final String SMS_URL = "http://222.73.117.158/msg/";
	private static final String SMS_ACCOUNT = "VIP-888";
	private static final String SMS_PASSWORD = "Tch789456";
	private static final boolean NEED_STATUS = false;

	private static final String SMS_CONTENT_LOGIN = "尊贵的第1舞台用户您好，您的注册验证码为 %s，请尽快输入。600秒后这条短信将自动去见马克思。";
	private static final String SMS_CONTENT_RETRIEVE = "忘记密码了？别慌，第1舞台机智地帮你，您的验证码 %s，赶紧输入来设置一个新密码吧。";

	public static int sendLoginValidCode(String phone, int validCode) {

		return sendSMS(phone, String.format(SMS_CONTENT_LOGIN, validCode));
	}

	public static int sendRetrieveValidCode(String phone, int validCode) {

		return sendSMS(phone, String.format(SMS_CONTENT_RETRIEVE, validCode));
	}

	private static int sendSMS(String phones, String msg) {

		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(SMS_URL, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("account", SMS_ACCOUNT), new NameValuePair("pswd", SMS_PASSWORD), new NameValuePair("mobile", phones), new NameValuePair("needstatus", String.valueOf(NEED_STATUS)), new NameValuePair("msg", msg), new NameValuePair("extno", null) });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				// InputStream in = method.getResponseBodyAsStream();
				// ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// byte[] buffer = new byte[1024];
				// int len = 0;
				// while ((len = in.read(buffer)) != -1) {
				// baos.write(buffer, 0, len);
				// }
				// System.out.println("RESULT: ");
				// System.out.println(URLDecoder.decode(baos.toString(),
				// "UTF-8"));
				return 1;
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
