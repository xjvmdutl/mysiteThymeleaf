package hello.mysite.repository;

import hello.mysite.entity.User;
import hello.mysite.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {


}
