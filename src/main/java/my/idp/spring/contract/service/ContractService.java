package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractRequestDto;
import my.idp.spring.contract.dto.ContractResponseVo;
import my.idp.spring.contract.dto.PageDto;
import my.idp.spring.contract.entity.Contract;
import my.idp.spring.contract.mapper.ContractMapper;
import my.idp.spring.contract.repository.ContractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

		ContractResponseVo contractResponseVo = mapper.mapToVo(contract);
		contractResponseVo.getItems().clear();

		int itemId = 1;
		for (ContractItemRequestDto itemDto : contractDTO.getItems()) {
			itemDto.setId(itemId++);
			itemDto.setDocId(contract.getId());
			contractResponseVo.getItems().add(contractItemService.create(itemDto));
		}

		return contractResponseVo;
	}

	public ContractResponseVo getById(Long id) {
		Contract contract = repository.findById(id).orElse(null);
		return mapper.mapToVo(contract);
	}

	public Page<ContractResponseVo> getAll(PageDto pageDto) {
		Pageable pageable;
		if (pageDto.getSort() == null) {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
		} else {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by(pageDto.getSort().getDirection(), pageDto.getSort().getField()));
		}
		return repository.findAll(pageable).map(mapper::mapToVo);
	}

	public Page<ContractResponseVo> getAllFrames(PageDto pageDto) {
		Pageable pageable;
		if (pageDto.getSort() == null) {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
		} else {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by(pageDto.getSort().getDirection(), pageDto.getSort().getField()));
		}
		return repository.findAllByFrameIs(true, pageable).map(mapper::mapToVo);
	}

	public List<ContractResponseVo> getDailyReport() {
		return repository.getDailyReport().stream().map(mapper::mapToVo).collect(Collectors.toList());
	}

	public ContractResponseVo update(Long id, ContractRequestDto contractDTO) {
		Contract contract = repository.findById(id).orElse(null);
		if (contract != null) {
			Contract updated = mapper.mapToEntity(contractDTO);
			updated.setId(id);
			contract = repository.save(updated);
		}
		return mapper.mapToVo(contract);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
