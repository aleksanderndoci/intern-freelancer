package al.ikubinfo.internship.freelancer.service;

import java.util.List;

import al.ikubinfo.internship.freelancer.entity.Users;
import al.ikubinfo.internship.freelancer.model.UserModel;

public interface UserssService {

	UserModel register(UserModel userModel);

	List<Users> getAllUsers();

}
