package my.idp.spring.contract.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "registration_date", columnDefinition = "DATE")
    private Date registrationDate;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "contract_currency")
    private String contractCurrency;
    @Column(name = "payment_currency")
    private String paymentCurrency;
    @Column(name = "frame")
    private boolean frame;
    @Column(name = "secret_data")
    private boolean secretData;

    @OneToMany(mappedBy = "contract", fetch = FetchType.EAGER)
    private List<ContractItem> items;

    @PrePersist
    private void prePersist() {
        createDate = new Date();
    }
}
