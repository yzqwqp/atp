package com.uusoft.atp.web.util;

import java.io.Serializable;
import java.util.List;
/**
 * 分页信息
 * @author hejian
 *
 */
public class PageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**总条数*/
	private Integer total;
	/**每页条数*/
	private Integer pageSize;
	/**步长*/
	private Integer len=3;
	/**当前位置*/
	private Integer current;
	/**前面是否有省略号 true没有省略号*/
	private boolean before;
	/**后面是否有省略号 true没有省略号*/
	private boolean after;
	/**总页数*/
	private Integer totalPage;
	/**存放要显示的数字*/
	private List<Integer> showNums;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getLen() {
		return len;
	}
	public void setLen(Integer len) {
		this.len = len;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public boolean isBefore() {
		return before;
	}
	public void setBefore(boolean before) {
		this.before = before;
	}
	public boolean isAfter() {
		return after;
	}
	public void setAfter(boolean after) {
		this.after = after;
	}
	public Integer getTotalPage() {
		totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<Integer> getShowNums() {
		return showNums;
	}
	public void setShowNums(List<Integer> showNums) {
		this.showNums = showNums;
	}

	public String toString() {
		return "{total=" + total + ", pageSize=" + pageSize + ", len=" + len
				+ ", current=" + current + ", before=" + before + ", after="
				+ after + ", totalPage=" + totalPage + ", showNums=" + showNums
				+ "}";
	}
	public PageVo(Integer pageSize){
		this.pageSize=pageSize;
	}
	public PageVo(){this.pageSize=10;}
}
