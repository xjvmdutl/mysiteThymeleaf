package hello.mysite.dao.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestPicture {
    private MultipartFile attachFile;
}
