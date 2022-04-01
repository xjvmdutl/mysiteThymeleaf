package hello.mysite.entity;

import hello.mysite.dao.Gender;
import hello.mysite.entity.util.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "USERS")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long no;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public void update(String password, String name, Gender gender) {
        this.password = password;
        this.name = name;
        this.gender = gender;
    }
}
