package hello.mysite.repository;

import hello.mysite.entity.GuestBook;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    @Query("select g from GuestBook g where no=:no and password=:password")
    Optional<GuestBook> findByIdAndPassword(@Param("no") Long no,@Param("password") String password);
}
