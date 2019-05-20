package cn.yyy.user.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "user")
public class User {
    @Id
    private Integer id;

    private String name;

    private String phone;

    private String password;

    @Transient
    private String verifyCode;

}