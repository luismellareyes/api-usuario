package cl.mella.usuarios.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
/*
Objeto de listado de Phones en objeto UsuarioToken
 */

@Data
@Entity
@Table(name = "Phones")
public class Phones {
    @Id
    @GeneratedValue
    Long phoneId;
    UUID idRegistro;
    String number;
    String cityCode;
    String contryCode;
}
