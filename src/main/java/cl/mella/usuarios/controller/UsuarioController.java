package cl.mella.usuarios.controller;

import cl.mella.usuarios.Service.ValidarUsuario;
import cl.mella.usuarios.exception.ErrorRequest;
import cl.mella.usuarios.model.Phones;
import cl.mella.usuarios.model.Registro;
import cl.mella.usuarios.dto.RetRegistro;
import cl.mella.usuarios.dto.Usuario;
import cl.mella.usuarios.repository.IPhones;
import cl.mella.usuarios.repository.IQuery;
import cl.mella.usuarios.repository.IRegistro;
import cl.mella.usuarios.security.dto.LoginUsuario;
import cl.mella.usuarios.security.dto.NuevoUsuario;
import cl.mella.usuarios.security.service.GeneratorToken;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuario")
@Log4j
@Api(value = "Servicios de registro de usuarios")
public class UsuarioController {

    @Autowired
    private IRegistro iRegistro;

    @Autowired
    private IPhones iPhones;

    @Autowired
    private GeneratorToken generatorToken;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ValidarUsuario validarUsuario;

    @Autowired
    IQuery iQuery;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Validated @RequestBody Usuario usuario) {
        log.info("UsuarioToken: " + usuario);
        if (!validarUsuario.validPasswordRegex(usuario))
            return new ResponseEntity(new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Contraseña no tiene formato requerido, Una Mayuscula, letras Minusculas y dos numeros"), HttpStatus.BAD_REQUEST);

        UUID uuid = UUID.randomUUID();
        NuevoUsuario nuevoUsuario = new NuevoUsuario(usuario.getEmail().split("@")[0],
                usuario.getEmail().split("@")[0],
                usuario.getEmail(),
                usuario.getPassword(),
                "user");
        LoginUsuario loginUsuario = new LoginUsuario(
                usuario.getEmail().split("@")[0],
                usuario.getPassword());
        log.info(nuevoUsuario.toString());
        generatorToken.newUser(nuevoUsuario);
        Registro registro = new Registro(uuid,
                usuario.getEmail(),
                passwordEncoder.encode(usuario.getPassword()),
                generatorToken.generator(loginUsuario).getToken(),
                true);
        log.info("Registro: " + registro.toString());
        iRegistro.save(registro);
        Set<Phones> phones = usuario.getPhones();
        for (Phones p : usuario.getPhones()) {
            p.setIdRegistro(uuid);
        }
        iPhones.saveAll(phones);

        //log.info(iQuery.findRegistro(usuario.getEmail()));


        return new ResponseEntity(iQuery.findRegistro(usuario.getEmail()), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> getListaUsuario() {

        List<String> lista = new ArrayList<>();

        for (Registro r : iRegistro.findByIsActive(true)) {
            lista.add(r.email);
        }
        return new ResponseEntity(lista, HttpStatus.OK);
    }


}

