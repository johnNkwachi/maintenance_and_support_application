package arnergy.solar.user;

import arnergy.solar.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean disabled = false;
//    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @DBRef
    private List<Ticket> tickets;


    public User(String emailAddress, String firstName, String lastName, String password, UserRole userRole) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
    }
    public User(Ticket ticket) {
        this.tickets = new ArrayList<Ticket>();
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void isDisabled(Boolean disabled) {
        this.disabled = disabled;
    }




}
