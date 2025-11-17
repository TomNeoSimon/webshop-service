package de.fhdw.tomneosimon.sicherheitsaspekte.mapper;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbOrderItem;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {
  private final OrderRepository orderRepository;
  private final OrderItemMapper orderItemMapper;

  public DtoOrder dbToDto(DbOrder dbOrder) {
    DtoOrder dtoOrder = null;

    if(dbOrder != null) {
      dtoOrder = new DtoOrder();
      dtoOrder.setId(dbOrder.getId());
      dtoOrder.setCreatedAt(dbOrder.getCreatedAt());
      dtoOrder.setOrderStatus(dbOrder.getStatus());
      dtoOrder.setUserEmail(dbOrder.getUserEmail());
      dtoOrder.setItems(dbOrder.getOrderItems()
          .stream()
          .map(orderItemMapper::dbToDto)
          .toList()
      );
    }

    return dtoOrder;
  }

  public DbOrder modificationToDb(ModificationOrder modificationOrder, UUID uuid, String email) {
    DbOrder dbOrder;
    if (uuid == null) {
      dbOrder = new DbOrder();
      dbOrder.setUserEmail(email);
    } else {
      dbOrder = orderRepository.findById(uuid).orElse(null);
    }

    if(dbOrder == null) {
      return null;
    }

    List<DbOrderItem> items = modificationOrder.getModificationOrderItems()
        .stream()
        .map(modificationOrderItem -> {
          DbOrderItem dbOrderItem = orderItemMapper.modificationToDb(modificationOrderItem, null);
          dbOrderItem.setOrder(dbOrder);
          return dbOrderItem;
        })
        .toList();
    dbOrder.setOrderItems(items);

    return dbOrder;
  }
}