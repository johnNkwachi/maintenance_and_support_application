package arnergy.solar.ticket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    Optional<Ticket> findByTicketId(String ticketId);
//    Optional<Ticket> getAllTicket();
}
