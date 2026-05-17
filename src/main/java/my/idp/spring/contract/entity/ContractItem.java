package my.idp.spring.contract.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract_item")
public class ContractItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "doc_id")
    private Long docId;
    @Column(name = "good_name")
    private String goodName;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;
    @Column(name = "units")
    private String units;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
}
