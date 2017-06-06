package cn.mydoll.ssh.util;

import java.io.Serializable;

import cn.mydoll.ssh.constant.Const;

public class PageSupport implements Serializable {
	private static final long serialVersionUID = 1471587171751738052L;
	//当前页
	private Integer pageIndex=1;
	//每页显示条数
	private Integer pageSize=Const.pageSize;
	//总记录数
	private Integer totalCount=0;
	//总页数
	private Integer totalPage=0;
	//每次调用时，重新设置当前页、总记录数、总页数
	public void init(){
		if(totalCount>0){
			totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
		}		
		if(pageIndex>totalPage){
			pageIndex=totalPage;
		}
		if(pageIndex<=0){
			pageIndex=1;
		}
	}
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}