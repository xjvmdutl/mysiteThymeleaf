package hello.mysite.controller;

import hello.mysite.dao.board.BoardDto;
import hello.mysite.dao.board.DetailBoardDto;
import hello.mysite.dao.board.InsertBoardDto;
import hello.mysite.dao.board.RequestDto;
import hello.mysite.dao.board.UploadFile;
import hello.mysite.entity.FileStore;
import hello.mysite.entity.User;
import hello.mysite.security.AuthUser;
import hello.mysite.service.BoardService;
import hello.mysite.service.PictureService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final PictureService pictureService;
    private final BoardService boardService;
    private final FileStore fileStore;

    @GetMapping({"", "list"})
    public String index(
        @PageableDefault(page = 0, size = 5) Pageable page,
        @RequestParam(value = "kwd", required = false, defaultValue = "") String kwd,
        Model model) {
        Page<BoardDto> list = boardService.findAll(page, kwd);
        model.addAttribute("list", list);
        model.addAttribute("kwd", kwd);
        return "board/list";
    }

    @GetMapping("/view/{no}")
    public String view(@PathVariable("no") Long no, Model model) {
        DetailBoardDto boardDto = boardService.updateHits(no);
        List<UploadFile> pictures = pictureService.findByNo(no);
        model.addAttribute("board", boardDto);
        model.addAttribute("pictures", pictures);
        return "board/view";
    }

    @GetMapping("/modify/{no}")
    public String modify(@PathVariable("no") Long no, Model model) {
        DetailBoardDto boardDto = boardService.findByNo(no);
        model.addAttribute("board", boardDto);
        return "board/modify";
    }
    
    @PostMapping("/modify/{no}")
    public String modify(@PathVariable("no") Long no, @ModelAttribute("board") DetailBoardDto boardDto) {
        boardService.modify(no, boardDto);
        boardDto.setNo(no);
        return "redirect:/board/view/"+no;
    }

    @GetMapping("/write")
    public String write(@AuthUser User user, Model model) {
        if (user == null) {
            return "redirect:/board";
        }
        model.addAttribute("board", new InsertBoardDto());
        return "board/write";
    }

    @GetMapping("/request/{no}")
    public String request(@PathVariable("no") Long no, Model model, @AuthUser User user) {
        if(user == null )
            return "redirect:/board";
        InsertBoardDto requestDto = boardService.findByRequest(no);
        model.addAttribute("board", requestDto);
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@AuthUser User user, @ModelAttribute("board") InsertBoardDto board) {
        if (board.getGNo() == null && board.getONo() == null && board.getDepth() == null) {
            boardService.insertGroup(board, user);
        } else {
            boardService.updateRequest(board.getONo(), board.getGNo(), board.getDepth());
            boardService.insertRequest(board, user);
        }
        return "redirect:/board";
    }

    @RequestMapping("/upload/{no}")
    public String upload(
        @RequestParam(required = false, name = "attachFile") MultipartFile attachFile,
        @PathVariable("no") Long no) throws IOException {
        pictureService.save(no, fileStore.storeFile(attachFile));
        return "redirect:/board/view/" + no;
    }


    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //보안에 취약
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
