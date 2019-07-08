package com.uusoft.atp.utils;
import java.util.Scanner;

public class Test {
	public static void main(String[] args){
		int i,j,m;
		Scanner s = new Scanner(System.in);
		System.out.println("Please input the first data :\r ");
		i = s.nextInt();
		System.out.println("Please input the first data :\r ");
		j = s.nextInt();
		m = deff(i,j);
		int n = (i*j)/m;
		System.out.println("最大公约数是： " + m);
		System.out.println("最小公倍数是： " + n);
	}
	
	public static int deff(int x, int y){
		int t;
		if (x < y) {
			t = x;
			x = y;
			y = t;
		}
		while (y != 0) {
			if (x == y)
				return x;
			else {
				int k = x%y;
				x = y;
				y = k;
			}
		}
		return x;
	}
}
