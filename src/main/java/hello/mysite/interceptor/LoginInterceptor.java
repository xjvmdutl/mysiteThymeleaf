package hello.mysite.interceptor;

import static hello.mysite.SessionConst.LOGIN_USER;

import hello.mysite.SessionConst;
import hello.mysite.entity.User;
import hello.mysite.service.UserService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(!StringUtils.hasText(email) || !StringUtils.hasText(password)){
            return false;
        }
        User user = User.builder().email(email).password(password).build();
        Optional<User> authUser = userService.findByEmailAndPassword(user);
        if(!authUser.isPresent()){
            response.sendRedirect(request.getContextPath() + "/user/loginform");
            return false;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_USER, authUser.get());
        response.sendRedirect("/");
        return false;
    }
}
