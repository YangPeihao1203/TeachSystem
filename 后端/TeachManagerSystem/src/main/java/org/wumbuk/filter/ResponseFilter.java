package org.wumbuk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther PeihaoYang
 * @date 2020/10/4 - 23:05
 */
@WebFilter(filterName = "myResponseFilter", urlPatterns = "/*")
public class ResponseFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("----------------------->过滤器被销毁");
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("过滤器开始执行了。。。。。");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type,Cookie");
        response.setHeader("Access-Control-Expose-Headers","x-forwared-port,X-forward-host");
        response.setHeader("Access-Control-expose-Headers","Authorization,BiToken,Cookie");
//        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, res);
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("----------------------->过滤器被创建");

    }

}
