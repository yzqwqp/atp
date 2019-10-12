package com.uusoft.atp.utils.RandomVar;

import java.util.Random;

public class Vehicle {
	
    public static void main(String[] args) {
            System.out.println(getVehicleNum());
    }

    // 车牌号的组成一般为：省份+地区代码+5位数字/字母
    public static String getVehicleNum() {

        char[] provinceAbbr = { // 省份简称 4+22+5+3
                '京', '津', '沪', '渝',
                '冀', '豫', '云', '辽', '黑', '湘', '皖', '鲁', '苏', '浙', '赣',
                '鄂', '甘', '晋', '陕', '吉', '闽', '贵', '粤', '青', '川', '琼',
                '宁', '新', '藏', '桂', '蒙'
        };
        //,'港', '澳', '台'
        String alphas1 = "QWERTYUIOPASDFGHJKLZXCVBNM"; // 26个字母 
        String alphas2 = "1234567890"; //  10个数字

        Random random = new Random(); // 随机数生成器

        String carID = "";

        // 省份+地区代码+·  如 湘A· 这个点其实是个传感器，不过加上美观一些
        carID += provinceAbbr[random.nextInt(31)]; // 注意：分开加，因为加的是2个char
        carID += alphas1.charAt(random.nextInt(26));

        // 5位数字/字母
        for (int i = 0; i < 4; i++) {
            carID += alphas2.charAt(random.nextInt(10));
        }
        carID += alphas1.charAt(random.nextInt(26));
        return carID;
    }
}