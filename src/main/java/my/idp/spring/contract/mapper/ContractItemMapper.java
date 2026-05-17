package my.idp.spring.contract.mapper;

import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.entity.CurrencyType;
import my.idp.spring.contract.dto.ContractItemDto;
import org.springframework.stereotype.Service;

@Service
public class ContractItemMapper implements EntityVoMapper<ContractItem, ContractItemDto> {
	@Override
	public ContractItem mapToEntity(ContractItemDto vo) {
		return ContractItem.builder()
				.id(vo.getId())
				.docId(vo.getDocId())
				.goodName(vo.getGoodName())
				.quantity(vo.getQuantity())
				.pricePerUnit(vo.getPricePerUnit())
				.units(vo.getUnits())
				.currencyType(CurrencyType.valueOf(vo.getCurrencyType()))
				.build();
	}

	@Override
	public ContractItemDto mapToDto(ContractItem entity) {
		return new ContractItemDto(entity.getId(),
				entity.getDocId(),
				entity.getGoodName(),
				entity.getQuantity(),
				entity.getPricePerUnit(),
				entity.getUnits(),
				entity.getCurrencyType().name());
	}
}
