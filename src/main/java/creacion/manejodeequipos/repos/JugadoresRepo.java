package creacion.manejodeequipos.repos;

import creacion.manejodeequipos.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadoresRepo extends JpaRepository<Jugador, Integer> {

  void deleteJugadorById(Integer id);

  Jugador findJugadorById(Integer id); // TODO excepcion si no encuentra el id
}
