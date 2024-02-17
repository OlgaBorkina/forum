package telran.java51.account.dto;

import java.util.Set;

import lombok.Getter;
@Getter
public class UserDto {
	String login;
	String firstName;
	String lastName;
	Set<String> roles;

}
