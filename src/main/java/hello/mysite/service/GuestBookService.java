package hello.mysite.service;

import hello.mysite.dao.GuestBookDao;
import hello.mysite.dao.GuestBookDeleteDao;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GuestBookService {
    List<GuestBookDao> findAll();

    GuestBookDao save(GuestBookDao guestBookDao);

    void delete(GuestBookDeleteDao guestBookDeleteDao);
}
