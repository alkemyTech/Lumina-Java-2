package com.alkemy.wallet.dto.requestDto;

import com.alkemy.wallet.model.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class UserModelRequestDTO {
    @NotNull(message = "el campo firstName no puede ser nulo")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras ni estar vacio.")
    private String firstName;
    @NotNull(message = "el campo firstName no puede ser nulo")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras ni estar vacio.")
    private String lastName;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Ingrese un mail valido")
    private String email;

    @NotBlank(message = "el campo password no tener espacios en blanco")
    private String password;

    private String role;

    private LocalDate creationDate;

    private LocalDate updateDate;

    private List<Account> accountsList = new ArrayList();
}
