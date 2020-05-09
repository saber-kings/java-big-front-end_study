package com.test;

public class Compute {

	public int getPrime(int num) {
		for (int i = 2; i < num; i++) {
			if (num%i==0) {
				num = 0;
				break;
			}
		}
		return num;
	}

	public int getPrime2(int num) {
		int temp = (int) Math.sqrt(num);
		for (int i = 2; i <= temp; i++) {
			if (num%i==0) {
				num = 0;
				break;
			}
		}
		return num;
	}
	
	public long sum(int max) {
		long sum = 0;
		for (int i = 2; i <= max; i++) {
			sum += getPrime(i);
		}
		return sum;
	}

	public long sum2(int max) {
		long sum = 0;
		for (int i = 2; i <= max; i++) {
			if (i<=3) {
				sum += i;
			} else {
				sum += getPrime2(i);
			}
		}
		return sum;
	}
}
