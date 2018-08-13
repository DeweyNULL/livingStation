package com.example.springbootdemo.controller.Interceptor;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName ProjectInterceptor
 * @Description TODO
 * @Author XLZ
 * @Date 2018/8/4 14:54
 * @Version :
 **/

@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        //System.out.println("???");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("ACCOUNT_IN_SESSION");
        String url  = request.getRequestURI();

        if (url.contains("Login")||url.contains("register")){
            System.out.println(url);
            return true;
        }
        if (username!=null){
            System.out.println(username);
            return  true;
        }else {
            System.out.println("false");
            response.sendRedirect("Login");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
