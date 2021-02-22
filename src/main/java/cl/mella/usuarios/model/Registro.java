package cl.mella.usuarios.model;

import cl.mella.usuarios.Auditoria.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Objeto de retorno para endpoint Registro
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "registro")
public class Registro extends Auditable<String> {
    @Id
    public UUID id;
    @Column(unique = true)
    public String email;
    public String password;
    public String token;
    public Boolean isActive;

}
