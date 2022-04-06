package hello.mysite.repository.custom;

import hello.mysite.entity.Picture;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface PictureCustomRepository {
    List<Picture> findByNo(Long no);
}
