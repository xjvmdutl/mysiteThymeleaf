package hello.mysite.repository.custom;

import static hello.mysite.entity.QGuestBook.guestBook;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mysite.entity.GuestBook;
import hello.mysite.repository.custom.GuestBookCustomRepository;
import java.util.Optional;
import javax.persistence.EntityManager;

public class GuestBookRepositoryImpl implements GuestBookCustomRepository {

    private final JPAQueryFactory queryFactory;

    public GuestBookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<GuestBook> findByIdAndPassword(Long no, String password) {
        return Optional.ofNullable(
            queryFactory
                .selectFrom(guestBook)
                .where(guestBook.no.eq(no).and(guestBook.password.eq(password)))
                .fetchOne());
    }
}
