package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.entity.Contract;
import my.idp.spring.contract.mapper.ContractMapper;
import my.idp.spring.contract.repository.ContractRepository;
import my.idp.spring.contract.dto.ContractDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContractService {
	private final ContractRepository repository;
	private final ContractMapper mapper;

	public ContractDto create(ContractDto contractDTO) {
		Contract contract = mapper.mapToEntity(contractDTO);
		contract = repository.save(contract);
		return mapper.mapToDto(contract);
	}

	public ContractDto getById(Long id) {
		Contract contract = repository.findById(id).orElse(null);
		return mapper.mapToDto(contract);
	}

	public List<ContractDto> getAll() {
		return repository.findAll().stream().map(mapper::mapToDto).collect(Collectors.toList());
	}

	public ContractDto update(Long id, ContractDto contractDTO) {
		Contract contract = repository.findById(id).orElse(null);
		if (contract != null) {
			Contract updated = mapper.mapToEntity(contractDTO);
			updated.setId(id);
			contract = repository.save(updated);
		}
		return mapper.mapToDto(contract);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
