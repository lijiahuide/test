package http;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;

public class MyCallable implements Callable<Object> {

	private String taskNum;

	MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}

	private String url = "http://192.168.1.109:8088/hr80/base/frame/login.do?action=checkLogin";

	@Override
	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp1 = new Date();
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ACCOUNT", 0);
		paramMap.put("PASSWORD", IdUtil.objectId());
		paramMap.put("FM_SYS_ID", "hr80");
		System.out.println(HttpUtil.post(url, paramMap));
		System.out.println(paramMap);
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
	}

}
