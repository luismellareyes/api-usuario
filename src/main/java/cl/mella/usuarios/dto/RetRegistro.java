package cl.mella.usuarios.dto;

import cl.mella.usuarios.model.Registro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@ToString
public class RetRegistro {

    UUID id;
    Date created;
    Date modified;
    Date last_login;
    String token;
    Boolean isActive;


    public RetRegistro(Registro registro) {
        this.id=registro.id;
        this.created=registro.created;
        this.modified=registro.modified;
        this.last_login=registro.last_login;
        this.token=registro.token;
        this.isActive=registro.isActive;
    }
}
