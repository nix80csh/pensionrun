package com.pensionrun.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.pensionrun.dao.AccountDao;
import com.pensionrun.entity.Account;
import com.pensionrun.util.TokenUtil;

public class AuthenticationTokenProcessingFilter  extends GenericFilterBean {
	@Autowired
	private AccountDao accountDao;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);		
		String authToken = this.extractAuthTokenFromRequest(httpRequest);
		String userId = TokenUtil.getUserIdFromToken(authToken);
		System.out.println("<필터진입> 아이디 : " + userId);
		System.out.println("<필터진입> 인증토큰 : " + authToken);
		
		if (userId != null) {
			Account account = this.accountDao.readByUserId(userId);
			if (account != null) {
				if (TokenUtil.validateToken(authToken, account.getUserid(),account.getPassword())) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							account.getUserid(), account.getPassword());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Methods", "POST, GET,OPTIONS");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token");
		res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", "http://52.79.84.57");    

		// allow cros preflight
		if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
	}

	private HttpServletRequest getAsHttpRequest(ServletRequest request) {		
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}

	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");		

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		return authToken;
	}
}
