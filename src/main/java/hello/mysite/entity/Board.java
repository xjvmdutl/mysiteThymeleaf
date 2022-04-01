package hello.mysite.entity;

import static javax.persistence.FetchType.LAZY;

import hello.mysite.dao.board.DetailBoardDto;
import hello.mysite.entity.util.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long no;

    private String title;

    private String contents;

    private Long hit;

    private Long gNo;

    private Long oNo;

    private Long depth;

    private String status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void updateHits() {
        this.hit++;
    }

    public void update(DetailBoardDto boardDto) {
        this.contents = boardDto.getContents();
        this.title = boardDto.getTitle();
        this.status = "update";
    }
}
