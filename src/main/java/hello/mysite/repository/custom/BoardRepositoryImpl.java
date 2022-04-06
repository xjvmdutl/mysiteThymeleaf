package hello.mysite.repository.custom;


import static hello.mysite.entity.QBoard.board;
import static hello.mysite.entity.QUser.user;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mysite.dao.board.BoardDto;

import hello.mysite.dao.board.QBoardDto;
import hello.mysite.entity.Board;
import hello.mysite.entity.QBoard;
import hello.mysite.entity.QUser;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

public class BoardRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<BoardDto> findByContents(Pageable page, String kwd) {
        List<BoardDto> content = queryFactory
            .select(
                new QBoardDto(
                    board.no, board.title, board.contents, board.hit, board.createdAt, board.gNo,
                    board.oNo, board.depth,
                    user.no.as("userNo"), user.name.as("name"), user.email.as("email"),
                    user.password.as("password"),
                    user.gender.as("gender")
                    , board.status
                )
            )
            .from(board)
            .leftJoin(board.user, user)
            .where(
                kwdContains(kwd)
            )
            .offset(page.getOffset())
            .limit(page.getPageSize())
            .fetch();
        JPAQuery<Board> countQuery = queryFactory.
            select(board)
            .from(board)
            .leftJoin(board.user, user)
            .where(
                kwdContains(kwd)
            );

        return PageableExecutionUtils.getPage(content, page, countQuery::fetchCount);
    }

    private BooleanExpression kwdContains(String kwd) {
        return StringUtils.hasText(kwd) ? board.title.contains(kwd) : null;
    }

    @Override
    public Long findByGNo() {
        return queryFactory
            .select(
                board.gNo.max()
            ).from(board)
            .fetchOne();
    }

    @Override
    public long bulkUpdate(Long oNo, Long gNo) {
        return queryFactory
            .update(board)
            .set(board.oNo, board.oNo.add(1))
            .where(board.gNo.eq(gNo).and(board.oNo.eq(oNo))).execute();

    }
}
