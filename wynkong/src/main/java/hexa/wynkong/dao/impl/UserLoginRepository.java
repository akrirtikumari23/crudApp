package hexa.wynkong.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hexa.wynkong.entity.UserEntity;

public interface UserLoginRepository extends JpaRepository<UserEntity, Integer>{
	public UserEntity findByEmail(String email);
	
	/*
	 * @Query("SELECT u FROM User u WHERE u.email = :email") UserEntity
	 * findUserByEmail(@Param("email") String email);
	 */
}
