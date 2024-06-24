package hexa.wynkong.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import hexa.wynkong.dao.ProjectDao;
import hexa.wynkong.dao.impl.UserLoginRepository;
import hexa.wynkong.entity.UserEntity;
import hexa.wynkong.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	ProjectDao projectDao;
	@Autowired
	UserLoginRepository userLoginRepo;
	
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public int deleteById(int id) {
		return projectDao.deleteById(id);
	}

	@Override
	public List<UserEntity> findAll() {
		return projectDao.findAll();
	}

	@Override
	public List<UserEntity> getDetailById(int id) {
		return projectDao.getDetailById(id);
	}

	@Override
	public int save(UserEntity userEntity) {
		return projectDao.save(userEntity);
	}

	@Override
	public int update(UserEntity userEntity) {
		return projectDao.update(userEntity);
	}

	@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user= userLoginRepo.findByEmail(username);
			if(user==null) {
				throw new UsernameNotFoundException("user not found with email-"+username);
				
			}
			List<GrantedAuthority>authorities=new ArrayList<>();
				return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities) ;
			}

}

	
