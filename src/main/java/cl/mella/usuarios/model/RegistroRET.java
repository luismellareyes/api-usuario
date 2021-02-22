package cl.mella.usuarios.model;

import cl.mella.usuarios.Auditoria.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * Objeto de retorno para endpoint Registro
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
