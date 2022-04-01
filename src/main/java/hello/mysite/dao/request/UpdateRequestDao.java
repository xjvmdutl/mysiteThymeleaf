package hello.mysite.dao.request;

import hello.mysite.dao.Gender;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRequestDao {
    private Long no;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Size(min = 2, max = 8 , message = "이름은 2~8 자리 사이어야 합니다.")
    private String name;

    private String email;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;

    @NotNull(message = "성별은 필수 입니다")
    private Gender gender;

}
