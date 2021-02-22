package cl.mella.usuarios.security.service;

import cl.mella.usuarios.security.dto.JwtDto;
import cl.mella.usuarios.security.dto.LoginUsuario;
import cl.mella.usuarios.security.dto.NuevoUsuario;
import cl.mella.usuarios.security.entity.UsuarioToken;
import cl.mella.usuarios.security.jwt.JwtProvider;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class GeneratorToken {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;


    @Autowired
    JwtProvider jwtProvider;

    public JwtDto generator(LoginUsuario loginUsuario) {
        log.info("Login: " + loginUsuario);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        log.info("auth: " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        log.info(jwtDto.toString());
        return jwtDto;
    }

    public void newUser(NuevoUsuario nuevoUsuario) {
        try {
            log.info("New " + nuevoUsuario);
            UsuarioToken usuarioToken =
                    new UsuarioToken(nuevoUsuario.getNombre(),
                            nuevoUsuario.getNombreUsuario(),
                            nuevoUsuario.getEmail(),
                            passwordEncoder.encode(nuevoUsuario.getPassword()),
                            (nuevoUsuario.getRol().contains("admin") ? "admin" : "user"));
            usuarioService.save(usuarioToken);
        } catch (Exception e) {
            log.info("Error: " + e);

        }
    }
}
