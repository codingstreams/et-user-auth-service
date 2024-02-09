package in.codingstreams.etuserauthservice.data.repo;

import in.codingstreams.etuserauthservice.data.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepo extends MongoRepository<AppUser, String> {
  boolean existsByEmail(String email);

  Optional<AppUser> findByEmail(String email);
}
