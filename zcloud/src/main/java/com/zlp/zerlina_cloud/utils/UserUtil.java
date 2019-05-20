package com.zlp.zerlina_cloud.utils;

import com.zlp.zerlina_cloud.domain.RegisterEntity;
import com.zlp.zerlina_cloud.domain.UsersEntity;



public class UserUtil {
	static public UsersEntity convert(RegisterEntity user){
		UsersEntity entity = new UsersEntity();
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setUid(user.getUid());
		entity.setUsername(user.getUsername());
		return entity;
	}
}