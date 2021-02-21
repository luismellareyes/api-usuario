package cl.mella.usuarios.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRequest {
    String codigo;
    String mensaje;
}
