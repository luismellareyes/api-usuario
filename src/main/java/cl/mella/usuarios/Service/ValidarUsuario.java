package cl.mella.usuarios.Service;

import cl.mella.usuarios.dto.Usuario;
import cl.mella.usuarios.security.dto.NuevoUsuario;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j
public class ValidarUsuario {

    public Boolean validPasswordRegex(Usuario usuario) {
        String regex = "^(?=(?:.*\\d){2})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){1})([A-Za-z\\d])(?=\\S+$)";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(usuario.getPassword());
        if (!mat.find()) {
            log.info("Password no contiene formato adecuado, una Mayuscula, letras minusculas  y dos decimales");
            return false;
        }
        log.info("Password con formato solicitado");
        return true;
    }

    public Boolean validPasswordRegex(NuevoUsuario usuario) {
        String regex = "^(?=(?:.*\\d){2})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){1})([A-Za-z\\d])(?=\\S+$)";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(usuario.getPassword());
        if (!mat.find()) {
            log.info("Password no contiene formato adecuado, una Mayuscula, letras minusculas  y dos decimales");
            return false;
        }
        log.info("Password con formato solicitado");
        return true;
    }

}
