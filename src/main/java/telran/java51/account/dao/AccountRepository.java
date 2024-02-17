package telran.java51.account.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java51.account.model.Account;

public interface AccountRepository  extends CrudRepository<Account, String>{
		
}
