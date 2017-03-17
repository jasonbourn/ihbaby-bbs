# ihbaby-bbs
SpringBoot+jwt+redis+mongo
技术框架介绍：
springboot：开源的微服务架构，微服务脚手架
jwt:JSON Web Token（JWT）是一个开放标准（RFC 7519），它定义了一种紧凑和自包含的方式，用于在各方之间作为JSON对象安全地传输信息。作为标准，它没有提供技术实现，但是大部分的语言平台都有按照它规定的内容提供了自己的技术实现，所以实际在用的时候，只要根据自己当前项目的技术平台，到官网上选用合适的实现库即可。
redis:开源NOSQL
 
关键代码：
一、过滤器

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
请求过来后会检查header中的authorization  进行token验签
	
	if (Jwts.parser().setSigningKey(emv.getProperty("jwt.SecretKey")).parse(authHeader) != null){
					final Claims claims = Jwts.parser().setSigningKey(emv.getProperty("jwt.SecretKey")).parseClaimsJws(authHeader).getBody();
					request.setAttribute("claims", claims);
					chain.doFilter(req, res);
				}


验签通过会记录token携带信息 记录到request并放行。
二、拦截器

	if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 默认签名
            UserPerm methodAnnotation = handlerMethod.getMethodAnnotation(UserPerm.class);
            if (methodAnnotation == null || methodAnnotation.value()) {
                String perm =methodAnnotation.param();
                Claims claims = (Claims) request.getAttribute("claims");
                String permSub =(String) claims.get("permission");
                if (StringUtils.isBlank(permSub)){
                    throw new SignRuntimeException("token失效！");
                }
                String arg = permSub.split(",");
                Boolean flag =Arrays.asList(arg).contains(perm);
                if (!flag){
                    throw new SignRuntimeException("您没有权限访问！");
                }
            }
        }

三、登录

	public ApiResult login(String userName, String password){
		password = DigestUtils.digest(password);
		SysUser user = sysUserDao.findByNameAndPwd(userName);
		if(user==null){
		    return ApiResult.createError("用户不存在！");
		}else if (!user.getPassword().equals(password)){
		    return ApiResult.createError("用户名或密码错误！");
		}else {
		    List<SysPermission> permissions = permissionDao.getPermissionList(user.getId());
		    StringBuilder builder =new StringBuilder();
		    for (SysPermission permission : permissions){
			builder.append(permission.getPermissionKey()).append(",");
		    }//失效时间
		    Date exp = DateUtils.getDayLater(1);
		    String jwtToken = Jwts.builder().setSubject(userName).claim("permission",builder.toString()).setIssuedAt(new Date()).setExpiration(exp)
			    .signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
		    return ApiResult.createSuccess(jwtToken);
		}

	    }


 思路点拨：在登陆时将用户权限查出用jwt加密生成token,每次请求都携带着token访问。过滤器进行签证验收，拦截器负责匹配权限。它会把一些数据放到token中，这样我们就可以把部分业务数据直接写到token中。这样我们就无需去填充session了，从而可以实现stateless的接口。
 
项目完整地址：https://github.com/jasonbourn/ihbaby-bbs.git
 
不要做伸手党，打赏开源精神

 
 
