package gm.practica.repository;

import gm.practica.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFollowRepository extends JpaRepository<Follow, Long> {

   @Query("SELECT F " +
       "FROM Follow F " +
       "WHERE F.follower = :follower")
List<Follow> findByFollower(@Param("follower") Long follower);

    Optional<Follow> findByFollowerAndFollowed(Long follower, Long followed);


}

