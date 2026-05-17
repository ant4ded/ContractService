package my.idp.spring.contract.dto;

import lombok.Value;

import java.util.List;

@Value
public class ContractResponseVo {
    Long id;
    String title;
    String registrationDate;
    String registrationNumber;
    String contractCurrency;
    String paymentCurrency;
    boolean frame;
    List<ContractItemResponseVo> items;
}
