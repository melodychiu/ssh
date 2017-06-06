package cn.mydoll.ssh.util;

import java.util.List;

public class QueryResult<T> {
	//返回查询结果的集合
	private List<T> list;
	//返回查询结果的总记录数
	private int totalCount;
	//对分页类进行封装
	private PageSupport pageSupport;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public PageSupport getPageSupport() {
		return pageSupport;
	}
	public void setPageSupport(PageSupport pageSupport) {
		this.pageSupport = pageSupport;
		pageSupport.init();
	}
}