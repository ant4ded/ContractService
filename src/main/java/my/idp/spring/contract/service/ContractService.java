package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.dto.ContractDto;
import my.idp.spring.contract.dto.PageDto;
import my.idp.spring.contract.entity.Contract;
import my.idp.spring.contract.mapper.ContractMapper;
import my.idp.spring.contract.repository.ContractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<ContractDto> getAll(PageDto pageDto) {
		Pageable pageable;
		if (pageDto.getSort() == null) {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
		} else {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by(pageDto.getSort().getDirection(), pageDto.getSort().getField()));
		}
		return repository.findAll(pageable).map(mapper::mapToDto);
	}

	public Page<ContractDto> getAllFrames(PageDto pageDto) {
		Pageable pageable;
		if (pageDto.getSort() == null) {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize());
		} else {
			pageable = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by(pageDto.getSort().getDirection(), pageDto.getSort().getField()));
		}
		return repository.findAllByFrameIs(true, pageable).map(mapper::mapToDto);
	}

	public List<ContractDto> getDailyReport() {
		return repository.getDailyReport().stream().map(mapper::mapToDto).collect(Collectors.toList());
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
