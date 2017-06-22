package com.uusoft.atp.demo;

import java.util.ArrayList;
import java.util.List;

import com.uusoft.atp.utils.ConstantUtils;

public class TestDataUtil<T> {
	private List<T> resultList = new ArrayList<T>();

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	public static void main(String[] arg) {
		System.out.println(ConstantUtils.generateSerialno());
		
	}
}
