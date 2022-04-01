package hello.mysite.dao.board;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class InsertBoardDto {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private Long oNo;
    private Long gNo;
    private Long depth;

}
