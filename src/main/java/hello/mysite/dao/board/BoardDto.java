package hello.mysite.dao.board;

import com.querydsl.core.annotations.QueryProjection;
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

    //@QueryProjection
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

    @QueryProjection
    public BoardDto(Long no, String title, String contents, Long hit, LocalDateTime createdAt,
        Long gNo, Long oNo, Long depth, Long userNo, String name, String email, String password, Gender gender, String status) {
        this.no = no;
        this.title = title;
        this.contents = contents;
        this.hit = hit;
        this.createdAt = createdAt;
        this.gNo = gNo;
        this.oNo = oNo;
        this.depth = depth;
        this.user = new UserDto(userNo, name, email, password, gender);
        this.status = status;
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

        @QueryProjection
        public UserDto(Long no, String name, String email, String password, Gender gender) {
            this.no = no;
            this.name = name;
            this.email = email;
            this.password = password;
            this.gender = gender;
        }
    }
}
