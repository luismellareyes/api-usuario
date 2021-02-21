package cl.mella.usuarios.repository;

import cl.mella.usuarios.model.Registro;
import cl.mella.usuarios.model.RegistroRET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRegistro extends JpaRepository<Registro, String> {

    List<Registro> findByIsActive(Boolean isActive);

    Optional<String> findByEmail(String email);

    //@Query(value = "Select * From registro where email = ?1", nativeQuery = true)
    //RegistroRET findRegistro(String email);


}
