package com.jacen.study.v2.sample;

public class ABeanFactory {

	public static ABean getABean(String name, CBean cb) {
		return new ABean(name, cb);
	}

	public ABean getABean2(String name, CBean cb) {
		return new ABean(name, cb);
	}
}
