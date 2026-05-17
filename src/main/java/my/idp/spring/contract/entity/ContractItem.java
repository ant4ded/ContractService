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
    @EmbeddedId
    private ContractItemId id;
    @Column(name = "good_name")
    private String goodName;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;
    @Column(name = "units")
    private String units;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "doc_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Contract contract;
}
