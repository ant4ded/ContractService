package my.idp.spring.contract.controller;

import lombok.extern.slf4j.Slf4j;
import my.idp.spring.contract.dto.ContractDto;
import my.idp.spring.contract.dto.PageDto;
import my.idp.spring.contract.service.ContractService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController {
	private final ContractService contractService;

	public ContractController(ContractService contractService) {
		this.contractService = contractService;
	}

	@PostMapping
	public ResponseEntity<ContractDto> create(@Valid @RequestBody ContractDto contractDTO) {
		return ResponseEntity.ok(contractService.create(contractDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContractDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(contractService.getById(id));
	}

	@GetMapping
	public ResponseEntity<Page<ContractDto>> getAll(@Valid @ModelAttribute PageDto pageDto) {
		return ResponseEntity.ok(contractService.getAll(pageDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContractDto> update(@PathVariable Long id, @Valid @RequestBody ContractDto contractDTO) {
		return ResponseEntity.ok(contractService.update(id, contractDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		contractService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
