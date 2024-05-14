package gm.practica.repository;

import gm.practica.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowedRepository extends JpaRepository<Follow, Long> {

   @Query("SELECT F " +
       "FROM Follow F " +
       "WHERE F.followed = :followed")
List<Follow> findByFollowed(@Param("followed") Long followed);

}

