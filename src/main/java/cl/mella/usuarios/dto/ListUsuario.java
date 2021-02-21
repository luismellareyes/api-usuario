package cl.mella.usuarios.dto;

import cl.mella.usuarios.model.Registro;
import lombok.Data;

import java.util.List;

@Data
public class ListUsuario {

    String email;

    public ListUsuario(Registro registro) {

        this.email = registro.email;

    }



}
