package cl.mella.usuarios.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase con retorno de mensajes de error en los request
 */
@Data
@AllArgsConstructor
public class ErrorRequest {
    String codigo;
    String mensaje;
}
