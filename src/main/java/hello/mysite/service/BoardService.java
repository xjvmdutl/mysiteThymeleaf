package hello.mysite.service;

import hello.mysite.dao.board.BoardDto;
import hello.mysite.dao.board.DetailBoardDto;
import hello.mysite.dao.board.InsertBoardDto;
import hello.mysite.dao.board.RequestDto;
import hello.mysite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    Page<BoardDto> findAll(Pageable page, String kwd);

    void insertGroup(InsertBoardDto board, User user);

    DetailBoardDto updateHits(Long no);

    DetailBoardDto findByNo(Long no);

    void modify(Long no, DetailBoardDto boardDto);

    RequestDto findByRequest(Long no);

    void updateRequest(Long oNo, Long gNo, Long depth);

    void insertRequest(InsertBoardDto board, User user);
}
