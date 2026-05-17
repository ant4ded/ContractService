package my.idp.spring.contract.repository;

import my.idp.spring.contract.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
	Page<Contract> findAllByFrameIs(boolean frame, Pageable pageable);
}
