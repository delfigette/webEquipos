package creacion.manejodeequipos;

import Exceptions.JugadorNotFoundExeption;
import Exceptions.NombreAlreadyExistsExeption;
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
  public ResponseEntity<?> addJugador(@RequestBody Jugador jugador){
    if(jugadoresServ.buscarJugadoresPorNombre(jugador.getNombre()).toArray().length == 0
        && jugador.getNumero() <= 35 && jugador.getNumero() >= 1  ){
      Jugador nuevoJugador = jugadoresServ.agregarJugador(jugador);
      return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }
    else{
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body("Ya existe un jugador con el nombre " + jugador.getNumero());
    }
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Jugador> getJugadorById(@PathVariable("id") Integer id) throws Throwable {
    Jugador jugador = jugadoresServ.buscarJugador(id);
    return new ResponseEntity<>(jugador, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateJugador(@RequestBody Jugador jugador) throws Throwable {
    try{
      jugadoresServ.buscarJugador(jugador.id);
    }
    catch (JugadorNotFoundExeption e){
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body("No existe un jugador con ese id " + jugador.getNumero());
    }
    /*jugadoresServ.buscarJugadoresPorNombre(jugador.getNombre()).toArray().length < 1*/
    if(jugadoresServ.buscarJugador(jugador.id).getNombre().contains(jugador.getNombre()) ||
        jugadoresServ.buscarJugadoresPorNombre(jugador.getNombre()).toArray().length == 0){ // si el nombre es el mismo o si no hay ninguno así
      Jugador updateableJugador = jugadoresServ.actualizarJugador(jugador);
      return new ResponseEntity<>(updateableJugador, HttpStatus.OK);
    }
    else{
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body("Ya existe un jugador con el nombre que se quiere poner");
    }
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


  @GetMapping("/filtrar/{numero}")
  public ResponseEntity<?> getJugadorByNumero(@PathVariable("numero") Integer numero){
    if(numero >= 1 && numero < 35){
      List<Jugador> jugadores = jugadoresServ.buscarJugadoresPorNumero(numero);
      return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }
    else{
      return ResponseEntity
          .status(HttpStatus.FORBIDDEN)
          .body("número de camiseta no válido");
    }
  }

  @GetMapping("/buscar/{nombre}")
  public ResponseEntity<List<Jugador>> getJugadorByNombre(@PathVariable("nombre") String nombre){
    List<Jugador> jugadores = jugadoresServ.buscarJugadoresPorNombre(nombre);
    return new ResponseEntity<>(jugadores, HttpStatus.OK);
  }



}
