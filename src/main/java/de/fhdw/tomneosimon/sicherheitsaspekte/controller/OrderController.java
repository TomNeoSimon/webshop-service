package de.fhdw.tomneosimon.sicherheitsaspekte.controller;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.OrderStatus;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationOrder;
import de.fhdw.tomneosimon.sicherheitsaspekte.service.OrderService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
  @PreAuthorize("hasAuthority('User')")
  public ResponseEntity<DtoOrder> createOrder(@RequestBody ModificationOrder modificationOrder) {
    try {
      DtoOrder createdOrder = orderService.createOrder(modificationOrder, getEmail());

      if (createdOrder == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(createdOrder);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(value = "/user", produces = "application/json")
  @PreAuthorize("hasAuthority('User')")
  public ResponseEntity<List<DtoOrder>> readOrdersFromUser() {
    try {
      List<DtoOrder> dtoOrders = orderService.readOrdersFromUser(getEmail());

      if (dtoOrders.isEmpty()) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok(dtoOrders);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(produces = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<List<DtoOrder>> readOrders() {
    try {
      List<DtoOrder> dtoOrders = orderService.readOrders();

      if (dtoOrders.isEmpty()) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok(dtoOrders);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(value = "/{uuid}", produces = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<DtoOrder> readOrder(@PathVariable UUID uuid) {
    try {
      DtoOrder dtoOrder = orderService.readOrder(uuid);

      if (dtoOrder == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(dtoOrder);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping(value = "/{uuid}", produces = "application/json", consumes = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<DtoOrder> updateOrder(@PathVariable UUID uuid, @RequestBody OrderStatus orderStatus) {
    try {
      DtoOrder dtoOrder = orderService.updateOrder(uuid, orderStatus);

      if (dtoOrder == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(dtoOrder);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping(value = "/{uuid}", produces = "application/json", consumes = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<Boolean> deleteOrder(@PathVariable UUID uuid) {
    try {
      return ResponseEntity.ok(orderService.deleteOrder(uuid));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  private String getEmail() {
    JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    Jwt jwt = (Jwt) authenticationToken.getCredentials();
    return (String) jwt.getClaims().get("https://permissions.turibabookshop.lv/email");
  }
}