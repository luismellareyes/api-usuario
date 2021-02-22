package cl.mella.usuarios.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * Objeto de retorno del endpoint Registro
 */
@Data
@Entity
@ToString

public class RegistroRET {

    @Id
    public UUID id;
    public Date created;
    public Date modified;
    public Date last_login;
    public String token;
    public Boolean isActive;

}
