package hello.mysite.repository.custom;

import hello.mysite.dao.board.BoardDto;
import hello.mysite.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardCustomRepository {
    Page<BoardDto> findByContents(Pageable page, @Param("title") String kwd);

    Long findByGNo();

    long bulkUpdate(@Param("oNo") Long oNo,@Param("gNo") Long gNo);
}
