package creacion.manejodeequipos.services;

import creacion.manejodeequipos.domain.Jugador;
import creacion.manejodeequipos.repos.JugadoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Jugador actualizarJugador(Jugador jugador){
    return repo.save(jugador);
  }

  public Jugador buscarJugador(Integer id){
    return repo.findJugadorById(id);
  }

  public void eliminarJugador(Integer id){
    repo.deleteJugadorById(id);
  }

}
