package com.uusoft.atp.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GetUrlPic {
    /*
file-1.book118.com/view3/M02/14/3A/wKh2BVzUFJGACZWxAAFMn2F20u4996.png
file-1.book118.com/view1/M05/15/38/wKh2BVzUFJGAViPtAAIdHdotzHY098.png
file-1.book118.com/view3/M02/14/3A/wKh2BVzUFJGAfqJsAAH1CgjPdpI600.png
file-1.book118.com/view3/M01/14/3A/wKh2BVzUFJGAFB1gAAEf1sR8bqs540.png
file-1.book118.com/view3/M00/14/3A/wKh2BVzUFJGAZNXlAAFNi1swGN4186.png
file-1.book118.com/view3/M03/14/3A/wKh2BVzUFJGAFgzsAAFAHuZ9UZ8336.png
file-1.book118.com/view2/M05/15/0F/wKh2BVzUFJGAdZ4uAAGza7fJDUk138.png
file-1.book118.com/view3/M01/14/3A/wKh2BVzUFJGAQojrAAG4IbGOidM197.png
file-1.book118.com/view3/M05/14/3A/wKh2BVzUFJGAGtHiAAGIRl51bYo904.png
file-1.book118.com/view3/M01/16/15/wKh2BVzUFceAd97LAACrx4rc31Q381.png
file-1.book118.com/view2/M01/16/2A/wKh2BVzUFceAd7ShAAE6O3wGc6Q447.png
file-1.book118.com/view3/M02/16/15/wKh2BVzUFceABKuvAAFJWgiyK4o823.png
file-1.book118.com/view2/M04/3D/28/wKh2BV0qnc2ACfxUAAGum7XFo-s756.png
file-1.book118.com/view3/M05/36/0A/wKh2BV0qnc2AMYIpAAD4x6HmBQY595.png
file-1.book118.com/view1/M05/3F/10/wKh2BV0qnc2AeYsWAAHVhto4HVo847.png
file-1.book118.com/view6/M01/3B/21/wKh2BF0qnc2AOzlKAAE1PdPV_io337.png
file-1.book118.com/view2/M03/3D/28/wKh2BV0qnc2ATaTNAADuz4cdrxo565.png
file-1.book118.com/view1/M00/3F/10/wKh2BV0qnc2ASst8AAI-IRKyUBA700.png
file-1.book118.com/view4/M04/3B/2F/wKh2Cl0qneOAGFg2AAF_LduJmUE863.png
file-1.book118.com/view4/M05/3C/1A/wKh2CV0qneSAIpihAAFvDChyVTc558.png
file-1.book118.com/view6/M00/3B/22/wKh2BF0qneWASKkZAADrrl_x8bA164.png
file-1.book118.com/view2/M02/3D/29/wKh2BV0qneWAELoIAAHRBvxaVkQ236.png
file-1.book118.com/view1/M01/0B/28/wKh2BF0qneWAFuxpAAH82Jv7wM8586.png
file-1.book118.com/view3/M04/36/0B/wKh2BV0qneWAejcfAAF1VMOdw0o900.png
file-1.book118.com/view6/M05/3B/33/wKh2BF0qn5CAKatiAADjoKLkZII588.png
file-1.book118.com/view4/M05/3C/00/wKh2Cl0qn5CAJq2MAADSH304X5w470.png
file-1.book118.com/view6/M01/3B/33/wKh2BF0qn5KAPHd1AAENt5tKaz8220.png
file-1.book118.com/view1/M02/0B/39/wKh2BF0qn5GARwmtAAGRygp_P_8579.png
file-1.book118.com/view5/M00/3D/1E/wKh2BF0qn5GAf4NnAAE6CtrKhZQ107.png
file-1.book118.com/view5/M01/3D/1D/wKh2CV0qn5GAMaIhAAEqv8CoGrg750.png
file-1.book118.com/view1/M01/3F/12/wKh2BV0qngOABkn8AADj83TeEuc009.png
file-1.book118.com/view4/M04/3B/30/wKh2Cl0qngOAYDWbAAEOrGwLrJI392.png
file-1.book118.com/view6/M03/3B/24/wKh2BF0qngOAFHtdAAHBVR_rxy4765.png
file-1.book118.com/view5/M02/3D/0E/wKh2CV0qngOAJHhBAAFDps4QtI4757.png
file-1.book118.com/view5/M02/3D/0F/wKh2BF0qngOAVaJZAAGerVFW5KA173.png
file-1.book118.com/view3/M00/36/0D/wKh2BV0qngOAQmsFAAF_G7YTqsM540.png
file-1.book118.com/view4/M01/3C/2C/wKh2CV0qn6GAPz0bAAGb6jPDOFg481.png
file-1.book118.com/view3/M03/13/35/wKh2BF0qn6GAQCJwAACki_H3PKk612.png
file-1.book118.com/view2/M05/3D/3B/wKh2BV0qn6aATuFRAAHWZhyCAB4255.png
file-1.book118.com/view2/M02/0B/1B/wKh2BF0qn6WAIZVzAAE24J3mLQM814.png
file-1.book118.com/view6/M00/3B/08/wKh2Cl0qn6WARmvIAADUrLCr0wA665.png
file-1.book118.com/view1/M00/3F/23/wKh2BV0qn6aAd0nDAAGHw0yvrw0644.png
file-1.book118.com/view3/M02/13/34/wKh2BF0qn5uAYzciAAEStfCqMbQ265.png
file-1.book118.com/view4/M03/3C/2C/wKh2CV0qn5uAZDzOAAFzGo-XMDI371.png
file-1.book118.com/view2/M03/3D/3B/wKh2BV0qn5uAQCbiAAGLblrB01U038.png
file-1.book118.com/view4/M04/3C/2C/wKh2CV0qn5uAHOVqAAG3JQAqD7g767.png
file-1.book118.com/view6/M00/3B/08/wKh2Cl0qn5uAb5ASAAClXYB-OAY682.png
file-1.book118.com/view1/M05/0B/3A/wKh2BF0qn5yASF9aAAJPQul8KNk415.png
file-1.book118.com/view4/M02/3D/08/wKh2CV0qomGAYVSaAAG1i0ync3U874.png
file-1.book118.com/view1/M05/0C/16/wKh2BF0qomKAdVJMAAGU7flPe9Y310.png
file-1.book118.com/view3/M04/36/39/wKh2BV0qomKAPJOLAAEMgQG5qOo529.png
file-1.book118.com/view4/M02/3C/1D/wKh2Cl0qomKAWUNaAABR2gLspVE439.png
file-1.book118.com/view3/M04/14/10/wKh2BF0qomKADVTYAAAjNnIN7xc848.png
file-1.book118.com/view1/M05/3F/3F/wKh2BV0qomKATFgPAAAMgm1s14g747.png
file-1.book118.com/view6/M05/3E/0A/wKh2Cl0qt9mAGjx4AAFZCf8vjxo659.png
file-1.book118.com/view5/M02/00/22/wKh2CV0qt9yAPwxuAAEG4FAW5WM388.png
view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYr1WHca1ADcEg=
view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYruX2uZBEtOEw=
view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYrSejOjAAS_XA=
file-1.book118.com/view2/M05/01/00/wKh2BV0qt9yACYY0AAJ4HR0IBbI824.png
file-1.book118.com/view3/M04/36/1E/wKh2BV0qn7WAcwwyAAD4rtbaF_0405.png
file-1.book118.com/view6/M01/3B/09/wKh2Cl0qn7WABJwBAAIp8rrP_8k687.png
file-1.book118.com/view1/M03/0B/3A/wKh2BF0qn7SAS2M5AABaUO3ht0c802.png
file-1.book118.com/view6/M00/3B/34/wKh2BF0qn7WAfF_0AAERZgc5srg467.png
file-1.book118.com/view4/M02/3C/01/wKh2Cl0qn7WAQjEQAAFuzlBysWA379.png
file-1.book118.com/view4/M03/3C/01/wKh2Cl0qn7aAByNdAAE49sO64v8960.png
file-1.book118.com/view2/M00/0E/12/wKh2BF0qthyACOY1AAGkDIYeRbw102.png
file-1.book118.com/view1/M05/02/19/wKh2BV0qthyALV_sAAE61gOUJlw032.png
     */
    public static void main(String[] args) throws Exception {  
    	Map<String,String> listStr = new LinkedHashMap<String,String>();
    	listStr.put("01", "file-1.book118.com/view3/M02/14/3A/wKh2BVzUFJGACZWxAAFMn2F20u4996.png");
    	listStr.put("02", "file-1.book118.com/view1/M05/15/38/wKh2BVzUFJGAViPtAAIdHdotzHY098.png");
    	listStr.put("03", "file-1.book118.com/view3/M02/14/3A/wKh2BVzUFJGAfqJsAAH1CgjPdpI600.png");
    	listStr.put("04", "file-1.book118.com/view3/M01/14/3A/wKh2BVzUFJGAFB1gAAEf1sR8bqs540.png");
    	listStr.put("05", "file-1.book118.com/view3/M00/14/3A/wKh2BVzUFJGAZNXlAAFNi1swGN4186.png");
    	listStr.put("06", "file-1.book118.com/view3/M03/14/3A/wKh2BVzUFJGAFgzsAAFAHuZ9UZ8336.png");
    	listStr.put("07", "file-1.book118.com/view2/M05/15/0F/wKh2BVzUFJGAdZ4uAAGza7fJDUk138.png");
    	listStr.put("08", "file-1.book118.com/view3/M01/14/3A/wKh2BVzUFJGAQojrAAG4IbGOidM197.png");
    	listStr.put("09", "file-1.book118.com/view3/M05/14/3A/wKh2BVzUFJGAGtHiAAGIRl51bYo904.png");
    	listStr.put("10", "file-1.book118.com/view3/M01/16/15/wKh2BVzUFceAd97LAACrx4rc31Q381.png");
    	listStr.put("11", "file-1.book118.com/view2/M01/16/2A/wKh2BVzUFceAd7ShAAE6O3wGc6Q447.png");
    	listStr.put("12", "file-1.book118.com/view3/M02/16/15/wKh2BVzUFceABKuvAAFJWgiyK4o823.png");
    	listStr.put("13", "file-1.book118.com/view2/M04/3D/28/wKh2BV0qnc2ACfxUAAGum7XFo-s756.png");
    	listStr.put("14", "file-1.book118.com/view3/M05/36/0A/wKh2BV0qnc2AMYIpAAD4x6HmBQY595.png");
    	listStr.put("15", "file-1.book118.com/view1/M05/3F/10/wKh2BV0qnc2AeYsWAAHVhto4HVo847.png");
    	listStr.put("16", "file-1.book118.com/view6/M01/3B/21/wKh2BF0qnc2AOzlKAAE1PdPV_io337.png");
    	listStr.put("17", "file-1.book118.com/view2/M03/3D/28/wKh2BV0qnc2ATaTNAADuz4cdrxo565.png");
    	listStr.put("18", "file-1.book118.com/view1/M00/3F/10/wKh2BV0qnc2ASst8AAI-IRKyUBA700.png");
    	listStr.put("19", "file-1.book118.com/view4/M04/3B/2F/wKh2Cl0qneOAGFg2AAF_LduJmUE863.png");
    	listStr.put("20", "file-1.book118.com/view4/M05/3C/1A/wKh2CV0qneSAIpihAAFvDChyVTc558.png");
    	listStr.put("21", "file-1.book118.com/view6/M00/3B/22/wKh2BF0qneWASKkZAADrrl_x8bA164.png");
    	listStr.put("22", "file-1.book118.com/view2/M02/3D/29/wKh2BV0qneWAELoIAAHRBvxaVkQ236.png");
    	listStr.put("23", "file-1.book118.com/view1/M01/0B/28/wKh2BF0qneWAFuxpAAH82Jv7wM8586.png");
    	listStr.put("24", "file-1.book118.com/view3/M04/36/0B/wKh2BV0qneWAejcfAAF1VMOdw0o900.png");
    	listStr.put("25", "file-1.book118.com/view6/M05/3B/33/wKh2BF0qn5CAKatiAADjoKLkZII588.png");
    	listStr.put("26", "file-1.book118.com/view4/M05/3C/00/wKh2Cl0qn5CAJq2MAADSH304X5w470.png");
    	listStr.put("27", "file-1.book118.com/view6/M01/3B/33/wKh2BF0qn5KAPHd1AAENt5tKaz8220.png");
    	listStr.put("28", "file-1.book118.com/view1/M02/0B/39/wKh2BF0qn5GARwmtAAGRygp_P_8579.png");
    	listStr.put("29", "file-1.book118.com/view5/M00/3D/1E/wKh2BF0qn5GAf4NnAAE6CtrKhZQ107.png");
    	listStr.put("30", "file-1.book118.com/view5/M01/3D/1D/wKh2CV0qn5GAMaIhAAEqv8CoGrg750.png");
    	listStr.put("31", "file-1.book118.com/view1/M01/3F/12/wKh2BV0qngOABkn8AADj83TeEuc009.png");
    	listStr.put("32", "file-1.book118.com/view4/M04/3B/30/wKh2Cl0qngOAYDWbAAEOrGwLrJI392.png");
    	listStr.put("33", "file-1.book118.com/view6/M03/3B/24/wKh2BF0qngOAFHtdAAHBVR_rxy4765.png");
    	listStr.put("34", "file-1.book118.com/view5/M02/3D/0E/wKh2CV0qngOAJHhBAAFDps4QtI4757.png");
    	listStr.put("35", "file-1.book118.com/view5/M02/3D/0F/wKh2BF0qngOAVaJZAAGerVFW5KA173.png");
    	listStr.put("36", "file-1.book118.com/view3/M00/36/0D/wKh2BV0qngOAQmsFAAF_G7YTqsM540.png");
    	listStr.put("37", "file-1.book118.com/view4/M01/3C/2C/wKh2CV0qn6GAPz0bAAGb6jPDOFg481.png");
    	listStr.put("38", "file-1.book118.com/view3/M03/13/35/wKh2BF0qn6GAQCJwAACki_H3PKk612.png");
    	listStr.put("39", "file-1.book118.com/view2/M05/3D/3B/wKh2BV0qn6aATuFRAAHWZhyCAB4255.png");
    	listStr.put("40", "file-1.book118.com/view2/M02/0B/1B/wKh2BF0qn6WAIZVzAAE24J3mLQM814.png");
    	listStr.put("41", "file-1.book118.com/view6/M00/3B/08/wKh2Cl0qn6WARmvIAADUrLCr0wA665.png");
    	listStr.put("42", "file-1.book118.com/view1/M00/3F/23/wKh2BV0qn6aAd0nDAAGHw0yvrw0644.png");
    	listStr.put("43", "file-1.book118.com/view3/M02/13/34/wKh2BF0qn5uAYzciAAEStfCqMbQ265.png");
    	listStr.put("44", "file-1.book118.com/view4/M03/3C/2C/wKh2CV0qn5uAZDzOAAFzGo-XMDI371.png");
    	listStr.put("45", "file-1.book118.com/view2/M03/3D/3B/wKh2BV0qn5uAQCbiAAGLblrB01U038.png");
    	listStr.put("46", "file-1.book118.com/view4/M04/3C/2C/wKh2CV0qn5uAHOVqAAG3JQAqD7g767.png");
    	listStr.put("47", "file-1.book118.com/view6/M00/3B/08/wKh2Cl0qn5uAb5ASAAClXYB-OAY682.png");
    	listStr.put("48", "file-1.book118.com/view1/M05/0B/3A/wKh2BF0qn5yASF9aAAJPQul8KNk415.png");
    	listStr.put("49", "file-1.book118.com/view4/M02/3D/08/wKh2CV0qomGAYVSaAAG1i0ync3U874.png");
    	listStr.put("50", "file-1.book118.com/view1/M05/0C/16/wKh2BF0qomKAdVJMAAGU7flPe9Y310.png");
    	listStr.put("51", "file-1.book118.com/view3/M04/36/39/wKh2BV0qomKAPJOLAAEMgQG5qOo529.png");
    	listStr.put("52", "file-1.book118.com/view4/M02/3C/1D/wKh2Cl0qomKAWUNaAABR2gLspVE439.png");
    	listStr.put("53", "file-1.book118.com/view3/M04/14/10/wKh2BF0qomKADVTYAAAjNnIN7xc848.png");
    	listStr.put("54", "file-1.book118.com/view1/M05/3F/3F/wKh2BV0qomKATFgPAAAMgm1s14g747.png");
    	listStr.put("55", "file-1.book118.com/view6/M05/3E/0A/wKh2Cl0qt9mAGjx4AAFZCf8vjxo659.png");
    	listStr.put("56", "file-1.book118.com/view5/M02/00/22/wKh2CV0qt9yAPwxuAAEG4FAW5WM388.png");
    	listStr.put("57", "view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYr1WHca1ADcEg=");
    	listStr.put("58", "view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYruX2uZBEtOEw=");
    	listStr.put("59", "view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYrSejOjAAS_XA=");
    	listStr.put("60", "file-1.book118.com/view2/M05/01/00/wKh2BV0qt9yACYY0AAJ4HR0IBbI824.png");
    	listStr.put("61", "file-1.book118.com/view3/M04/36/1E/wKh2BV0qn7WAcwwyAAD4rtbaF_0405.png");
    	listStr.put("62", "file-1.book118.com/view6/M01/3B/09/wKh2Cl0qn7WABJwBAAIp8rrP_8k687.png");
    	listStr.put("63", "file-1.book118.com/view1/M03/0B/3A/wKh2BF0qn7SAS2M5AABaUO3ht0c802.png");
    	listStr.put("64", "file-1.book118.com/view6/M00/3B/34/wKh2BF0qn7WAfF_0AAERZgc5srg467.png");
    	listStr.put("65", "file-1.book118.com/view4/M02/3C/01/wKh2Cl0qn7WAQjEQAAFuzlBysWA379.png");
    	listStr.put("66", "file-1.book118.com/view4/M03/3C/01/wKh2Cl0qn7aAByNdAAE49sO64v8960.png");
    	listStr.put("67", "file-1.book118.com/view2/M00/0E/12/wKh2BF0qthyACOY1AAGkDIYeRbw102.png");
    	listStr.put("68", "file-1.book118.com/view1/M05/02/19/wKh2BV0qthyALV_sAAE61gOUJlw032.png");

    	Set<Entry<String, String>> set = listStr.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> ey = it.next();
			String key = ey.getKey();
			String value = ey.getValue();
			String urlStr = "http://"+value;
			String jpgStr = "F:\\01-Tmp\\06-pic\\"+key+".jpg";
			System.out.println(urlStr);
			System.out.println(jpgStr);
			getUrlPicRun(urlStr,jpgStr);
		}
        
    }  
    
    public static void getUrlPicRun(String urlStr, String pngStr) throws Exception{
    	//new一个URL对象  
        URL url = new URL(urlStr);  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File(pngStr);
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();  
    }
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
/*
1 file-1.book118.com/view3/M02/14/3A/wKh2BVzUFJGACZWxAAFMn2F20u4996.png
2 file-1.book118.com/view1/M05/15/38/wKh2BVzUFJGAViPtAAIdHdotzHY098.png
3 file-1.book118.com/view3/M02/14/3A/wKh2BVzUFJGAfqJsAAH1CgjPdpI600.png
4 file-1.book118.com/view3/M01/14/3A/wKh2BVzUFJGAFB1gAAEf1sR8bqs540.png
5 file-1.book118.com/view3/M00/14/3A/wKh2BVzUFJGAZNXlAAFNi1swGN4186.png
6 file-1.book118.com/view3/M03/14/3A/wKh2BVzUFJGAFgzsAAFAHuZ9UZ8336.png
7 file-1.book118.com/view2/M05/15/0F/wKh2BVzUFJGAdZ4uAAGza7fJDUk138.png
8 file-1.book118.com/view3/M01/14/3A/wKh2BVzUFJGAQojrAAG4IbGOidM197.png
9 file-1.book118.com/view3/M05/14/3A/wKh2BVzUFJGAGtHiAAGIRl51bYo904.png
10 file-1.book118.com/view3/M01/16/15/wKh2BVzUFceAd97LAACrx4rc31Q381.png
11 file-1.book118.com/view2/M01/16/2A/wKh2BVzUFceAd7ShAAE6O3wGc6Q447.png
12 file-1.book118.com/view3/M02/16/15/wKh2BVzUFceABKuvAAFJWgiyK4o823.png
13 file-1.book118.com/view2/M04/3D/28/wKh2BV0qnc2ACfxUAAGum7XFo-s756.png
14 file-1.book118.com/view3/M05/36/0A/wKh2BV0qnc2AMYIpAAD4x6HmBQY595.png
15 file-1.book118.com/view1/M05/3F/10/wKh2BV0qnc2AeYsWAAHVhto4HVo847.png
16 file-1.book118.com/view6/M01/3B/21/wKh2BF0qnc2AOzlKAAE1PdPV_io337.png
17 file-1.book118.com/view2/M03/3D/28/wKh2BV0qnc2ATaTNAADuz4cdrxo565.png
18 file-1.book118.com/view1/M00/3F/10/wKh2BV0qnc2ASst8AAI-IRKyUBA700.png
19 file-1.book118.com/view4/M04/3B/2F/wKh2Cl0qneOAGFg2AAF_LduJmUE863.png
20 file-1.book118.com/view4/M05/3C/1A/wKh2CV0qneSAIpihAAFvDChyVTc558.png
21 file-1.book118.com/view6/M00/3B/22/wKh2BF0qneWASKkZAADrrl_x8bA164.png
22 file-1.book118.com/view2/M02/3D/29/wKh2BV0qneWAELoIAAHRBvxaVkQ236.png
23 file-1.book118.com/view1/M01/0B/28/wKh2BF0qneWAFuxpAAH82Jv7wM8586.png
24 file-1.book118.com/view3/M04/36/0B/wKh2BV0qneWAejcfAAF1VMOdw0o900.png
25 file-1.book118.com/view6/M05/3B/33/wKh2BF0qn5CAKatiAADjoKLkZII588.png
26 file-1.book118.com/view4/M05/3C/00/wKh2Cl0qn5CAJq2MAADSH304X5w470.png
27 file-1.book118.com/view6/M01/3B/33/wKh2BF0qn5KAPHd1AAENt5tKaz8220.png
28 file-1.book118.com/view1/M02/0B/39/wKh2BF0qn5GARwmtAAGRygp_P_8579.png
29 file-1.book118.com/view5/M00/3D/1E/wKh2BF0qn5GAf4NnAAE6CtrKhZQ107.png
30 file-1.book118.com/view5/M01/3D/1D/wKh2CV0qn5GAMaIhAAEqv8CoGrg750.png
31 file-1.book118.com/view1/M01/3F/12/wKh2BV0qngOABkn8AADj83TeEuc009.png
32 file-1.book118.com/view4/M04/3B/30/wKh2Cl0qngOAYDWbAAEOrGwLrJI392.png
33 file-1.book118.com/view6/M03/3B/24/wKh2BF0qngOAFHtdAAHBVR_rxy4765.png
34 file-1.book118.com/view5/M02/3D/0E/wKh2CV0qngOAJHhBAAFDps4QtI4757.png
35 file-1.book118.com/view5/M02/3D/0F/wKh2BF0qngOAVaJZAAGerVFW5KA173.png
36 file-1.book118.com/view3/M00/36/0D/wKh2BV0qngOAQmsFAAF_G7YTqsM540.png
37 file-1.book118.com/view4/M01/3C/2C/wKh2CV0qn6GAPz0bAAGb6jPDOFg481.png
38 file-1.book118.com/view3/M03/13/35/wKh2BF0qn6GAQCJwAACki_H3PKk612.png
39 file-1.book118.com/view2/M05/3D/3B/wKh2BV0qn6aATuFRAAHWZhyCAB4255.png
40 file-1.book118.com/view2/M02/0B/1B/wKh2BF0qn6WAIZVzAAE24J3mLQM814.png
41 file-1.book118.com/view6/M00/3B/08/wKh2Cl0qn6WARmvIAADUrLCr0wA665.png
42 file-1.book118.com/view1/M00/3F/23/wKh2BV0qn6aAd0nDAAGHw0yvrw0644.png
43 file-1.book118.com/view3/M02/13/34/wKh2BF0qn5uAYzciAAEStfCqMbQ265.png
44 file-1.book118.com/view4/M03/3C/2C/wKh2CV0qn5uAZDzOAAFzGo-XMDI371.png
45 file-1.book118.com/view2/M03/3D/3B/wKh2BV0qn5uAQCbiAAGLblrB01U038.png
46 file-1.book118.com/view4/M04/3C/2C/wKh2CV0qn5uAHOVqAAG3JQAqD7g767.png
47 file-1.book118.com/view6/M00/3B/08/wKh2Cl0qn5uAb5ASAAClXYB-OAY682.png
48 file-1.book118.com/view1/M05/0B/3A/wKh2BF0qn5yASF9aAAJPQul8KNk415.png
49 file-1.book118.com/view4/M02/3D/08/wKh2CV0qomGAYVSaAAG1i0ync3U874.png
50 file-1.book118.com/view1/M05/0C/16/wKh2BF0qomKAdVJMAAGU7flPe9Y310.png
51 file-1.book118.com/view3/M04/36/39/wKh2BV0qomKAPJOLAAEMgQG5qOo529.png
52 file-1.book118.com/view4/M02/3C/1D/wKh2Cl0qomKAWUNaAABR2gLspVE439.png
53 file-1.book118.com/view3/M04/14/10/wKh2BF0qomKADVTYAAAjNnIN7xc848.png
54 file-1.book118.com/view1/M05/3F/3F/wKh2BV0qomKATFgPAAAMgm1s14g747.png
55 file-1.book118.com/view6/M05/3E/0A/wKh2Cl0qt9mAGjx4AAFZCf8vjxo659.png
56 file-1.book118.com/view5/M02/00/22/wKh2CV0qt9yAPwxuAAEG4FAW5WM388.png
57 view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYr1WHca1ADcEg=
58 view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYruX2uZBEtOEw=
59 view41.book118.com/img?img=7o@o7xcocmnJQJbnWj7mOiVXdca38iwfknkOpoIYKLaHJ_VDfvJgVvMifSLU8AYrSejOjAAS_XA=
60 file-1.book118.com/view2/M05/01/00/wKh2BV0qt9yACYY0AAJ4HR0IBbI824.png
61 file-1.book118.com/view3/M04/36/1E/wKh2BV0qn7WAcwwyAAD4rtbaF_0405.png
62 file-1.book118.com/view6/M01/3B/09/wKh2Cl0qn7WABJwBAAIp8rrP_8k687.png
63 file-1.book118.com/view1/M03/0B/3A/wKh2BF0qn7SAS2M5AABaUO3ht0c802.png
64 file-1.book118.com/view6/M00/3B/34/wKh2BF0qn7WAfF_0AAERZgc5srg467.png
65 file-1.book118.com/view4/M02/3C/01/wKh2Cl0qn7WAQjEQAAFuzlBysWA379.png
66 file-1.book118.com/view4/M03/3C/01/wKh2Cl0qn7aAByNdAAE49sO64v8960.png
67 file-1.book118.com/view2/M00/0E/12/wKh2BF0qthyACOY1AAGkDIYeRbw102.png
68 file-1.book118.com/view1/M05/02/19/wKh2BV0qthyALV_sAAE61gOUJlw032.png
*/
    
    
}