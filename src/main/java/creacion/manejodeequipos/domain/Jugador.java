package creacion.manejodeequipos.domain;
import creacion.manejodeequipos.helpers.persistentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Jugador extends persistentEntity implements Serializable {
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

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
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



