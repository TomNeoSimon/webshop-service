package de.fhdw.tomneosimon.sicherheitsaspekte.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "orders", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbOrder {
  @Id
  @UuidGenerator
  private UUID id;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDate createdAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status = OrderStatus.PENDING;

  private String userEmail;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,  orphanRemoval = true)
  private List<DbOrderItem> orderItems;
}