package my.idp.spring.contract.controller;

import lombok.extern.slf4j.Slf4j;
import my.idp.spring.contract.dto.ContractRequestDto;
import my.idp.spring.contract.dto.ContractResponseVo;
import my.idp.spring.contract.dto.PageDto;
import my.idp.spring.contract.service.ContractService;
import org.springframework.data.domain.Page;
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
	public ResponseEntity<ContractResponseVo> create(@Valid @RequestBody ContractRequestDto contractDTO) {
		return ResponseEntity.ok(contractService.create(contractDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContractResponseVo> getById(@PathVariable Long id) {
		return ResponseEntity.ok(contractService.getById(id));
	}

	@GetMapping
	public ResponseEntity<Page<ContractResponseVo>> getAll(@Valid @ModelAttribute PageDto pageDto) {
		return ResponseEntity.ok(contractService.getAll(pageDto));
	}

	@GetMapping("/frame")
	public ResponseEntity<Page<ContractResponseVo>> getAllFrames(@Valid @ModelAttribute PageDto pageDto) {
		return ResponseEntity.ok(contractService.getAllFrames(pageDto));
	}

	@GetMapping("/daily-report")
	public ResponseEntity<List<ContractResponseVo>> getDailyReport() {
		return ResponseEntity.ok(contractService.getDailyReport());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContractResponseVo> update(@PathVariable Long id, @Valid @RequestBody ContractRequestDto contractDTO) {
		return ResponseEntity.ok(contractService.update(id, contractDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		contractService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
