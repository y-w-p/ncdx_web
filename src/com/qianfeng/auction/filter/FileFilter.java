package com.qianfeng.auction.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qianfeng.auction.entity.User;

public class FileFilter implements Filter {

	// ʵ������־����
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		// ��ȡ�û����ʵ��ļ���ַ
		String userURI = request2.getRequestURI();
		// ����˵����ʵ���һ�� user_login.jsp ��ô���URI ���� /ncdx_web/user_login.jsp
		// ������Ҫ֪���û�������ʵ�����һ���ļ�
		userURI = userURI.substring(userURI.indexOf("/", 1), userURI.length());
		// �������д��� ���ջ�ȡ���ľ��� /user_login.jsp
		// ���Ǻ�ΪҪ֪�� �û��ľ�����ʵ��ļ��� ��Ϊ���Ƿ����б�
		// Ϊ��Ҫ�������б� ��Ϊ���ǲ������е��ļ�������
		// contains����˼ ����˼�ǰ��� ���ص���һ�� ���ʽ
		// || userURI.contains("/js") || userURI.contains("/images") ||
		// userURI.contains("/css")
		// �����ļ�֮�����ڷ����б��� ����Ϊ /* Ҳ������ ��̬��Դ

		// �������п��Ʋ��ļ�
		if (userURI.contains("Servlet")) {
			// ��ȡ�û���IP��ַ
			String userHost = request2.getLocalAddr();
			// ��ȡ�û����ύ����
			String userMethod = request2.getMethod();
			// ��ȡ�û����ύ������
			String userData = request2.getQueryString();
			// ��������־�ļ�¼
			User user = (User) request2.getSession().getAttribute("user");
			logger.error("���ʵ��û���"
					+ (user == null ? "" : user.getUsername())
					+ "���û���IP����:"
					+ userHost
					+ ",���û������󷽷���:"
					+ userMethod
					+ ",�û����ύ������Ϊ:"
					+ (Arrays.toString(userData == null ? new String[0]
							: userData.split("&")) + ",�û����ʵ��ļ�Ϊ:" + userURI));
		}

		if (userURI.contains("/user_login.jsp")
				|| userURI.contains("/UserLoginServlet")
				|| userURI.contains("/js") || userURI.contains("/images")
				|| userURI.contains("/css")
				|| userURI.contains("AliPayReturnServlet")
				|| userURI.contains("AliPayServlet")
				|| userURI.contains("UserSendNote")) {
			// ����(������ͨ��)
			// ִ����һ�������� ���û����һ�������� ��ֱ�ӷ����û������ʵ��ļ�
			chain.doFilter(request2, response2);
		} else {
			// ֻҪ�ǽ��뵽������ļ� ȫ���ܵ��ù������ı���
			User user = (User) request2.getSession().getAttribute("user");
			if (user == null) {
				// ������û�û�е�¼�� ��ô�Ͱ�����û� �ߵ���¼ҳ��
				response2.sendRedirect("user_login.jsp");
			} else {
				// �ܽ����� ˵���û��е�¼��
				chain.doFilter(request2, response2);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
