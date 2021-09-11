package com.jacen.study.v1.sample;


public class ABeanFactory {

	public static ABean getABean() {
		return new ABean();
	}

	public ABean getABean2() {
		return new ABean();
	}
}
