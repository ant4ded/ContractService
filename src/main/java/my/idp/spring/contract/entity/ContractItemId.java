package my.idp.spring.contract.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContractItemId implements Serializable {
    @Column(name = "id")
    Integer id;
    @Column(name = "doc_id")
    Long docId;
}
