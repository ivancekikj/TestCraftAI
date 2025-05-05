package mk.com.testcraftai.testcraftai.Repository;

import mk.com.testcraftai.testcraftai.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReporsitory extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
}
