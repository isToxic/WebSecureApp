package is.toxic.dao;

import is.toxic.entity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleDAO extends CrudRepository<AppUser, Long> {

    @Query("Select ur.appRole.roleName from UserRole ur where ur.appUser.userId = :userId")
    List<String> getRoleNames(@Param("userId") Long userId);
}

