package my.idp.spring.contract.mapper;

public interface EntityVoMapper<E, D, V> {
	E mapToEntity(D dto);

	V mapToVo(E entity);
}
