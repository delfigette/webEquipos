package creacion.manejodeequipos.services;

import creacion.manejodeequipos.domain.Jugador;
import creacion.manejodeequipos.repos.JugadoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadoresServ {
  private final JugadoresRepo repo;

  @Autowired
  public JugadoresServ(JugadoresRepo jugadoresRepo) {
    this.repo = jugadoresRepo;
  }

  public Jugador agregarJugador(Jugador jugador){
    return repo.save(jugador);
  }

  public List<Jugador> EquipoCompleto(){
    return repo.findAll();
  }

  public List<Integer> numeroCamisetas(){
    return repo.findAll().stream().map(Jugador::getNumero).distinct().collect(Collectors.toList());
  }

  public Jugador actualizarJugador(Jugador jugador){
    return repo.save(jugador);
  }

  public Jugador buscarJugador(Integer id){
    return repo.findJugadorById(id);
  }

  public List<Jugador> buscarJugadoresPorNumero(Integer numero){
    return repo.findJugadoressByNumero(numero);
  }

  public List<Jugador> buscarJugadoresPorNombre(String nombre){
    return repo.findJugadoresByNombre(nombre);
  }

  public void eliminarJugador(Integer id){
    repo.deleteJugadorById(id);
  }

}
