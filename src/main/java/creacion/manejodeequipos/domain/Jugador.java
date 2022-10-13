package creacion.manejodeequipos.domain;
import creacion.manejodeequipos.helpers.PersistentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.lang.annotation.Repeatable;

@Entity
public class Jugador extends PersistentEntity implements Serializable {
  @Column(unique = true)
  private String nombre;
  @Column
  private int numero;
  @Column
  private String posicion;

  public Jugador() {}

  public Jugador(String nombre, int num, String pos) {
    this.nombre = nombre;
    this.numero = num;
    this.posicion = pos;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer num) {
      this.numero = num;
  }

  public String getPosicion() {
    return posicion;
  }

  public void setPosicion(String pos) {
    this.posicion = pos;
  }

}



