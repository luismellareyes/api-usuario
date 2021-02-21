package cl.mella.usuarios.repository;

import cl.mella.usuarios.model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhones extends JpaRepository<Phones, Long> {

}
