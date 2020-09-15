package com.geekbrains.geekmarket.entities;

import com.geekbrains.geekmarket.utils.FieldMatch;
import com.geekbrains.geekmarket.utils.ValidEmail;
import com.geekbrains.geekmarket.utils.ValidPassword;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "Пароли должны совпадать")
public class SystemUser {
    @NotNull(message = "требуется")
    @Size(min = 3, message = "имя должно быть как минимум 2 символа")
//    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 letters/digits")
    private String userName;

    @ValidPassword
    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String password;

    @ValidPassword
    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String matchingPassword;

    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String firstName;

    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String lastName;

    @ValidEmail
    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String email;

    @NotNull(message = "требуется")
    @Column(name = "phone")
    private String phone;

//    @NotNull
//    @Min(value = 0, message = "value must be greater or equals than 0")
//    @Max(value = 10, message = "value must be lesser or equals than 10")
//    private Integer count;
}
