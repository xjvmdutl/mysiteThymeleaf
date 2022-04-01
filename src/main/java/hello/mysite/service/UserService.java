package hello.mysite.service;

import hello.mysite.dao.request.JoinRequestDao;
import hello.mysite.dao.request.UpdateRequestDao;
import hello.mysite.entity.User;
import java.util.Optional;

public interface UserService {

    void save(JoinRequestDao joinRequestDao);

    boolean existUser(String email);

    Optional<User> findByEmailAndPassword(User user);

    void update(UpdateRequestDao user);
}
