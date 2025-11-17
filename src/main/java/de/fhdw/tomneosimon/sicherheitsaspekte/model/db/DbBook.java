package de.fhdw.tomneosimon.sicherheitsaspekte.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "books", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbBook {
  @Id
  @UuidGenerator
  private UUID id;

  private String title;
  private String isbn;
  private String publisher;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate publicationDate;
  private double price;
  private int stock;
  private String author;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDate createdAt;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DbOrderItem> orderItems;
}
