package my.idp.spring.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.idp.spring.contract.entity.CurrencyType;
import my.idp.spring.contract.validation.PossibleValues;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractItemDto {
    Integer id;
    Long docId;
    String goodName;
    Integer quantity;
    BigDecimal pricePerUnit;
    String units;
    @NotNull
    @PossibleValues(CurrencyType.class)
    String currencyType;
}
