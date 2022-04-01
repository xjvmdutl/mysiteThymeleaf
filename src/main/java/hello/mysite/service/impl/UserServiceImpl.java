package hello.mysite.service.impl;

import hello.mysite.dao.request.JoinRequestDao;
import hello.mysite.dao.request.UpdateRequestDao;
import hello.mysite.entity.User;
import hello.mysite.repository.UserRepository;
import hello.mysite.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void save(JoinRequestDao joinRequestDao) {
        userRepository.save(User.builder()
            .name(joinRequestDao.getName())
            .password(joinRequestDao.getPassword())
            .gender(joinRequestDao.getGender())
            .email(joinRequestDao.getEmail())
            .build());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existUser(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmailAndPassword(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public void update(UpdateRequestDao user) {
        User findUser = userRepository.findById(user.getNo())
            .orElseThrow(() -> new IllegalArgumentException());
        findUser.update(user.getPassword(), user.getName(), user.getGender());
    }
}
