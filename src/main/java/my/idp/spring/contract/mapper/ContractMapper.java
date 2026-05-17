package my.idp.spring.contract.mapper;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.entity.Contract;
import my.idp.spring.contract.dto.ContractDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContractMapper implements EntityVoMapper<Contract, ContractDto> {
	private final ContractItemMapper contractItemMapper;

	public ContractDto mapToDto(Contract entity) {
		return new ContractDto(entity.getId(),
				entity.getTitle(),
				entity.getRegistrationDate().toString(),
				entity.getRegistrationNumber(),
				entity.getContractCurrency(),
				entity.getPaymentCurrency(),
				entity.isFrame(),
				entity.getItems().stream().map(contractItemMapper::mapToDto)
						.collect(Collectors.toList()));
	}

	public Contract mapToEntity(ContractDto vo) {
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
