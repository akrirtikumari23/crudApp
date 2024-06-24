package hexa.wynkong.dao;

import java.util.List;
import hexa.wynkong.entity.UserEntity;

public interface ProjectDao  {

	int deleteById(int id);

	List<UserEntity> findAll();

	List<UserEntity> getDetailById(int id);

	int save(UserEntity userEntity);

	int update(UserEntity userEntity);

}
