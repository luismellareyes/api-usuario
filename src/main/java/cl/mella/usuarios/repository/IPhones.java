package cl.mella.usuarios.repository;

import cl.mella.usuarios.model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Repositorio para phones
 */
public interface IPhones extends JpaRepository<Phones, Long> {

}
