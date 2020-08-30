package com.qianfeng.auction.util;

import java.util.List;
import javax.servlet.http.HttpSession;

public class AuthorizationUtil {

	@SuppressWarnings("unchecked")
	public static boolean hasRole(String rolename, HttpSession httpSession) {
		List<String> roleList = (List<String>) httpSession
				.getAttribute("roles");
		for (String role : roleList) {
			if (role.equals(rolename)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean hasPermission(String permissionname,
			HttpSession httpSession) {
		List<String> permissions = (List<String>) httpSession
				.getAttribute("permissions");
		for (String permission : permissions) {
			if (permission.equals(permissionname)) {
				return true;
			}
		}
		return false;
	}
}
