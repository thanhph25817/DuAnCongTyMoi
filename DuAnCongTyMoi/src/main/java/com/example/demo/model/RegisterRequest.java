package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotNull(message = "không để trống first name")
    private String firstName;
    @NotNull(message = "không để trống last name")
    private String lastName;
    @Email(message = "email phải đúng định dạng ")
    @NotNull(message = "không để trống email")
    private String email;
    @Email(message = "email phải đúng định dạng ")
    @NotNull(message = "không để trống email")
    private String emailAgain;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;
    private String gioiTinh;
    @NotNull(message = "không để trống password")
    @Size(min = 5,message = "pass phai co it nhat 5 ky tu ")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "password phải bao gồm ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt")
    private String passWord;
}
