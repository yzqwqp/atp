package com.uusoft.atp.utils.RandomVar;

public class RandomPersonInfo {

	public static void main(String[] args) {
		for (int i = 0; i < 200; i++)
//		System.out.println(getRandomPersonChineseName());
		System.out.println(getRandomPersonIdNum());
//		System.out.println(getRandomVehicleNum());
	}
	
	public static String getRandomPersonIdNum(){
		return IdNum.getIdNo();
	}
	
	public static String getRandomPersonChineseName(){
		return ChineseName.getName();
	}
	
	public static String getRandomVehicleNum(){
		return Vehicle.getVehicleNum();
	}

}
