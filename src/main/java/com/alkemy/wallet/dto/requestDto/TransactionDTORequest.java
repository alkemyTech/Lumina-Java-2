package com.alkemy.wallet.dto.requestDto;

import com.alkemy.wallet.model.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionDTORequest {
    private Long idAccountAdressee;
    private Integer amount;
    private Type type;
}
