package my.idp.spring.contract.mapper;

public interface EntityMapper<E, D, V> {
	E mapToEntity(D dto);

	V mapToVo(E entity);
}
