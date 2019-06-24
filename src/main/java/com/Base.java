package com;

public class Base {

	 static{
	        System.out.println("我是静态代码块");
	    }
	
	private static int COUNT_LOAD = 0;
	{
		System.out.println("执行代码框");
		COUNT_LOAD++;
	}

	public Base() {
		// COUNT_LOAD++;
	}

	public Base(String ss) {
		this();
	}

	public int getCount() {
		return COUNT_LOAD;
	}

}
