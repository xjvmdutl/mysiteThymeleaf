package hello.mysite.repository.custom;

import static hello.mysite.entity.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.mysite.entity.User;
import java.util.Optional;
import javax.persistence.EntityManager;

public class UserRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(queryFactory
            .selectFrom(user)
            .where(user.email.eq(email))
            .fetchOne());
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.ofNullable(
            queryFactory
            .selectFrom(user)
                .where(user.email.eq(email).and(user.password.eq(password)))
            .fetchOne()
        );
    }
}
