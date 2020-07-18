package fr.laPouleQuiMousse.repositories;
import fr.laPouleQuiMousse.models.Enums.ERoleName;
import fr.laPouleQuiMousse.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERoleName name);

}
