package hello.mysite.repository.custom;

import hello.mysite.entity.User;
import java.util.Optional;

public interface UserCustomRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
