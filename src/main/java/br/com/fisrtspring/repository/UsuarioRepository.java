package br.com.fisrtspring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fisrtspring.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where lower(trim(u.nome)) like %?1%")
    List<Usuario> findAllName(String name);
}
