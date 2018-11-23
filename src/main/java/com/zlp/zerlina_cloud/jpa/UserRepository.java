package com.zlp.zerlina_cloud.jpa;

import com.zlp.zerlina_cloud.domain.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UsersEntity,Integer> {
	 UsersEntity findByUsername(String username);

	 UsersEntity findByEmail(String email);

	 UsersEntity findByUid(String uid);

	 UsersEntity findByUsernameOrEmail(String username, String email);

	 UsersEntity findByUsernameAndPassword(String username, String password);


	@Override
	<S extends UsersEntity> S save(S s);
}
