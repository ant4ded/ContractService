package my.idp.spring.contract.repository;

import my.idp.spring.contract.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
	Page<Contract> findAllByFrameIs(boolean frame, Pageable pageable);

	@Query(
			"select c " +
			"from Contract c " +
			"where c.registrationDate = CURRENT_DATE and " +
				  "c.contractCurrency = 'BYN' and " +
				  "c.paymentCurrency = 'BYN'"
	)
	List<Contract> getDailyReport();
}
