package telran.java51.security.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java51.account.dao.AccountRepository;
import telran.java51.account.model.Account;


@Component
@RequiredArgsConstructor
@Order(20)
public class AdminManagingRolesFilter implements Filter {

	final AccountRepository accountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndPoint(request.getMethod(), request.getServletPath())) {
			Account userAccount = accountRepository
					.findById(request.getUserPrincipal().getName()).get();
			if (!userAccount.getRoles().contains("ADMINISTRATOR")) {
				response.sendError(403, "Permission denied");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoint(String method, String path) {
		return path.matches("/account/user/\\w+/role/\\w+");
	}

}