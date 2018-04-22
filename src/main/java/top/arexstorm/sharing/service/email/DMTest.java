package top.arexstorm.sharing.service.email;

import java.rmi.ServerException;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class DMTest {

	 private static final String accessKeyId = "LTAIU9GVkfu8Vno2";
	 private static final String accessKeySecret = "I0QTWwcmQAnm7nvwTcL2WcTxLwEhWd";
	
	public static void main(String[] args) {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
    // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        //try {
        //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        //} catch (ClientException e) {
        //e.printStackTrace();
        //}
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
         //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("arexstorm@hr.arexstorm.top");
            request.setFromAlias("arexstorm");
            request.setAddressType(1);
            request.setTagName("控制台创建的标签");
            request.setReplyToAddress(true);
            request.setToAddress("sandiegoe@126.com");
            request.setSubject("登录邮件");
            request.setHtmlBody("hi, 欢迎登录，您的验证码为：171010");
            SingleSendMailResponse response = client.getAcsResponse(request);
            
            String envId = response.getEnvId();
            String requestId = response.getRequestId();
            System.out.println("envId : " + envId);
            System.out.println("requestId : " + requestId);
        } catch (ClientException e) {
            e.printStackTrace();
        }
	}
}
