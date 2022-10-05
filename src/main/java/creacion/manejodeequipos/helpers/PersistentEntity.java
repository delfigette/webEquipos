package creacion.manejodeequipos.helpers;

import javax.persistence.*;

@MappedSuperclass
public class PersistentEntity {
  @Id
  @GeneratedValue
  @Column(nullable = false)
  public Integer id;
}


