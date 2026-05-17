package my.idp.spring.contract.repository;

import my.idp.spring.contract.entity.ContractItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractItemRepository extends JpaRepository<ContractItem, Integer> {
}
