package de.fhdw.tomneosimon.sicherheitsaspekte.model.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "order_items", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbOrderItem {
  @Id
  @UuidGenerator
  private UUID id;

  private int quantity;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private DbOrder order;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private DbBook book;
}