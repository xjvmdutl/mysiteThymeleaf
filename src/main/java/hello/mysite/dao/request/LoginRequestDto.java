package hello.mysite.dao.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {

    @NotEmpty(message = "이메일은 필수 입니다")
    @Email(message = "email 형식이 아닙니다.")
    private String email;
    
    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;

}
