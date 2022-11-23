package com.gcu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.UserBusinessService;
import com.gcu.model.UserModel;


/**
 * Add a new REST service class named UsersRestService to the project in the
com.gcu.api package. The class should be marked with the @RestController
annotation and @RequestMapping(“/service”) annotation. Add an API named
getUsers() that is marked with the @GetMapping(“/users”) annotation that takes
no method arguments. Inject an instance of the UserBusinessService named
service into the class. The implementation should call the getAllUsers() method
from the service variable. The method should return an instance of
ResponseEntity with a response code of HttpStatus.OK if no error and a response
code of HttpStatus.INTERNAL_SERVER_ERROR in a try catch block.
 * @author jjk14
 *
 */
@RestController
@RequestMapping("/service")
public class UsersRestService {

	@Autowired
	UserBusinessService service;
	
	@GetMapping(path="/users")
	public ResponseEntity<?> getUsers() {
		service.getAllUsers();
		try {
			List<UserModel> users = service.getAllUsers();
			if(users == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			
		}
		catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
