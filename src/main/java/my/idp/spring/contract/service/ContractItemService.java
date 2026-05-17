package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.mapper.ContractItemMapper;
import my.idp.spring.contract.repository.ContractItemRepository;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseVo;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContractItemService {
	private final ContractItemRepository repository;
	private final ContractItemMapper mapper;

	public ContractItemResponseVo create(ContractItemRequestDto contractItemDto) {
		ContractItem contractItem = mapper.mapToEntity(contractItemDto);
		contractItem = repository.save(contractItem);
		return mapper.mapToVo(contractItem);
	}

	public ContractItemResponseVo getById(Integer id) {
		ContractItem contractItem = repository.findById(id).orElse(null);
		return mapper.mapToVo(contractItem);
	}

	public ContractItemResponseVo update(Integer id, ContractItemRequestDto contractItemDto) {
		ContractItem contractItem = repository.findById(id).orElse(null);
		if (contractItem != null) {
			ContractItem updated = mapper.mapToEntity(contractItemDto);
			updated.setId(id);
			contractItem = repository.save(updated);
		}
		return mapper.mapToVo(contractItem);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
