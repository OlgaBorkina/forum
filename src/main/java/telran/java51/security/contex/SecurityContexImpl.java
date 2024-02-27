package telran.java51.security.contex;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import telran.java51.security.model.User;

public class SecurityContexImpl implements SecuriteContext {

	private Map<String, User> context = new ConcurrentHashMap<>();

	@Override
	public User addUserSession(String sessionId, User user) {
		return context.put(sessionId, user);
	}

	@Override
	public User removeUserSession(String sessionId) {
		return context.remove(sessionId);
	}

	@Override
	public User getUserBySessionId(String sessionId) {
		return context.get(sessionId);
	}

}
