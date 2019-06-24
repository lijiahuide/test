package com;

public class TestBase {

	public static void main(String[] args) {
		Base sBase = new Base();
		Base ss = new Base("ss");
		new Base();
		new Base();
		System.out.println(ss.getCount());
	}
	
}
