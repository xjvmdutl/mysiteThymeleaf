package hello.mysite.repository.custom;

import hello.mysite.entity.GuestBook;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface GuestBookCustomRepository {
    Optional<GuestBook> findByIdAndPassword(Long no, String password);
}
