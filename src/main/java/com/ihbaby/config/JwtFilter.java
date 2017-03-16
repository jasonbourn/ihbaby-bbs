package com.ihbaby.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihbaby.sys.ApiResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class JwtFilter extends GenericFilterBean {
	private Environment emv;
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {

			if (StringUtils.isBlank(authHeader)) {
				throw new ServletException("Missing or invalid Authorization header");
			}
			try {
				if (Jwts.parser().setSigningKey(emv.getProperty("jwt.SecretKey")).parse(authHeader) != null){
					final Claims claims = Jwts.parser().setSigningKey(emv.getProperty("jwt.SecretKey")).parseClaimsJws(authHeader).getBody();
					request.setAttribute("claims", claims);
					chain.doFilter(req, res);
				}
			} catch (Exception e){
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.setContentType("application/json; charset=utf-8");
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				ObjectMapper mapper = new ObjectMapper();

				ApiResult result = ApiResult.createError("token失效");
				httpResponse.getWriter().write(mapper.writeValueAsString(result));
			}
		}
	}

	@Override
	public void setEnvironment(Environment environment) {
		super.setEnvironment(environment);
		emv=environment;
	}
}
