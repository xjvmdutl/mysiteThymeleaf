package hello.mysite.controller;

import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main(HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        Cookie[] cookies = request.getCookies();
        if(cookies.length > 0 & cookies != null){
            Optional<Cookie> visitCount = Arrays.stream(cookies)
                .filter((cookie) -> cookie.getName().equals("visitCount")).findFirst();
            if(visitCount.isPresent()){
                count = Integer.parseInt(visitCount.get().getValue());
            }
        }
        count++;
        Cookie cookie = new Cookie("visitCount", String.valueOf(count));
        cookie.setMaxAge(24 * 60 * 60); // 1일
        cookie.setPath("/");
        response.addCookie(cookie);
        return "main/index";
    }
}
