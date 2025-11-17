package de.fhdw.tomneosimon.sicherheitsaspekte.repository;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbBook;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<DbBook, UUID> {}