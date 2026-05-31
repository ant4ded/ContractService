package my.idp.spring.contract.repository;

import my.idp.spring.contract.entity.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, String> {
    boolean existsByToken(String token);
}
