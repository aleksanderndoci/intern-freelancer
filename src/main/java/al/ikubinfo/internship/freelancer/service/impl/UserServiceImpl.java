package al.ikubinfo.internship.freelancer.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import al.ikubinfo.internship.freelancer.entity.Users;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.UserModel;
import al.ikubinfo.internship.freelancer.repository.UserRepository;
import al.ikubinfo.internship.freelancer.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository userRepository;

	private final Mapper<Users, UserModel> userMapper;

	public UserServiceImpl(UserRepository userRepository, Mapper<Users, UserModel> userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserModel register(UserModel userModel) {
		
		Users userEntity =userMapper.toEntity(userModel);
		userRepository.save(userEntity);
						
		return userMapper.toModel(userEntity);
	}

	@Override
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

}
