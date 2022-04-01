package hello.mysite.dao.board;

import hello.mysite.dao.Gender;
import hello.mysite.entity.Board;
import hello.mysite.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@NoArgsConstructor
public class BoardDto {
    private Long no;
    private String title;
    private String contents;
    private Long hit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private Long gNo;
    private Long oNo;
    private Long depth;
    private UserDto user;
    private String status;

    public BoardDto(Board board){
        this.no = board.getNo();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.hit = board.getHit();
        this.createdAt = board.getCreatedAt();
        this.gNo = board.getGNo();
        this.oNo = board.getONo();
        this.depth = board.getDepth();
        this.user = new UserDto(board.getUser());
        this.status = board.getStatus();
    }
    @Data
    static class UserDto{
        private Long no;
        private String name;
        private String email;
        private String password;
        private Gender gender;

        public UserDto(User user) {
            this.no = user.getNo();
            this.name = user.getName();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.gender = user.getGender();
        }
    }
}
