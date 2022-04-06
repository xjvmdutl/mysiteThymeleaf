package hello.mysite.repository;

import hello.mysite.dao.board.UploadFile;
import hello.mysite.entity.Picture;
import hello.mysite.repository.custom.PictureCustomRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PictureRepository extends JpaRepository<Picture, Long>, PictureCustomRepository {

    //@Query("select p from Picture p where p.board.no = :no")
    //List<Picture> findByNo(@Param("no") Long no);
}
