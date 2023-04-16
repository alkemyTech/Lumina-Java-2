package com.alkemy.wallet.dto.responseDto;

import com.alkemy.wallet.dto.AccountDTO;
import com.alkemy.wallet.model.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class UserEntityResponseDTO {
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    private LocalDate creationDate;

    private LocalDate updateDate;

    private Boolean softDelete;

    private List<AccountDTO> accountsList = new ArrayList();
}
