package hello.mysite.dao.board;

import hello.mysite.entity.Board;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertBoardDto {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private Long oNo;
    private Long gNo;
    private Long depth;

    public InsertBoardDto(Board board){
        this.oNo = board.getONo();
        this.gNo = board.getGNo();
        this.depth = board.getDepth();
    }
}
