package hello.mysite.dao.board;

import hello.mysite.dao.Gender;
import hello.mysite.entity.Board;
import hello.mysite.entity.User;
import lombok.Data;

@Data
public class DetailBoardDto {
    private Long no;
    private String title;
    private String contents;
    private UserDto user;
    private Long hit;

    public DetailBoardDto(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.user = new UserDto(board.getUser());
        this.hit = board.getHit();
    }

    @Data
    static class UserDto {

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
