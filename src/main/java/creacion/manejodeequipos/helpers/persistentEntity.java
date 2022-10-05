package creacion.manejodeequipos.helpers;

import javax.persistence.*;

@MappedSuperclass
public class persistentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  public int id;
}


