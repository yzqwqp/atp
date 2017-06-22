package com.uusoft.atp.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 模仿京东分页插件,根据步长展示分页信息
 * @author hejian
 *
 */
public class PageUtils {
	private PageUtils(){}
	
	/**
	 * 组装分页信息
	 * @param pageVo
	 * @return
	 */
	private static PageVo getPageInfo(PageVo pageVo){
		Integer current=pageVo.getCurrent();//当前请求页
		Integer totalPage=pageVo.getTotalPage();//获得总页数
		Integer len=pageVo.getLen();//步长
		List<Integer> showNums=new ArrayList<Integer>();
		if(current-len<=2){//设置没有隐藏前面的省略号
			pageVo.setBefore(true);
		}
		if(current+len>=totalPage){//设置没有隐藏后面的省略号
			pageVo.setAfter(true);
		}
		if(pageVo.isBefore()){
			for(int i=1;i<=current;i++){
				showNums.add(i);
			}
		}else{
			for(int i=current-len;i<=current;i++){
				showNums.add(i);
			}
		}
		if(pageVo.isAfter()){
			for(int i=current+1;i<=totalPage;i++){
				showNums.add(i);
			}
		}else{
			for(int i=1;i<len;i++){
				showNums.add(current+i);
			}
		}
		pageVo.setShowNums(showNums);
		return pageVo;
	}
	
	public static PageVo getPageInfo(Integer current,Integer total){
		return getPageInfo(current, total, 10);
	}
	public static PageVo getPageInfo(Integer current,Integer total,Integer pageSize){
		if(pageSize==null){
			pageSize=10;
		}
		PageVo pageVo=new PageVo(pageSize);
		if(current==null||current<1){
			current=1;
		}
		if(total==0){
			pageVo.setTotal(0);
			return pageVo;
		}
		pageVo.setCurrent(current);
		pageVo.setTotal(total==0?1:total);
		pageVo=PageUtils.getPageInfo(pageVo);
		return pageVo;
	}
	public static void main(String asd[]){
		PageVo pageVo=new PageVo();
		pageVo.setCurrent(56);
		pageVo.setTotal(60);
		pageVo.setPageSize(1);
		System.out.println("测试分页:"+getPageInfo(pageVo));
	}
}
