package hello.mysite.dao;

import hello.mysite.entity.GuestBook;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookDao {
    private Long no;
    private String name;
    private String password;
    private String content;
    private LocalDateTime updatedAt;

    public GuestBookDao(GuestBook guestBook) {
        this.no = guestBook.getNo();
        this.name = guestBook.getName();
        this.password = guestBook.getPassword();
        this.content = guestBook.getContent();
        this.updatedAt = guestBook.getUpdatedAt();
    }
}
