package creacion.manejodeequipos.repos;

import creacion.manejodeequipos.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadoresRepo extends JpaRepository<Jugador, Integer> {

  void deleteJugadorById(Integer id);

  List<Jugador> findJugadoressByNumero(Integer numero);

  List<Jugador> findJugadoresByNombre(String nombre);

  Jugador findJugadorById(Integer id); // TODO excepcion si no encuentra el id
}
