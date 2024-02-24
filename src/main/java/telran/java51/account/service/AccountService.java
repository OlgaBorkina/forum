package telran.java51.account.service;

import telran.java51.account.dto.RoleDto;
import telran.java51.account.dto.UpdateUserDto;
import telran.java51.account.dto.UserDto;
import telran.java51.account.dto.UserRegisterDto;

public interface AccountService {
	
	UserDto userRegister(UserRegisterDto userRegisterDto);
	
	
	
	UserDto deleteUser(String user);
	
	UserDto userUpdate(String user, UpdateUserDto updateUserDto);
	
	RoleDto addRole(String user,String role);
	
	RoleDto deleteRole(String user,String role);
	
	UserDto getUser(String user);
	
	void changePassword(String login, String newPassword);

	
	
	

}
