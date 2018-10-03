package com.telefonica.portalmiddleware.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.telefonica.portalmiddleware.utils.CustomHttpServletResponseWrapper;

public class SoapFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		CustomHttpServletResponseWrapper wrapper = new CustomHttpServletResponseWrapper(httpResponse);
		
		chain.doFilter(req, wrapper);
		String content = wrapper.getCaptureAsString();
		
		System.out.println(content);
		resp.getWriter().write(content.replaceAll("send_messageResponse", "send_message_response"));
	}
	public void destroy() {}
}
