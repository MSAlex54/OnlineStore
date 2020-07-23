package my.shop.repo;

import my.shop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUserName(String userName);

    @Query("from User u where u.userName like :pattern")
    List<User> queryByNamePattern(@Param("pattern") String pattern);

    Optional<User> findUserByUserName(String userName);
}
