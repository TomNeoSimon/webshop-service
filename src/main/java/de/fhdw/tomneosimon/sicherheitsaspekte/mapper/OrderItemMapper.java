package de.fhdw.tomneosimon.sicherheitsaspekte.mapper;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbOrderItem;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoOrderItem;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationOrderItem;
import de.fhdw.tomneosimon.sicherheitsaspekte.repository.BookRepository;
import de.fhdw.tomneosimon.sicherheitsaspekte.repository.OrderItemRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemMapper {
  private final BookRepository bookRepository;
  private final BookMapper bookMapper;
  private final OrderItemRepository orderItemRepository;

  public DtoOrderItem dbToDto(DbOrderItem dbOrderItem) {
    DtoOrderItem dtoOrderItem = null;

    if(dbOrderItem != null) {
      dtoOrderItem = new DtoOrderItem();
      dtoOrderItem.setId(dbOrderItem.getId());
      dtoOrderItem.setQuantity(dbOrderItem.getQuantity());
      dtoOrderItem.setBook(bookMapper.dbToDto(dbOrderItem.getBook()));
    }

    return dtoOrderItem;
  }

  public DbOrderItem modificationToDb(ModificationOrderItem modificationOrderItem, UUID uuid) {
    DbOrderItem dbOrderItem;

    if (uuid == null) {
      dbOrderItem = new DbOrderItem();
    } else {
      dbOrderItem = orderItemRepository.findById(uuid).orElse(null);
    }

    if(dbOrderItem == null) {
      return null;
    }

    dbOrderItem.setQuantity(modificationOrderItem.getQuantity());
    DbBook book = bookRepository.findById(modificationOrderItem.getBookUUID()).orElse(null);
    dbOrderItem.setBook(book);

    return dbOrderItem;
  }
}