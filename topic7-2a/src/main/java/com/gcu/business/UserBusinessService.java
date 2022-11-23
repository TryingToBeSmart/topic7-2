package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.UserEntity;
import com.gcu.UsersRepository;
import com.gcu.model.UserModel;

@Service
public class UserBusinessService {
	@Autowired
	UsersRepository usersRepo;
	
	public List<UserModel> getAllUsers(){
		//get all the entity users
		 List<UserEntity> usersEntity = usersRepo.findAll();
		
		 //iterate over entity users and create list of UserModels (Domain Users)
		 List<UserModel> usersDomain = new ArrayList<UserModel>();
		 for(UserEntity user : usersEntity) {
			 usersDomain.add(new UserModel(user.getId(), user.getUsername(), user.getPassword()));
		 }
		 return usersDomain;
	}
}
