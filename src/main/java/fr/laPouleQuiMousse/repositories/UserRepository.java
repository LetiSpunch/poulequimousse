package fr.laPouleQuiMousse.repositories;

import fr.laPouleQuiMousse.models.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);

    User findByToken(String token);

    @Modifying
    @Transactional
    @Query("update User u set u.token = ?2, u.tokenExpirationDate = ?3 where u.id = ?1")
    void saveToken(Long id, String token, Date expiration);

    @Modifying
    @Transactional
    @Query("update User u set u.encodedPassword = ?2, u.token = null, u.tokenExpirationDate = null where u.id = ?1")
    void savePassword(Long id, String encodedPassword);

    @Modifying
    @Transactional
    @Query("update User u set u.lastLoginDate = ?2 where u.id = ?1")
    void updateLastLoginDate(Long id, Date now);
}

