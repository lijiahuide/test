package dynamic.proxy;

import org.junit.Test;

public class ProxyTest {

	@Test
	public void testProxy() {
		// 实例化目标对象
		UserService userService = new UserServiceImpl();
		// 实例化InvocationHandler
		MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
		// 根据目标对象生成代理对象
		UserService proxy = (UserService) invocationHandler.getProxy();
		// 调用代理对象的方法
		proxy.add();
	}

	@Test
	public void testGenerateProxyClass() {
		ProxyGeneratorUtils.writeProxyClassToHardDisk("E:/$Proxy11.class");
	}

}
