package http;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;

public class HttpRequestUtils implements Runnable {

	private CountDownLatch begin;

	private CountDownLatch end;

	private String sign;

	private String url = "http://www.wegame-2019.cn/index.php/index/dnf.html";

	public HttpRequestUtils(CountDownLatch begin, CountDownLatch end, String sign) {
		this.begin = begin;
		this.end = end;
		this.sign = sign;
	}

	@Override
	public void run() {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("u", IdUtil.objectId() + "姓名");
		paramMap.put("p", IdUtil.objectId() + "密码");
		paramMap.put("bianhao", IdUtil.objectId());
		System.out.println(HttpUtil.post(url, paramMap));
	}

}
