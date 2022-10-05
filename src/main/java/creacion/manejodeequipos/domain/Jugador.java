package creacion.manejodeequipos.domain;
import creacion.manejodeequipos.helpers.PersistentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Jugador extends PersistentEntity implements Serializable {
  @Column
  private String nombre;
  private int numero;

  public Jugador() {}

  public Jugador(String nombre, int num) {
    this.nombre = nombre;
    this.numero = num;
  }

  public int getId() {
    return this.id;
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

  @Override
  public String toString() {
    return "Jugador{" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        ", numero='" + numero + '\'' +
        '}';
  }
}



