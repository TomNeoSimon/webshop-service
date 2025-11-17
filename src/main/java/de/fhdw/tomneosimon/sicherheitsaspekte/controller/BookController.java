package de.fhdw.tomneosimon.sicherheitsaspekte.controller;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.dto.DtoBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.model.modification.ModificationBook;
import de.fhdw.tomneosimon.sicherheitsaspekte.service.BookService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<DtoBook> createBook(@RequestBody ModificationBook modificationBook) {
    try {
      DtoBook createdBook = bookService.createBook(modificationBook);

      if (createdBook == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(createdBook);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<DtoBook>> readBooks() {
    try {
      List<DtoBook> dtoBooks = bookService.readBooks();

      if (dtoBooks.isEmpty()) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok(dtoBooks);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(value = "/{uuid}", produces = "application/json")
  public ResponseEntity<DtoBook> readBook(@PathVariable UUID uuid) {
    try {
      DtoBook dtoBook = bookService.readBook(uuid);

      if (dtoBook == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(dtoBook);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping(value = "/{uuid}", produces = "application/json", consumes = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<DtoBook> updateBook(@PathVariable UUID uuid, @RequestBody ModificationBook modificationBook) {
    try {
      DtoBook dtoBook = bookService.updateBook(uuid, modificationBook);

      if (dtoBook == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(dtoBook);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping(value = "/{uuid}", produces = "application/json", consumes = "application/json")
  @PreAuthorize("hasAuthority('Admin')")
  public ResponseEntity<Boolean> deleteBook(@PathVariable UUID uuid) {
    try {
      return ResponseEntity.ok(bookService.deleteBook(uuid));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }
}