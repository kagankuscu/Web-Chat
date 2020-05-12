package com.kagankuscu.webchat.subStructure;

import com.kagankuscu.webchat.model.domain.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * IUserRepository
 */
@Repository
public interface IUserRepository extends JpaRepository<UserDTO, Integer> {
}