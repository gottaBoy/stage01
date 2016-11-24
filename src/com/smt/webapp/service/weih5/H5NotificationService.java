package com.smt.webapp.service.weih5;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import edu.emory.mathcs.backport.java.util.concurrent.ConcurrentHashMap;

@Service
public class H5NotificationService {

	/**
	 * WEIXIN
	 */
	private static final String WEIXIN_API_URL = "";
	private static final String TEMPLATE_ID_APPLY = "";
	private static final String TEMPLATE_ID_INVITE = "";
	private static final String TEMPLATE_ID_REFUSE = "";
	private static final String TEMPLATE_ID_CLOSE_TASK = "";

	/**
	 * SMS
	 */
	private static final String SMS_API_URL = "";
	private static final String SMS_ACCOUNT = "";
	private static final String SMS_PASSWORD = "";

	/**
	 * cache for sms sending
	 */
	private static final Map<Integer, List<Date>> SMS_CACHE = new ConcurrentHashMap();

	public void sendSMS(H5NotificationTypeEnum type, Integer[] mobileNOs) {

		// check type and for each

		// validation phone no: no style and cache

		// clean cache, save cache and send

		// log if not normal
	}

	private String sendPostRequest(String url, Map<String, String> param) {
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);

			if (!CollectionUtils.isEmpty(param)) {
				NameValuePair[] data = new NameValuePair[param.size()];
				int index = 0;
				for (String key : param.keySet()) {
					NameValuePair valuePair = new NameValuePair(key, param.get(key));
					data[index] = valuePair;
				}
				method.setRequestBody(data);
			}

			int status = -1;
			status = client.executeMethod(method);
			String response = method.getResponseBodyAsString();
			method.releaseConnection();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String sendGetRequest(String url, Map<String, String> param) {
		try {
			HttpClient client = new HttpClient();
			HttpMethod method = new GetMethod(url);

			if (!CollectionUtils.isEmpty(param)) {
				HttpMethodParams params = client.getParams();
				for (String key : param.keySet()) {
					params.setParameter(key, param.get(key));
				}
				method.setParams(params);
			}

			int status = -1;
			status = client.executeMethod(method);
			String response = method.getResponseBodyAsString();
			method.releaseConnection();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void sendApplyNotification(int applyUserId, String applyUserName, int taskId, String taskName, String ownerOpenId) {
		System.out.println("sendApplyNotification...");

		System.out.println(String.format("用户 %s 应征了您的通告: %s", applyUserName, taskName));
		System.out.println("userId: " + applyUserId);
		System.out.println("taskId: " + taskId);
		System.out.println("openId: " + ownerOpenId);

		// nickName
		// userId, url
		// taskName
		// taskId, url

		// String response = sendPostRequest(WEIXIN_API_URL, new HashMap<String,
		// String>());
	}

	public void sendInviteNotification(int invitingUserId, String invitingUserName, String bizName, String roleName, String invitedUserOpenId) {
		System.out.println("sendInviteNotification...");

		// 用户 xxx 邀请您 xxx
		System.out.println(String.format("用户 %s 邀请了您", invitingUserName));
		System.out.println("invitingUserId: " + invitingUserId);
		System.out.println("bizName: " + bizName);
		System.out.println("roles: " + roleName);
		System.out.println("openId: " + invitedUserOpenId);

		// nickName
		// bizName

	}

	public void sendRefuseNotification(int taskId, String taskName, String roleName, String openId) {
		System.out.println("sendRefuseNotification...");

		String.format("您应征的通告 %s 的 %s 角色 已被创建人拒绝，请知晓。", taskName, roleName);

		System.out.println("taskId: " + taskId);
		System.out.println("openId: " + openId);

		// 您应征的通告，角色，已被拒绝＋拒绝理由？

	}

	public void send4TaskClose() {
		System.out.println("send4TaskClose...");

		//

		// task id
		// task name
		// role name
		// applied users' open id
		//

	}
}
