package org.exmaple;

public class Hello {
	
	private int m;
	
	public Hello(){
		this.m = 0;
	}
	
	public Hello(int i){
		this.m = i;
	}
	
	public void inc(){
		m += 1;
	}
	
	public static void main(String[] args){
		System.out.println("hello world!");
	}
}