package com.qianfeng.auction.vo;

import java.math.BigDecimal;
import java.util.List;

public class PageVO<T> {

	// ��ҳ���ݵĽ����
	private List<T> lists;
	// ҳ�������
	private BigDecimal pageindex;
	// ÿҳ��ʾ������
	private BigDecimal pagenumber;
	// ������
	private BigDecimal total;
	// βҳ
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
