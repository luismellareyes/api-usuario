package cl.mella.usuarios;

import cl.mella.usuarios.dto.Usuario;
import cl.mella.usuarios.model.Registro;
import cl.mella.usuarios.dto.RetRegistro;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest
@Log4j
class ApiRestUsuarioTokenApplicationTests {


    @Test
    void contextLoads() {
        String regex = "^(?=(?:.*\\d){2})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){1})([A-Za-z\\d])(?=\\S+$)";
        //Pattern pat = Pattern.compile(regex);
        Pattern pat = Pattern.compile(regex);
        String input = "Hola 11";
        //Matcher mat = pat.matcher(input);
        Matcher mat = pat.matcher(input);
        if (mat.find()) {
            log.info("regex encontrada");
            //log.info("Sujeto activo :"+mat.group(1));
            //log.info("Sujeto pasivo :"+mat.group(2));
        } else {
            log.info("regex NO encontrada");
        }
        //log.info("hola");
    }

    @Test
    public void grego() {

        log.info(GregorianCalendar.getInstance().getTime());
    }


    @Test
    public void extraerEmail() {
        UUID uuid = UUID.randomUUID();
        Registro registro = new Registro(uuid,
                "Lmella@gmail.com",
                "PassLuismella",
                "12345ABCDE",
                true);


        RetRegistro ret = new RetRegistro(registro);

        log.info(ret);
    }




}
