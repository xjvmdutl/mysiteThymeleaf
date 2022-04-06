package hello.mysite.repository.custom;

import static hello.mysite.entity.QPicture.picture;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mysite.entity.Picture;
import hello.mysite.entity.QPicture;
import java.util.List;
import javax.persistence.EntityManager;

public class PictureRepositoryImpl implements PictureCustomRepository{

    private final JPAQueryFactory queryFactory;

    public PictureRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Picture> findByNo(Long boardNo) {
        return queryFactory
            .selectFrom(picture)
            .where(picture.board.no.eq(boardNo))
            .fetch();
    }
}
