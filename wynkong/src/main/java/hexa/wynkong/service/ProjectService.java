package hexa.wynkong.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import hexa.wynkong.entity.UserEntity;

public interface ProjectService {

	int deleteById(int id);

	List<UserEntity> findAll();

	int save(UserEntity userEntity);

	int update(UserEntity userEntity);

	List<UserEntity> getDetailById(int id);

	UserDetails loadUserByUsername(String userName);

}
