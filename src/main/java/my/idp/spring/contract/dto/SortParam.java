package my.idp.spring.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.Sort.Direction;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortParam {
    String field;
    Direction direction = Direction.ASC;
}
