package my.idp.spring.contract.repository;

import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.entity.ContractItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractItemRepository extends JpaRepository<ContractItem, ContractItemId> {
}
