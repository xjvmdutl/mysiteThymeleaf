package hello.mysite.service.impl;

import hello.mysite.dao.board.UploadFile;
import hello.mysite.entity.Board;
import hello.mysite.entity.Picture;
import hello.mysite.repository.BoardRepository;
import hello.mysite.repository.PictureRepository;
import hello.mysite.service.PictureService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<UploadFile> findByNo(Long no) {
        return pictureRepository.findByNo(no).stream().map(UploadFile::new).collect(Collectors.toList());
    }

    @Override
    public void save(Long no, UploadFile storeFile) {
        Board board = boardRepository.findById(no)
            .orElseThrow(() -> new IllegalArgumentException());
        pictureRepository.save(
            Picture.builder().board(board).storeFileName(storeFile.getStoreFileName())
                .uploadFileName(storeFile.getUploadFileName())
                .build());
    }
}
