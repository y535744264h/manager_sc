SSO实现原理
授权服务 manager-gateway
资源服务 manager-*
https://www.jianshu.com/p/ac42f38baf6e

用户通过智能路由（ZUUL）访问资源服务，ZUUL将请求转发至授权服务器，判断是否有令牌，如果没有，转发至登陆页面，如果有则访问资源服务，资源服务解析token是否正确