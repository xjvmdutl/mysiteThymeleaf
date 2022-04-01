package hello.mysite.service;

import hello.mysite.dao.board.UploadFile;
import java.util.List;

public interface PictureService {

    List<UploadFile> findByNo(Long no);

    void save(Long no, UploadFile storeFile);
}
