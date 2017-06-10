package app.cadastro;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by assis on 03/03/17.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> findAll();
}
