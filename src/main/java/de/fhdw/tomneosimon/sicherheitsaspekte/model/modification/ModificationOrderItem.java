package de.fhdw.tomneosimon.sicherheitsaspekte.model.modification;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModificationOrderItem {
  private int quantity;
  private UUID bookUUID;
}