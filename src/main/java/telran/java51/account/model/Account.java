package telran.java51.account.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@EqualsAndHashCode(of = "login")
@Document(collection = "accounts")
@NoArgsConstructor
public class Account {
	@Id
	String login;
	@Setter
	String firstName;
	@Setter
	String lastName;
	@Setter
	String password;
	@Singular
	Set<String> roles  = new HashSet<>();
	
	public Account(String login, String firstName, String lastName, Set<String> roles) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		roles = new HashSet<>();
		
	}
	
	public boolean addRole(String role) {
		return roles.add(role);
	}
	public boolean removeRole(String role) {
		return roles.remove(role);
	}
	
	
	
	

}
