package de.fhdw.tomneosimon.sicherheitsaspekte.model.dto;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.OrderStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrder {
  private UUID id;
  private LocalDate createdAt;
  private OrderStatus orderStatus;
  private String userEmail;
  private List<DtoOrderItem> items;
}