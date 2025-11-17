package de.fhdw.tomneosimon.sicherheitsaspekte.service;

import de.fhdw.tomneosimon.sicherheitsaspekte.mapper.BookMapper;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.repository.BookRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookMapper bookMapper;
  private final BookRepository bookRepository;

  public DtoBook createBook(ModificationBook modificationBook) {
    DbBook bookToCreate = bookMapper.modificationToDb(modificationBook, null);

    if (bookToCreate == null) {
      return null;
    }

    DbBook createdBook = bookRepository.saveAndFlush(bookToCreate);

    return bookMapper.dbToDto(createdBook);
  }

  public DtoBook readBook(UUID uuid) {
    DbBook dbBook = bookRepository.findById(uuid).orElse(null);

    return bookMapper.dbToDto(dbBook);
  }

  public List<DtoBook> readBooks() {
    List<DbBook> dbBooks = bookRepository.findAll();

    return dbBooks.stream().map(bookMapper::dbToDto).toList();
  }

  public DtoBook updateBook(UUID uuid, ModificationBook modificationBook) {
    DbBook bookToUpdate = bookMapper.modificationToDb(modificationBook, uuid);

    if (bookToUpdate == null) {
      return null;
    }

    DbBook updatedBook = bookRepository.saveAndFlush(bookToUpdate);

    return bookMapper.dbToDto(updatedBook);
  }

  public boolean deleteBook(UUID uuid) {
    bookRepository.deleteById(uuid);
    return true;
  }
}
