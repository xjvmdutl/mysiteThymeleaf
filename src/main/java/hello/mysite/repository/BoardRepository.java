package hello.mysite.repository;

import hello.mysite.dao.board.BoardDto;
import hello.mysite.entity.Board;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.contents like %:title% ")
    Page<Board> findByContents(Pageable page, @Param("title") String kwd);

    @Query("select max(b.gNo) from Board b")
    Long findByGNo();

    @Modifying
    @Query("update Board b set b.oNo = b.oNo+1 where b.gNo = :gNo and b.oNo >= :oNo")
    int bulkUpdate(@Param("oNo") Long oNo,@Param("gNo") Long gNo);
}
