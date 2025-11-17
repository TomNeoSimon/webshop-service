package de.fhdw.tomneosimon.sicherheitsaspekte.mapper;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.repository.BookRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMapper {
  private final BookRepository bookRepository;

  public DtoBook dbToDto(DbBook dbBook) {
    DtoBook dtoBook = null;

    if(dbBook != null) {
      dtoBook = new DtoBook();
      dtoBook.setId(dbBook.getId());
      dtoBook.setTitle(dbBook.getTitle());
      dtoBook.setIsbn(dbBook.getIsbn());
      dtoBook.setPublisher(dbBook.getPublisher());
      dtoBook.setPublicationDate(dbBook.getPublicationDate());
      dtoBook.setPrice(dbBook.getPrice());
      dtoBook.setStock(dbBook.getStock());
      dtoBook.setAuthor(dbBook.getAuthor());
    }

    return dtoBook;
  }

  public DbBook modificationToDb(ModificationBook modificationBook, UUID uuid) {
    DbBook dbBook;
    if (uuid == null) {
      dbBook = new DbBook();
    } else {
      dbBook = bookRepository.findById(uuid).orElse(null);
    }

    if(dbBook == null) {
      return null;
    }

    dbBook.setTitle(modificationBook.getTitle());
    dbBook.setIsbn(modificationBook.getIsbn());
    dbBook.setPublisher(modificationBook.getPublisher());
    dbBook.setPublicationDate(modificationBook.getPublicationDate());
    dbBook.setPrice(modificationBook.getPrice());
    dbBook.setStock(modificationBook.getStock());
    dbBook.setAuthor(modificationBook.getAuthor());

    return dbBook;
  }
}