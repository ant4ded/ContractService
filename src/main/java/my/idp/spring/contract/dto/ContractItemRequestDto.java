package my.idp.spring.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.idp.spring.contract.entity.CurrencyType;
import my.idp.spring.contract.validation.PossibleValues;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractItemRequestDto {
    Integer id;
    Long docId;
    @NotNull
    String goodName;
    @Min(1)
    @NotNull
    Integer quantity;
    @Min(1)
    @NotNull
    BigDecimal pricePerUnit;
    @NotNull
    String units;
    @NotNull
    @PossibleValues(CurrencyType.class)
    String currencyType;
}
