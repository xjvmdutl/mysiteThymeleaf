package hello.mysite.controller;

import hello.mysite.dao.GuestBookDao;
import hello.mysite.dao.GuestBookDeleteDao;
import hello.mysite.service.GuestBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner.Mode;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestBookController {

    private final GuestBookService guestBookService;

    @GetMapping({"", "list"})
    public String list(Model model) {
        List<GuestBookDao> list = guestBookService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("guestbook", new GuestBookDao());
        return "guestbook/list";
    }

    @PostMapping({"", "list"})
    public String insert(GuestBookDao guestBookDao) {
        GuestBookDao guestbook = guestBookService.save(guestBookDao);
        if(guestbook == null){
            throw new IllegalArgumentException("Save Fail");
        }
        return "redirect:/guestbook";
    }

    @GetMapping("/deleteform")
    public String deleteform(@RequestParam Long no, Model model) {
        model.addAttribute("no", no);
        model.addAttribute("guestbook", new GuestBookDao());
        return "guestbook/deleteform";
    }

    @PostMapping("/deleteform")
    public String delete(GuestBookDeleteDao guestBookDeleteDao) {
        guestBookService.delete(guestBookDeleteDao);
        return "redirect:/guestbook";
    }
}
