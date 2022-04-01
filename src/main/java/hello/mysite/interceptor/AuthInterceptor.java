package hello.mysite.interceptor;

import static hello.mysite.SessionConst.LOGIN_USER;

import hello.mysite.SessionConst;
import hello.mysite.entity.User;
import hello.mysite.security.Auth;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if(auth == null){
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class); //클래스 타입
            if(auth == null){
                return  true;
            }
        }
        HttpSession session = request.getSession();
        User authUser = (User)session.getAttribute(LOGIN_USER);
        if(session == null || authUser == null){
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        String role = auth.value();
        if("USER".equals(role)){
            return true;
        }
        if("ADMIN".equals(role)){
            return true;
        }
        return false;
    }
}
