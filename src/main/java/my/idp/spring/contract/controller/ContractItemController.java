package my.idp.spring.contract.controller;

import my.idp.spring.contract.service.ContractItemService;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseVo;
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
	public ResponseEntity<ContractItemResponseVo> create(@Valid @RequestBody ContractItemRequestDto contractItemDto) {
		return ResponseEntity.ok(contractItemService.create(contractItemDto));
	}

	@GetMapping("/{id}/{docId}")
	public ResponseEntity<ContractItemResponseVo> getById(@PathVariable Integer id, @PathVariable Long docId) {
		return ResponseEntity.ok(contractItemService.getById(id, docId));
	}

	@PutMapping("/{id}/{docId}")
	public ResponseEntity<ContractItemResponseVo> update(@PathVariable Integer id, @PathVariable Long docId, @Valid @RequestBody ContractItemRequestDto contractItemDto) {
		return ResponseEntity.ok(contractItemService.update(id, docId, contractItemDto));
	}

	@DeleteMapping("/{id}/{docId}")
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Long docId) {
		contractItemService.delete(id, docId);
		return ResponseEntity.noContent().build();
	}
}
