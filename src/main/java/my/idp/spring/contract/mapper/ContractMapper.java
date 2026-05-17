package my.idp.spring.contract.mapper;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.entity.Contract;
import my.idp.spring.contract.dto.ContractRequestDto;
import my.idp.spring.contract.dto.ContractResponseVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContractMapper implements EntityMapper<Contract, ContractRequestDto, ContractResponseVo> {
	private final ContractItemMapper contractItemMapper;

	@Override
	public ContractResponseVo mapToDto(Contract entity) {
		return new ContractResponseVo(entity.getId(),
				entity.getTitle(),
				entity.getRegistrationDate(),
				entity.getRegistrationNumber(),
				entity.getContractCurrency(),
				entity.getPaymentCurrency(),
				entity.isFrame(),
				entity.getItems().stream().map(contractItemMapper::mapToDto)
						.collect(Collectors.toList()));
	}

	public Contract mapToEntity(ContractRequestDto vo) {
		return Contract.builder()
				.title(vo.getTitle())
				.registrationDate(new Date())
				.registrationNumber(vo.getRegistrationNumber())
				.contractCurrency(vo.getContractCurrency())
				.paymentCurrency(vo.getPaymentCurrency())
				.frame(vo.isFrame())
				.items(vo.getItems().stream().map(contractItemMapper::mapToEntity).collect(Collectors.toList()))
				.build();
	}
}
