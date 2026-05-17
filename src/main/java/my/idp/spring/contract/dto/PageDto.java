package my.idp.spring.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    @Min(0)
    int page;
    @Min(1)
    @Max(100)
    int size;
    SortParam sort;
}
