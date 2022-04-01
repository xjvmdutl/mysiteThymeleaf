package hello.mysite.service.impl;

import hello.mysite.dao.GuestBookDao;
import hello.mysite.dao.GuestBookDeleteDao;
import hello.mysite.entity.GuestBook;
import hello.mysite.repository.GuestBookRepository;
import hello.mysite.service.GuestBookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Override
    public List<GuestBookDao> findAll() {
        return guestBookRepository.findAll().stream().map(GuestBookDao::new)
            .collect(Collectors.toList());
    }

    @Override
    public GuestBookDao save(GuestBookDao guestBookDao) {
        GuestBook saveGuestBook = guestBookRepository.save(GuestBook.builder()
            .name(guestBookDao.getName())
            .password(guestBookDao.getPassword())
            .content(guestBookDao.getContent())
            .build());
        return new GuestBookDao(saveGuestBook);
    }

    @Override
    public void delete(GuestBookDeleteDao guestBookDeleteDao) {
        Optional<GuestBook> guestBook = guestBookRepository
            .findByIdAndPassword(guestBookDeleteDao.getNo(), guestBookDeleteDao.getPassword());
        if(guestBook.isPresent()){
            guestBookRepository.deleteById(guestBook.get().getNo());
        }else{
            throw new IllegalArgumentException("Can not Remove");
        }
    }
}
