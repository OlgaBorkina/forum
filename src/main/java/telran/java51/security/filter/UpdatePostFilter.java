package telran.java51.security.filter;

import java.io.IOException;
import java.security.Principal;

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
import telran.java51.post.dao.PostRepository;
import telran.java51.post.model.Post;


@Component
@RequiredArgsConstructor
@Order(50)
public class UpdatePostFilter implements Filter {

	final PostRepository postRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		if (checkEndPoint(request.getMethod(), request.getServletPath())) {
			Principal principal = request.getUserPrincipal();
			String[] arr = request.getServletPath().split("/");
			String idPost = arr[arr.length - 1];
			

			Post post = postRepository.findById(idPost).orElseThrow(RuntimeException::new);
					if (!principal.getName().equals(post.getAuthor()))	{
				response.sendError(403);
				return;
			}

		}
		chain.doFilter(request, response);

	}

	private boolean checkEndPoint(String method, String path) {
		return HttpMethod.PUT.matches(method) && path.matches("/forum/post/\\w+/?");
	}

}
