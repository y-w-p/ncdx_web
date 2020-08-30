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

	// 实例化日志功能
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		// 获取用户访问的文件地址
		String userURI = request2.getRequestURI();
		// 比如说你访问的是一个 user_login.jsp 那么这个URI 就是 /ncdx_web/user_login.jsp
		// 我们需要知道用户具体访问的是哪一个文件
		userURI = userURI.substring(userURI.indexOf("/", 1), userURI.length());
		// 上面这行代码 最终获取到的就是 /user_login.jsp
		// 我们何为要知道 用户的具体访问的文件名 因为我们放行列表
		// 为何要做放行列表 因为我们不能所有的文件都拦截
		// contains的意思 的意思是包含 返回的是一个 表达式
		// || userURI.contains("/js") || userURI.contains("/images") ||
		// userURI.contains("/css")
		// 以上文件之所以在放行列表中 是因为 /* 也会拦截 静态资源

		// 拦截所有控制层文件
		if (userURI.contains("Servlet")) {
			// 获取用户的IP地址
			String userHost = request2.getLocalAddr();
			// 获取用户的提交方法
			String userMethod = request2.getMethod();
			// 获取用户的提交的数据
			String userData = request2.getQueryString();
			// 并进行日志的纪录
			User user = (User) request2.getSession().getAttribute("user");
			logger.error("访问的用户是"
					+ (user == null ? "" : user.getUsername())
					+ "该用户的IP的是:"
					+ userHost
					+ ",该用户的请求方法是:"
					+ userMethod
					+ ",用户的提交的数据为:"
					+ (Arrays.toString(userData == null ? new String[0]
							: userData.split("&")) + ",用户访问的文件为:" + userURI));
		}

		if (userURI.contains("/user_login.jsp")
				|| userURI.contains("/UserLoginServlet")
				|| userURI.contains("/js") || userURI.contains("/images")
				|| userURI.contains("/css")
				|| userURI.contains("AliPayReturnServlet")
				|| userURI.contains("AliPayServlet")
				|| userURI.contains("UserSendNote")) {
			// 放行(拦截器通过)
			// 执行下一个拦截器 如果没有下一个拦截器 就直接访问用户所访问的文件
			chain.doFilter(request2, response2);
		} else {
			// 只要是进入到这里的文件 全部受到该过滤器的保护
			User user = (User) request2.getSession().getAttribute("user");
			if (user == null) {
				// 如果该用户没有登录过 那么就把这个用户 踢到登录页中
				response2.sendRedirect("user_login.jsp");
			} else {
				// 能进到这 说明用户有登录过
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
