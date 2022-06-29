package br.com.fisrtspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fisrtspring.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
