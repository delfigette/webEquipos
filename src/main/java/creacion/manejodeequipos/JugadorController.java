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

  @GetMapping("/numeros")
  public ResponseEntity<List<Integer>> getAllNumeros(){
    List<Integer> numeros = jugadoresServ.numeroCamisetas();
    return new ResponseEntity<>(numeros, HttpStatus.OK);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteJugador(@PathVariable("id") Integer id){
    jugadoresServ.eliminarJugador(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  @GetMapping("/filtrar/{numero}")
  public ResponseEntity<List<Jugador>> getJugadorByNumero(@PathVariable("numero") Integer numero){
    List<Jugador> jugadores = jugadoresServ.buscarJugadoresPorNumero(numero);
    return new ResponseEntity<>(jugadores, HttpStatus.OK);
  }

  @GetMapping("/buscar/{nombre}")
  public ResponseEntity<List<Jugador>> getJugadorByNombre(@PathVariable("nombre") String nombre){
    List<Jugador> jugadores = jugadoresServ.buscarJugadoresPorNombre(nombre);
    return new ResponseEntity<>(jugadores, HttpStatus.OK);
  }



}
