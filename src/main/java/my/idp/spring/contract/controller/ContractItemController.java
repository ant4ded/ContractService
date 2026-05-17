package my.idp.spring.contract.controller;

import my.idp.spring.contract.service.ContractItemService;
import my.idp.spring.contract.dto.ContractItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contract/item")
public class ContractItemController {
	private final ContractItemService contractItemService;

	public ContractItemController(ContractItemService contractItemService) {
		this.contractItemService = contractItemService;
	}

	@PostMapping
	public ResponseEntity<ContractItemDto> create(@Valid @RequestBody ContractItemDto contractItemDto) {
		return ResponseEntity.ok(contractItemService.create(contractItemDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContractItemDto> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(contractItemService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContractItemDto> update(@PathVariable Integer id, @Valid @RequestBody ContractItemDto contractItemDto) {
		return ResponseEntity.ok(contractItemService.update(id, contractItemDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		contractItemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
