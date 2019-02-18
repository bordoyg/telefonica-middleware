package com.telefonica.portalmiddleware.filters;

import java.io.IOException;
import java.nio.CharBuffer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.telefonica.portalmiddleware.utils.CustomHttpServletRequestWrapper;
import com.telefonica.portalmiddleware.utils.CustomHttpServletResponseWrapper;

public class SoapFilter implements Filter {
	private final Logger LOG = LogManager.getLogger(getClass());
	
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		CustomHttpServletRequestWrapper wrapperRequest = new CustomHttpServletRequestWrapper((HttpServletRequest) req);
		LOG.debug("SOAP REQUEST:");
		LOG.debug(wrapperRequest.getBody());
		
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		CustomHttpServletResponseWrapper wrapperResponse = new CustomHttpServletResponseWrapper(httpResponse);
		
		chain.doFilter(wrapperRequest, wrapperResponse);
		String content = wrapperResponse.getCaptureAsString();
		content=content.replaceAll("send_messageResponse", "send_message_response");
		
		resp.getWriter().write(content);
		LOG.debug("SOAP RESPONSE:");
		LOG.debug(content);
	}
	public void destroy() {}
}
