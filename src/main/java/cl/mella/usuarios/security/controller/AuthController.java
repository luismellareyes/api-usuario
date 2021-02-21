package cl.mella.usuarios.security.controller;

import cl.mella.usuarios.Service.ValidarUsuario;
import cl.mella.usuarios.dto.Mensaje;
import cl.mella.usuarios.exception.ErrorRequest;
import cl.mella.usuarios.security.dto.JwtDto;
import cl.mella.usuarios.security.dto.LoginUsuario;
import cl.mella.usuarios.security.dto.NuevoUsuario;
import cl.mella.usuarios.security.entity.UsuarioToken;
import cl.mella.usuarios.security.jwt.JwtProvider;
import cl.mella.usuarios.security.service.GeneratorToken;
import cl.mella.usuarios.security.service.UsuarioService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Log4j
@Api(value = "Servicios de Autenticacion")
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GeneratorToken generatorToken;

    @Autowired
    ValidarUsuario validarUsuario;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        log.info("REQUEST NEW USER: " + nuevoUsuario);
        if (bindingResult.hasErrors())
            return new ResponseEntity(new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), "campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), "ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), "ese email ya existe"), HttpStatus.BAD_REQUEST);
        if (!validarUsuario.validPasswordRegex(nuevoUsuario))
            return new ResponseEntity(new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Contraseña no tiene formato requerido, Una Mayuscula, letras Minusculas y dos numeros"), HttpStatus.BAD_REQUEST);

        generatorToken.newUser(nuevoUsuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }


    @PostMapping("/login")

    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), "El usuario y/o Password esta mal ingresados"), HttpStatus.BAD_REQUEST);
        JwtDto jwtDto = generatorToken.generator(loginUsuario);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
