package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.entity.ContractItemId;
import my.idp.spring.contract.mapper.ContractItemMapper;
import my.idp.spring.contract.repository.ContractItemRepository;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ContractItemService {
	private final ContractItemRepository repository;
	private final ContractItemMapper mapper;

    @Transactional
    public ContractItemResponseVo create(ContractItemRequestDto contractItemDto) {
		ContractItemId id = new ContractItemId(contractItemDto.getId(), contractItemDto.getDocId());
		ContractItem contractItem = mapper.mapToEntity(contractItemDto);
		contractItem.setId(id);
		contractItem = repository.save(contractItem);
		return mapper.mapToVo(contractItem);
	}

	public ContractItemResponseVo getById(Integer itemId, Long docId) {
		ContractItemId id = new ContractItemId(itemId, docId);
		ContractItem contractItem = repository.findById(id).orElse(null);
		return mapper.mapToVo(contractItem);
	}

	public ContractItemResponseVo update(Integer itemId, Long docId, ContractItemRequestDto contractItemDto) {
		ContractItemId id = new ContractItemId(itemId, docId);
		ContractItem contractItem = repository.findById(id).orElse(null);
		if (contractItem != null) {
			ContractItem updated = mapper.mapToEntity(contractItemDto);
			updated.setId(id);
			contractItem = repository.save(updated);
		}
		return mapper.mapToVo(contractItem);
	}

	public void delete(Integer itemId, Long docId) {
		ContractItemId id = new ContractItemId(itemId, docId);
		repository.deleteById(id);
	}
}
