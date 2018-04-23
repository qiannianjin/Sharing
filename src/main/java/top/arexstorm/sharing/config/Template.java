package top.arexstorm.sharing.config;

public interface Template {

	public static String EmailTemplate = "{nickname},您好<br/>感谢您注册Sharing共享信息平台,请点击以下链接激活您的邮箱:<br/>"
			+ "<a href='http://www.arexstorm.top:9000/email/verify?userid={userid}&code={code}&email={email}&type={type}'>激活邮箱</a>"
			+ "<br/>如果以上链接无法访问，请将该网址复制并粘贴至浏览器窗口直接访问。";
}
