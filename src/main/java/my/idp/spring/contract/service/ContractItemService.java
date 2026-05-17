package my.idp.spring.contract.service;

import lombok.AllArgsConstructor;
import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.entity.ContractItemId;
import my.idp.spring.contract.entity.CurrencyType;
import my.idp.spring.contract.exception.EntityNotFoundException;
import my.idp.spring.contract.mapper.ContractItemMapper;
import my.idp.spring.contract.repository.ContractItemRepository;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseDto;
import my.idp.spring.contract.dto.ContractResponseVo;
import my.idp.spring.dto.CurrencyRateDto;
import my.idp.spring.service.CurrencyRateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ContractItemService {
	private final ContractItemRepository repository;
	private final ContractItemMapper mapper;
	private final CurrencyRateService currencyRateService;

    @Transactional
    public ContractItemResponseDto create(ContractItemRequestDto contractItemDto) {
		ContractItemId id = new ContractItemId(contractItemDto.getId(), contractItemDto.getDocId());
		ContractItem contractItem = mapper.mapToEntity(contractItemDto);
		contractItem.setId(id);
		contractItem = repository.save(contractItem);
		return mapper.mapToDto(contractItem);
	}

	public ContractItemResponseDto getById(Integer itemId, Long docId) {
		ContractItemId id = new ContractItemId(itemId, docId);
		ContractItem contractItem = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("%s with id %s not found.", ContractItem.class.getSimpleName(), id)));
		return mapper.mapToDto(contractItem);
	}

	public ContractItemResponseDto update(Integer itemId, Long docId, ContractItemRequestDto contractItemDto) {
		ContractItemId id = new ContractItemId(itemId, docId);
		ContractItem updated = mapper.mapToEntity(contractItemDto);
		updated.setId(id);
		repository.save(updated);
		return mapper.mapToDto(updated);
	}

	public void delete(Integer itemId, Long docId) {
		ContractItemId id = new ContractItemId(itemId, docId);
		repository.deleteById(id);
	}

	public ContractItemResponseDto calculateContractItemPrices(ContractItemResponseDto dto, ContractResponseVo contractVo, BigDecimal pricePerUnit) {
		dto.setPricePerUnitInContractCurrency(pricePerUnit);
		dto.setPricePerUnitInPaymentCurrency(pricePerUnit);
		if (!Objects.equals(contractVo.getContractCurrency(), contractVo.getPaymentCurrency())) {

			String contractCurrency = contractVo.getContractCurrency();
			String paymentCurrency = contractVo.getPaymentCurrency();
			LocalDate registrationDate = contractVo.getRegistrationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if (dto.getCurrencyType().equals(CurrencyType.CONTRACT.name())) {
				CurrencyRateDto rate = currencyRateService.getRate(contractCurrency, paymentCurrency, registrationDate);
				dto.setPricePerUnitInPaymentCurrency(pricePerUnit.multiply(rate.getRate()));
			} else {
				CurrencyRateDto rate = currencyRateService.getRate(paymentCurrency, contractCurrency, registrationDate);
				dto.setPricePerUnitInContractCurrency(pricePerUnit.multiply(rate.getRate()));
			}
		}
		return dto;
	}
}
