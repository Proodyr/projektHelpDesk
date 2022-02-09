package pl.w65154.helpdesk.repository;

import pl.w65154.helpdesk.entity.Ticket;
import pl.w65154.helpdesk.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findAllByOrderByCreationDateDesc(Pageable pageable);
    Page<Ticket> findAllByUserOrderByLastStatusChangeDateDesc(User user, Pageable pageable);
    Long countAllByStatusEquals(Ticket.Status status);
}
