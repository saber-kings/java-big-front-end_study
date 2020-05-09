package com.saberking.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendMsgCode {
	public static void sendCode(String phone, String code) {
		// RAM账号的AccessKey ID
		String accessKeyId = "";
		// RAM账号AccessKey Secret
		String accessSecret = "";
		// 短信签名名称
		String signName = "SaberKing";
		// 短信模板ID
		String templateCode = "SMS_187942355";
		// 创建DefaultAcsClient实例并初始化
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("SignName", signName);
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自己写的随机生成6位随机验证码
	 * 
	 * @return String
	 */
	public static String createRandomVcode() {
		// 验证码
		String code = "";
		for (int i = 0; i < 6; i++) {
			code = code + (int) (Math.random() * 9);
		}
		return code;
	}
}
