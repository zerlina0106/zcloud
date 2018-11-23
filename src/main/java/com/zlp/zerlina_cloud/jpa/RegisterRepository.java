package com.zlp.zerlina_cloud.jpa;


import com.zlp.zerlina_cloud.domain.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface RegisterRepository  extends JpaRepository<RegisterEntity,Integer> {
	@Override
	<S extends RegisterEntity> S save(S entity);

	RegisterEntity findByUid(String uid);

	RegisterEntity findByUsername(String username);

	@Transactional
	void deleteByUid(String uid);
}