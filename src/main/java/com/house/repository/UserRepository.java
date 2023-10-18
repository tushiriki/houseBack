package com.house.repository;

import com.house.entity.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List; 

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT count(u) > 0 FROM UserEntity u  WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT count(C) > 0 FROM UserEntity C WHERE C.username=?2 and C.id!=?1")
    boolean verificationUsernname(Integer id, String username);


    @Query(value = "SELECT * FROM users WHERE username LIKE %?1%", nativeQuery = true)
    List<UserEntity> findByUsernameContaining(@Param("username") String username);

    UserEntity findByUsername(String username);

    @Query("select e from UserEntity e where  e.username like '%' || ?1  || '%' or e.email  like '%' || ?1  || '%' ")
    Page<UserEntity> getByUsernameAndEmail(String libelle, Pageable pagingSort);

}