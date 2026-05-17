package my.idp.spring.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

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
}
