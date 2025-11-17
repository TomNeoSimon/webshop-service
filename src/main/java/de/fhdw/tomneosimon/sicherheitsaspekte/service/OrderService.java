package de.fhdw.tomneosimon.sicherheitsaspekte.service;

import de.fhdw.tomneosimon.sicherheitsaspekte.mapper.OrderMapper;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.OrderStatus;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderMapper orderMapper;
  private final OrderRepository orderRepository;

  public DtoOrder createOrder(ModificationOrder modificationOrder, String email) {
    DbOrder orderToCreate = orderMapper.modificationToDb(modificationOrder, null, email);

    if (orderToCreate == null) {
      return null;
    }

    DbOrder createdOrder = orderRepository.saveAndFlush(orderToCreate);

    return orderMapper.dbToDto(createdOrder);
  }

  public List<DtoOrder> readOrdersFromUser(String email) {
    List<DbOrder> dbOrders = orderRepository.findAllByUserEmail(email);

    return dbOrders.stream().map(orderMapper::dbToDto).toList();
  }

  public List<DtoOrder> readOrders() {
    List<DbOrder> dbOrders = orderRepository.findAll();

    return dbOrders.stream().map(orderMapper::dbToDto).toList();
  }

  public DtoOrder readOrder(UUID uuid) {
    DbOrder dbOrder = orderRepository.findById(uuid).orElse(null);

    return orderMapper.dbToDto(dbOrder);
  }

  public DtoOrder updateOrder(UUID uuid, OrderStatus orderStatus) {
    DbOrder orderToUpdate = orderRepository.findById(uuid).orElse(null);

    if (orderToUpdate == null) {
      return null;
    }

    orderToUpdate.setStatus(orderStatus);

    DbOrder updatedOrder = orderRepository.saveAndFlush(orderToUpdate);

    return orderMapper.dbToDto(updatedOrder);
  }

  public boolean deleteOrder(UUID uuid) {
    orderRepository.deleteById(uuid);
    return true;
  }
}
