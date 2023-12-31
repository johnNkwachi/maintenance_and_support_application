package arnergy.solar.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailAddressIgnoreCase(String emailAddress);
    Optional <User> findById(String id);
}
