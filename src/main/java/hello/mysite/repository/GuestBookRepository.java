package hello.mysite.repository;

import hello.mysite.entity.GuestBook;
import hello.mysite.repository.custom.GuestBookCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>,
    GuestBookCustomRepository {
    //@Query("select g from GuestBook g where no=:no and password=:password")
    //Optional<GuestBook> findByIdAndPassword(@Param("no") Long no,@Param("password") String password);
}
