package cl.mella.usuarios.model;

import cl.mella.usuarios.Auditoria.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Objeto de retorno para endpoint Registro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "registro")
public class Registro  extends Auditable<String> {
    @Id
    public UUID id;
    @Column(unique = true)
    public String email;
    public String password;
    //public Date created;
    //public Date modified;
    //public Date last_login;
    public String token;
    public Boolean isActive;

}
