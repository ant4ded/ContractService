package my.idp.spring.contract.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContractItemResponseDto {
    private final Integer id;
    private final Long docId;
    private final String goodName;
    private final Integer quantity;
    @JsonIgnore
    private final BigDecimal pricePerUnit;
    private final String units;
    private final String currencyType;
    BigDecimal pricePerUnitInContractCurrency;
    BigDecimal pricePerUnitInPaymentCurrency;
}
