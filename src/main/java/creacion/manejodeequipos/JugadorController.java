package creacion.manejodeequipos;

import creacion.manejodeequipos.domain.Jugador;
import creacion.manejodeequipos.services.JugadoresServ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugador")
public class JugadorController {
  private final JugadoresServ jugadoresServ;

  public JugadorController(JugadoresServ jugadoresServ) {
    this.jugadoresServ = jugadoresServ;
  }

  @PostMapping("/add")
  public ResponseEntity<Jugador> addJugador(@RequestBody Jugador jugador){
    Jugador nuevoJugador = jugadoresServ.agregarJugador(jugador);
    return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);

  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Jugador> getJugadorById(@PathVariable("id") Integer id){
    Jugador jugador = jugadoresServ.buscarJugador(id);
    return new ResponseEntity<>(jugador, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<Jugador> updateJugador(@RequestBody Jugador jugador){
    Jugador updateableJugador = jugadoresServ.actualizarJugador(jugador);
    return new ResponseEntity<>(updateableJugador, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Jugador>> getAllJugadores(){
    List<Jugador> jugadores = jugadoresServ.EquipoCompleto();
    return new ResponseEntity<>(jugadores, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteJugador(@PathVariable("id") Integer id){
    jugadoresServ.eliminarJugador(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
