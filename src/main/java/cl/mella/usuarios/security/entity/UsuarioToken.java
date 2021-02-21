package cl.mella.usuarios.security.entity;

import cl.mella.usuarios.security.enums.RolNombre;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class UsuarioToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String rol;


    public UsuarioToken(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password, @NotNull String rol) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }


}
