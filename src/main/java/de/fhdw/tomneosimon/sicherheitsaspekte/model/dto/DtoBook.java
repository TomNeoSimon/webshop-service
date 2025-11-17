package de.fhdw.tomneosimon.sicherheitsaspekte.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBook {
  private UUID id;
  private String title;
  private String isbn;
  private String publisher;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate publicationDate;
  private double price;
  private int stock;
  private String author;
}
