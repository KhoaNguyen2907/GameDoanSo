package cybersoft.java18.backend.gamedoanso.filter;

import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {UrlUtils.ALL},filterName = "AuthFilter")
public class AuthFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isLoginUser(req) || isAllowedURL(req)){
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + UrlUtils.DANG_NHAP + "?error=not_login");
        }

    }

    private boolean isAllowedURL(HttpServletRequest req) {
        var url = req.getServletPath();
        if (url.startsWith(UrlUtils.DANG_NHAP) || url.startsWith(UrlUtils.DANG_KY)
        || url.startsWith(UrlUtils.HEALTH) || url.startsWith(UrlUtils.HOME) || url.startsWith("/index.jsp") ){
            return true;
        }
        return false;
    }

    private boolean isLoginUser(HttpServletRequest req) {
        var currentUser = req.getSession().getAttribute("currentUser");
       return currentUser !=null;
    }
}
