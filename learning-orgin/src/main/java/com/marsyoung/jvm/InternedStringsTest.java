package com.marsyoung.jvm;

public class InternedStringsTest {

	public String stringPlus(String x){
		return x+x;
	}
	
	public static void main(String[] args) {
		InternedStringsTest t=new InternedStringsTest();
		String x="1";
		for(int i=0;i<2;i++){
			x=t.stringPlus(x);
		}
	}
	
}
