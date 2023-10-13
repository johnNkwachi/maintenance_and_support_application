package arnergy.solar.ticket.dto;

import arnergy.solar.ticket.TicketPriority;
import arnergy.solar.utils.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ticketRequest {
    private String title;
    private String description;
    private String image;
    TicketPriority priority;


}
