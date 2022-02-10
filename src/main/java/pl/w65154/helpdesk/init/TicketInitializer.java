package pl.w65154.helpdesk.init;

import pl.w65154.helpdesk.entity.Message;
import pl.w65154.helpdesk.entity.Ticket;
import pl.w65154.helpdesk.entity.User;
import pl.w65154.helpdesk.repository.MessageRepository;
import pl.w65154.helpdesk.repository.TicketRepository;
import pl.w65154.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TicketInitializer implements DataLoader {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void load() {
        if (ticketRepository.count() == 0) {
            User user = userRepository.findByUsername("user");
            LocalDateTime now = LocalDateTime.now();

            Ticket ticket = new Ticket();
            ticket.setCreationDate(now);
            ticket.setStatus(Ticket.Status.NEW);
            ticket.setUser(user);
            ticket.setLastStatusChangeBy(user);
            ticket.setLastStatusChangeDate(now);
            ticket.setTitle("Internet w tej firmie działa beznadziejnie!");
            ticketRepository.save(ticket);

            Message message = new Message();
            message.setTicket(ticket);
            message.setUser(user);
            message.setCreationDate(now);
            message.setContent("Co chwile problemy z internetem, nigdy nie działa jak należy! Za co ja płacę!");
            messageRepository.save(message);
        }
    }
}
