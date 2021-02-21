package cl.mella.usuarios.repository;

import cl.mella.usuarios.model.Registro;
import cl.mella.usuarios.model.RegistroRET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IQuery extends JpaRepository<RegistroRET, String> {

    @Query(value = "Select * From registro where email = ?1", nativeQuery = true)
    RegistroRET findRegistro(String email);
}
