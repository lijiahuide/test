package com.fanshe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FanShe {

	private String name;

	private void hello(String name) {
		System.out.println("hello " + name);
	}

	public int calc(int num) {
		return num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws Exception {
		FanShe fanShe = (FanShe) Class.forName("com.fanshe.FanShe").newInstance();
		fanShe.setName("张三");
		// 获取hello方法
		Method helloMethod = FanShe.class.getDeclaredMethod("hello", String.class);
		// 可以获取私有的方法
		helloMethod.setAccessible(true);
		helloMethod.invoke(fanShe, "word!");
		Method calcMethod = FanShe.class.getDeclaredMethod("calc", int.class);
		Object calc = calcMethod.invoke(fanShe, 2);
		System.out.println(calc);
		Field field = FanShe.class.getDeclaredField("name");
		// 获取属性字段
		field.setAccessible(true);
		// 设置属性字段的值
		field.set(fanShe, "李四");
		System.out.println(field.get(fanShe));
	}
}
