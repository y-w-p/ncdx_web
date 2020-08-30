package com.qianfeng.auction.entity;

import java.io.Serializable;

public class Permission implements Serializable {

	private int id;
	private String permissionname;
	private String permissiondesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public String getPermissiondesc() {
		return permissiondesc;
	}

	public void setPermissiondesc(String permissiondesc) {
		this.permissiondesc = permissiondesc;
	}

}
