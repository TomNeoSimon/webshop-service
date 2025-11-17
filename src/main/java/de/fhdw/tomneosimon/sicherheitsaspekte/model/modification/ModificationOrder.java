package de.fhdw.tomneosimon.sicherheitsaspekte.model.modification;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModificationOrder {
  private List<ModificationOrderItem> modificationOrderItems;
}