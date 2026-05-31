package my.idp.spring.contract.controller;

import my.idp.spring.contract.service.ContractItemService;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contract/item")
public class ContractItemController {
	private final ContractItemService contractItemService;

	public ContractItemController(ContractItemService contractItemService) {
		this.contractItemService = contractItemService;
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PostMapping
	public ResponseEntity<ContractItemResponseDto> create(@Valid @RequestBody ContractItemRequestDto contractItemDto) {
		return ResponseEntity.ok(contractItemService.create(contractItemDto));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{id}/{docId}")
	public ResponseEntity<ContractItemResponseDto> getById(@PathVariable Integer id, @PathVariable Long docId) {
		return ResponseEntity.ok(contractItemService.getById(id, docId));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("/{id}/{docId}")
	public ResponseEntity<ContractItemResponseDto> update(@PathVariable Integer id, @PathVariable Long docId, @Valid @RequestBody ContractItemRequestDto contractItemDto) {
		return ResponseEntity.ok(contractItemService.update(id, docId, contractItemDto));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@DeleteMapping("/{id}/{docId}")
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Long docId) {
		contractItemService.delete(id, docId);
		return ResponseEntity.noContent().build();
	}
}
