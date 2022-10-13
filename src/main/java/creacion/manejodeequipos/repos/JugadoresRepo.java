package creacion.manejodeequipos.repos;

import creacion.manejodeequipos.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JugadoresRepo extends JpaRepository<Jugador, Integer> {

  List<Jugador> findJugadoressByNumero(Integer numero);

  List<Jugador> findJugadoresByNombre(String nombre);

  Optional<Jugador> findJugadorById(Integer id);
}
