package hello.mysite.controller;

import hello.mysite.dao.request.JoinRequestDao;
import hello.mysite.dao.request.LoginRequestDto;
import hello.mysite.dao.request.UpdateRequestDao;
import hello.mysite.entity.User;
import hello.mysite.security.Auth;
import hello.mysite.security.AuthUser;
import hello.mysite.service.UserService;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private static Map<String, String> map = new LinkedHashMap<>();
    private final UserService userService;

    @PostConstruct
    public void init() {
        map.put("MAN", "남자");
        map.put("FEMALE", "여자");
    }

    @ModelAttribute("genders")
    public Map<String, String> genders() {
        return map;
    }

    @GetMapping("/joinform")
    public String joinform(Model model) {
        model.addAttribute("user", new JoinRequestDao());
        return "user/join";
    }

    @PostMapping("/joinform")
    public String join(@ModelAttribute("user") @Valid JoinRequestDao joinRequestDao,
        BindingResult bindingResult) {
        if (!joinRequestDao.getAgreeProv()) {
            bindingResult.addError(new FieldError("user", "agreeProv", "동의하기를 체크하여 주세요"));
        }
        if (bindingResult.hasErrors()) {
            log.info("User = {}", joinRequestDao);
            return "user/join";
        }
        userService.save(joinRequestDao);
        return "redirect:/user/joinsuccess";
    }

    @GetMapping("/joinsuccess")
    public String joinsuccess() {
        return "user/joinsuccess";
    }

    @GetMapping("/loginform")
    public String login(Model model) {
        model.addAttribute("user", new LoginRequestDto());
        return "user/login";
    }

    @Auth("USER")
    @GetMapping("/updateform")
    public String updateForm(@AuthUser User user, Model model) {
        UpdateRequestDao updateRequestDao = new UpdateRequestDao();
        updateRequestDao.setEmail(user.getEmail());
        updateRequestDao.setGender(user.getGender());
        updateRequestDao.setName(user.getName());
        updateRequestDao.setPassword(user.getPassword());
        model.addAttribute("user", updateRequestDao);
        return "user/update";
    }

    @Auth("USER")
    @PostMapping("/updateform")
    public String update(@AuthUser User authUser,
        @ModelAttribute("user") @Valid UpdateRequestDao user, BindingResult bindingResult,
        Model model) {
        if (bindingResult.hasErrors()) {
            log.info("User = {}", user);
            return "user/update";
        }
        user.setNo(authUser.getNo());
        userService.update(user);
        updateSession(authUser, user);
        return "redirect:/";
    }

    private void updateSession(User authUser, UpdateRequestDao updateRequestDao) {
        authUser.update(updateRequestDao.getPassword(), updateRequestDao.getName(), updateRequestDao.getGender());
    }
}
