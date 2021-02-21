package cl.mella.usuarios.Auditoria;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditoriaAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		String name = "s";
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			name = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return Optional.ofNullable(name);
	}

}
