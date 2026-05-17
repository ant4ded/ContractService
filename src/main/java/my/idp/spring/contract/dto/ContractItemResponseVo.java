package my.idp.spring.contract.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ContractItemResponseVo {
    Integer id;
    Long docId;
    String goodName;
    Integer quantity;
    BigDecimal pricePerUnit;
    String units;
    String currencyType;
}
