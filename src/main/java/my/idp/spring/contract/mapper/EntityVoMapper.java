package my.idp.spring.contract.mapper;

public interface EntityVoMapper<E, V> {
	V mapToDto(E entity);

	E mapToEntity(V vo);
}
