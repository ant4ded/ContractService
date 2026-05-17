package my.idp.spring.contract.mapper;

import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.entity.ContractItemId;
import my.idp.spring.contract.entity.CurrencyType;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ContractItemMapper implements EntityMapper<ContractItem, ContractItemRequestDto, ContractItemResponseDto> {
	@Override
	public ContractItem mapToEntity(ContractItemRequestDto vo) {
		ContractItemId id = new ContractItemId(vo.getId(), vo.getDocId());
		return ContractItem.builder()
				.id(id)
				.goodName(vo.getGoodName())
				.quantity(vo.getQuantity())
				.pricePerUnit(vo.getPricePerUnit())
				.units(vo.getUnits())
				.currencyType(CurrencyType.valueOf(vo.getCurrencyType()))
				.build();
	}

	@Override
	public ContractItemResponseDto mapToDto(ContractItem entity) {
		return new ContractItemResponseDto(entity.getId().getId(),
				entity.getId().getDocId(),
				entity.getGoodName(),
				entity.getQuantity(),
				entity.getPricePerUnit(),
				entity.getUnits(),
				entity.getCurrencyType().name());
	}
}
