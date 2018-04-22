package top.arexstorm.sharing.service.email;

import java.util.Locale;
import java.util.ResourceBundle;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class DMUtils {

	 private static ResourceBundle bundle = ResourceBundle.getBundle("DM", Locale.CHINA);
	 private static final String accessKeyId = bundle.getString("accessKeyId");
	 private static final String accessKeySecret = bundle.getString("accessKeySecret");
	
	
	public static void main(String[] args) {
		SingleSendMailResponse response = sendEmail("sandiegoe@126.com", "hi", "what is the matter?");
		System.out.println(response.getEnvId());
		System.out.println(response.getRequestId());
	}
	
	/**
	 * 
	 * @param toAddress  发送人的emial地址，如果有多个，彼此间通过,隔开
	 * @param subject   发送email的主题
	 * @param content  发送email的内容
	 * @return
	 */
	private static SingleSendMailResponse sendEmail(String toAddress, String subject, String content) {
		SingleSendMailResponse response = null;
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	        IAcsClient client = new DefaultAcsClient(profile);
	        SingleSendMailRequest request = new SingleSendMailRequest();
	        try {
	         //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
	            request.setAccountName(bundle.getString("accountName"));
	            request.setFromAlias(bundle.getString("fromAlias"));
	            request.setAddressType(Integer.valueOf(bundle.getString("addressType")));
	            request.setTagName(bundle.getString("tagName"));
	            request.setReplyToAddress(bundle.getString("replyToAddress")==null ? false : "true".equals(bundle.getString("replyToAddress")) ? true : false);
	            request.setToAddress(toAddress);
	            request.setSubject(subject);
	            request.setHtmlBody(content);
	            response = client.getAcsResponse(request);
	            
	            String envId = response.getEnvId();
	            String requestId = response.getRequestId();
	            System.out.println("envId : " + envId);
	            System.out.println("requestId : " + requestId);
	        } catch (ClientException e) {
	            e.printStackTrace();
	        }
	        
	        return response;
	}
}
