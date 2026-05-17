package my.idp.spring.contract.mapper;

import my.idp.spring.contract.entity.ContractItem;
import my.idp.spring.contract.entity.CurrencyType;
import my.idp.spring.contract.dto.ContractItemRequestDto;
import my.idp.spring.contract.dto.ContractItemResponseVo;
import org.springframework.stereotype.Service;

@Service
public class ContractItemMapper implements EntityVoMapper<ContractItem, ContractItemRequestDto, ContractItemResponseVo> {
	@Override
	public ContractItem mapToEntity(ContractItemRequestDto vo) {
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
	public ContractItemResponseVo mapToVo(ContractItem entity) {
		return new ContractItemResponseVo(entity.getId(),
				entity.getDocId(),
				entity.getGoodName(),
				entity.getQuantity(),
				entity.getPricePerUnit(),
				entity.getUnits(),
				entity.getCurrencyType().name());
	}
}
