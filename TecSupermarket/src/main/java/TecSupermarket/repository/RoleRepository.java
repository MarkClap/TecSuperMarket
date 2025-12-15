package TecSupermarket.repository;

import TecSupermarket.enums.RoleList;
import TecSupermarket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Object> findByName(RoleList roleList);
}
