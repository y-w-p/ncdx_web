package com.qianfeng.auction.util;

public class StringUtil {
	
	// Ϊ��Ҫ�ж�NULL �� ���ַ����أ�
	
	// NULL ���û�û�����������д����KEY��ʱ����
	// "" ���û��ڱ�û���������ݵ�����·���
	public static boolean isEmpty(String arg){
		return arg == null || arg.equals("");
	}

	public static boolean isNotEmpty(String arg){
		return arg != null && !arg.equals("");
	}
}
