package my.idp.spring.contract.controller;

import lombok.extern.slf4j.Slf4j;
import my.idp.spring.contract.service.ContractService;
import my.idp.spring.contract.dto.ContractDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	public ResponseEntity<List<ContractDto>> getAll() {
		return ResponseEntity.ok(contractService.getAll());
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
