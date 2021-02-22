package cl.mella.usuarios.repository;

import cl.mella.usuarios.model.Registro;
import cl.mella.usuarios.model.RegistroRET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
Interface para manejo de Query
 */
@Repository
public interface IQuery extends JpaRepository<RegistroRET, String> {

    @Query(value = "Select * From registro where email = ?1", nativeQuery = true)
    RegistroRET findRegistro(String email);

    @Query(value = "SELECT r.email FROM registro r WHERE r.is_active = true", nativeQuery=true)
    List<String> findListIsActive();
}
