package com.jacen.study.v2.sample;

public class CCBean extends CBean {

	public CCBean(String name) {
		super(name);
	}

	public void init() {
		System.out.println("ABean.init() 执行了");
	}
}
