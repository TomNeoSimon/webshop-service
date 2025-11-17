package de.fhdw.tomneosimon.sicherheitsaspekte.repository;

import de.fhdw.tomneosimon.sicherheitsaspekte.model.db.DbOrderItem;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<DbOrderItem, UUID> {}