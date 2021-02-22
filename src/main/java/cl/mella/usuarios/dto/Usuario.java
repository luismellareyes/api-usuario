package cl.mella.usuarios.dto;


import cl.mella.usuarios.model.Phones;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.validation.annotation.Validated;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Objeto con campos de usuario para registro
 */
@Data
@Log4j
public class Usuario {

    @NotBlank(message = "Campo email es mandatorio")
    @Email
    private String email;
    @NotBlank(message = "Campo password mandatorio")
    private String password;
    private Set<Phones> phones = new HashSet<>();


}
