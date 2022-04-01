package hello.mysite.controller.api;

import hello.mysite.dao.JSONResult;
import hello.mysite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("userApiController")
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/checkemail")
    public JSONResult checkEmail(@RequestParam String email){
        return JSONResult.success(userService.existUser(email));
    }
}
