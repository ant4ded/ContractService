package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.dto.*;
import my.idp.spring.contract.entity.Contract;
import my.idp.spring.contract.exception.EntityNotFoundException;
import my.idp.spring.contract.mapper.ContractMapper;
import my.idp.spring.contract.repository.ContractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContractService {
	private final ContractRepository repository;
	private final ContractMapper mapper;
	private final ContractItemService contractItemService;

	@Transactional
	public ContractResponseVo create(ContractRequestDto contractDTO) {
		Contract contract = mapper.mapToEntity(contractDTO);
		contract = repository.save(contract);

		ContractResponseVo contractResponseVo = mapper.mapToDto(contract);
		contractResponseVo.getItems().clear();

		int itemId = 1;
		for (ContractItemRequestDto itemDto : contractDTO.getItems()) {
			itemDto.setId(itemId++);
			itemDto.setDocId(contract.getId());
			ContractItemResponseDto itemResponseDto = contractItemService.create(itemDto);
			itemResponseDto = contractItemService.calculateContractItemPrices(itemResponseDto, contractResponseVo, itemDto.getPricePerUnit());
			contractResponseVo.getItems().add(itemResponseDto);
		}

		return contractResponseVo;
	}

	public ContractResponseVo getById(Long id) {
		Contract contract = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("%s with id %s not found.", Contract.class.getSimpleName(), id)));
		ContractResponseVo contractResponseVo = mapper.mapToDto(contract);
		for (ContractItemResponseDto itemVo : contractResponseVo.getItems()) {
			contractItemService.calculateContractItemPrices(itemVo, contractResponseVo, itemVo.getPricePerUnit());
		}
		return contractResponseVo;
	}

	public Page<ContractResponseVo> getAll(PageDto pageDto) {
		Pageable pageable;
		if (pageDto.getSort() == null) {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
		} else {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by(pageDto.getSort().getDirection(), pageDto.getSort().getField()));
		}
		return repository.findAll(pageable).map(contract -> {
			ContractResponseVo contractResponseVo = mapper.mapToDto(contract);
			for (ContractItemResponseDto itemVo : contractResponseVo.getItems()) {
				contractItemService.calculateContractItemPrices(itemVo, contractResponseVo, itemVo.getPricePerUnit());
			}
			return contractResponseVo;
		});
	}

	public Page<ContractResponseVo> getAllFrames(PageDto pageDto) {
		Pageable pageable;
		if (pageDto.getSort() == null) {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
		} else {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by(pageDto.getSort().getDirection(), pageDto.getSort().getField()));
		}
		return repository.findAllByFrameIs(true, pageable).map(contract -> {
			ContractResponseVo contractResponseVo = mapper.mapToDto(contract);
			for (ContractItemResponseDto itemVo : contractResponseVo.getItems()) {
				contractItemService.calculateContractItemPrices(itemVo, contractResponseVo, itemVo.getPricePerUnit());
			}
			return contractResponseVo;
		});
	}

	@PostFilter("hasRole('ADMIN') or !filterObject.secretData")
	public List<ContractResponseVo> getDailyReport() {
		return repository.getDailyReport().stream().map(contract -> {
			ContractResponseVo contractResponseVo = mapper.mapToDto(contract);
			for (ContractItemResponseDto itemVo : contractResponseVo.getItems()) {
				contractItemService.calculateContractItemPrices(itemVo, contractResponseVo, itemVo.getPricePerUnit());
			}
			return contractResponseVo;
		}).collect(Collectors.toList());
	}

	@Transactional
	public ContractResponseVo update(Long id, ContractRequestDto contractDTO) {
		Contract updated = mapper.mapToEntity(contractDTO);
		updated.setId(id);
		Contract saved = repository.save(updated);
		ContractResponseVo contractResponseVo = mapper.mapToDto(saved);
		for (ContractItemResponseDto itemVo : contractResponseVo.getItems()) {
			contractItemService.calculateContractItemPrices(itemVo, contractResponseVo, itemVo.getPricePerUnit());
		}
		return contractResponseVo;
	}

	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
