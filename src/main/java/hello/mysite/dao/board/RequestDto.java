package hello.mysite.dao.board;

import hello.mysite.entity.Board;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RequestDto {
    private Long oNo;
    private Long gNo;
    private Long depth;

    public RequestDto(Board board) {
        this.oNo = board.getONo();
        this.gNo = board.getGNo();
        this.depth = board.getDepth();
    }
}