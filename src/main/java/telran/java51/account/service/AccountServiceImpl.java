package telran.java51.account.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import telran.java51.account.dao.AccountRepository;
import telran.java51.account.dto.RoleDto;
import telran.java51.account.dto.UpdateUserDto;
import telran.java51.account.dto.UserDto;
import telran.java51.account.dto.UserRegisterDto;
import telran.java51.account.dto.exception.AccountNotException;
import telran.java51.account.dto.exception.AlreadyExists;
import telran.java51.account.model.Account;

@Controller
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	final AccountRepository accountRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto userRegister(UserRegisterDto userRegisterDto) {


		if(accountRepository.existsById(userRegisterDto.getLogin())) {
			throw new AlreadyExists();
		}

		Account account = modelMapper.map(userRegisterDto, Account.class);
		account.addRole("USER");
		account = accountRepository.save(account);
		return modelMapper.map(account, UserDto.class);
	}

	@Override
	public UserDto userLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto deleteUser(String user) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		accountRepository.deleteById(user);
		return modelMapper.map(account, UserDto.class);
	}

	@Override
	public UserDto userUpdate(String user, UpdateUserDto updateUserDto) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		if (updateUserDto.getFirstName() != null) {
			account.setFirstName(updateUserDto.getFirstName());
		}
		if (updateUserDto.getLastName() != null) {
			account.setLastName(updateUserDto.getLastName());
		}
		accountRepository.save(account);
		return modelMapper.map(account, UserDto.class);
	}

	@Override
	public RoleDto addRole(String user, String role) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		account.addRole(role);
		accountRepository.save(account);
		return modelMapper.map(account, RoleDto.class);
	}

	@Override
	public RoleDto deleteRole(String user, String role) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		account.removeRole(role);
		accountRepository.save(account);
		return modelMapper.map(account, RoleDto.class);
	}

	@Override
	public void changePassword() {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDto getUser(String user) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		return modelMapper.map(account, UserDto.class);
	}

}