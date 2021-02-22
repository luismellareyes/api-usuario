package cl.mella.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Metodo con retorno de mensajes a los request
 */
@Data
@AllArgsConstructor
public class Mensaje {
    private String mensaje;
}
