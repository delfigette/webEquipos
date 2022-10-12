package creacion.manejodeequipos.services;

import Exceptions.JugadorNotFoundExeption;
import Exceptions.NombreAlreadyExistsExeption;
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
    if(buscarJugadoresPorNombre(jugador.getNombre()).toArray().length == 0){return repo.save(jugador);}
    else{
      throw new NombreAlreadyExistsExeption("Ya existe un jugador con el nombre " + jugador.getNombre() );
    }
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

  public Jugador buscarJugador(Integer id) throws Throwable {
    return repo.findJugadorById(id)
        .orElseThrow(() -> new JugadorNotFoundExeption("User by id " + id + " was not found"));
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
