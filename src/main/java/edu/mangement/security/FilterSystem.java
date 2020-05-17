package edu.mangement.security;

import edu.mangement.constant.Constant;
import edu.mangement.entity.Auth;
import edu.mangement.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 12:29 AM
 */
@Slf4j
public class FilterSystem implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Request URL : " + request.getRequestURI());
        Member member = (Member) request.getSession().getAttribute(Constant.USER_INFO);
        if (member == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return true;
        } else {
            var url = request.getServletPath();
            if (!hasPermission(url,member)) {
                log.info("Asses Dined :  "+ request.getRequestURI());
                response.sendRedirect(request.getContextPath()+"/403");
            }
        }
        return false;
    }

    private boolean hasPermission(String url, Member member) {
        var auths = member.getRole().getAuths();
        for(Auth auth : auths){
            if(url.contains(auth.getFunction().getUrl())){
                return auth.getPermission() == 1;
            }
        }
        return false;
    }
}
