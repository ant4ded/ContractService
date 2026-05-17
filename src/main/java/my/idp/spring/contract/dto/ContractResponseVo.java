package my.idp.spring.contract.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class ContractResponseVo {
    Long id;
    String title;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date registrationDate;
    String registrationNumber;
    String contractCurrency;
    String paymentCurrency;
    boolean frame;
    List<ContractItemResponseDto> items;
}
