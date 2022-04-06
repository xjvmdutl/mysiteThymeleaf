package hello.mysite.service.impl;

import hello.mysite.dao.board.BoardDto;
import hello.mysite.dao.board.DetailBoardDto;
import hello.mysite.dao.board.InsertBoardDto;
import hello.mysite.dao.board.RequestDto;
import hello.mysite.entity.Board;
import hello.mysite.entity.User;
import hello.mysite.repository.BoardRepository;
import hello.mysite.service.BoardService;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final EntityManager em;

    @Override
    public Page<BoardDto> findAll(Pageable page, String kwd) {
        return boardRepository.findByContents(page, kwd);
    }

    @Override
    public void insertGroup(InsertBoardDto board, User user) {
        Long maxGNo = boardRepository.findByGNo();
        if (maxGNo == null) {
            maxGNo = 0L;
        }
        boardRepository.save(Board.builder()
            .title(board.getTitle())
            .contents(board.getContent())
            .hit(0L)
            .gNo(maxGNo + 1)
            .oNo(1L)
            .depth(0L)
            .status("insert")
            .user(user)
            .build());
    }

    @Override
    public DetailBoardDto updateHits(Long no) {
        Board board = getBoard(no);
        board.updateHits();
        return new DetailBoardDto(board);
    }

    private Board getBoard(Long no) {
        return boardRepository.findById(no).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public DetailBoardDto findByNo(Long no) {
        return new DetailBoardDto(getBoard(no));
    }

    @Override
    public void modify(Long no, DetailBoardDto boardDto) {
        Board board = getBoard(no);
        board.update(boardDto);
    }

    @Override
    public RequestDto findByRequest(Long no) {
        Board board = getBoard(no);

        return new RequestDto(board);
    }

    @Override
    public void updateRequest(Long oNo, Long gNo, Long depth) {
        boardRepository.bulkUpdate(oNo, gNo);
        em.flush();
        em.clear();
    }

    @Override
    public void insertRequest(InsertBoardDto board, User user) {
        boardRepository.save(Board.builder()
            .title(board.getTitle())
            .contents(board.getContent())
            .hit(0L)
            .gNo(board.getGNo())
            .oNo(board.getONo() + 1)
            .depth(board.getDepth() + 1)
            .status("insert")
            .user(user)
            .build());
    }
}
