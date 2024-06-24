package hexa.wynkong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hexa.wynkong.entity.UserEntity;
import hexa.wynkong.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/home")
	public String Home() {
		return "welcome to home page";
	}

	@GetMapping("/detail")
	public List<UserEntity> getAllUserEntitys() {
		return projectService.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<List<UserEntity>> getUserEntityById(@PathVariable int id) {
		List<UserEntity> userDetails = projectService.getDetailById(id);
		if (!userDetails.isEmpty()) {
			return new ResponseEntity<>(userDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/user")
	public int createUserEntity(@RequestBody UserEntity UserEntity) {
		return projectService.save(UserEntity);
	}

	@PutMapping("/{id}")
	public int updateUserEntity(@PathVariable int id, @RequestBody UserEntity UserEntity) {
		UserEntity.setId(id);
		return projectService.update(UserEntity);
	}

	@DeleteMapping("/{id}")
	public int deleteUserEntity(@PathVariable int id) {
		return projectService.deleteById(id);
	}

}
