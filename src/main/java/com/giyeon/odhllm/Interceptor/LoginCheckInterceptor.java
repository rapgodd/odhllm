package com.giyeon.odhllm.Interceptor;

import com.giyeon.odhllm.UrlConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] permitUrls = UrlConst.PERMIT_URLS;

        String requestURI = request.getRequestURI();
        AntPathMatcher matcher = new AntPathMatcher();

        //허용 URL -> 컨트롤러로 보냄
        for (String permitUrl : permitUrls) {
            if (matcher.match(permitUrl, requestURI)) {
                return true;
            }
        }

        //로그인 필요 URL -> 세션없으면 로그인 페이지로 보냄
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
