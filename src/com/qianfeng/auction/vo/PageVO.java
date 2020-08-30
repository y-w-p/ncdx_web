package com.qianfeng.auction.vo;

import java.math.BigDecimal;
import java.util.List;

public class PageVO<T> {

	// 分页数据的结果集
	private List<T> lists;
	// 页码的索引
	private BigDecimal pageindex;
	// 每页显示多少条
	private BigDecimal pagenumber;
	// 总条数
	private BigDecimal total;
	// 尾页
	private BigDecimal endpage;

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public BigDecimal getpageindex() {
		return pageindex;
	}

	public void setpageindex(BigDecimal pageindex) {
		this.pageindex = pageindex;
	}

	public BigDecimal getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(BigDecimal pagenumber) {
		this.pagenumber = pagenumber;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getEndpage() {
		return endpage;
	}

	public void setEndpage(BigDecimal endpage) {
		this.endpage = endpage;
	}

}
