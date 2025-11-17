package de.fhdw.tomneosimon.sicherheitsaspekte.model.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItem {
  private UUID id;
  private int quantity;
  private DtoBook book;
}