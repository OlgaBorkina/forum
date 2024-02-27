package telran.java51.security.contex;

import telran.java51.security.model.User;

public interface SecuriteContext {
User addUserSession(String sessionId, User user);
	
	User removeUserSession(String sessionId);
	
	User getUserBySessionId(String sessionId);
	

}
