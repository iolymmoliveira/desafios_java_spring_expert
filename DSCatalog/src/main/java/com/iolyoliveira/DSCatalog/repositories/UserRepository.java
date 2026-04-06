package com.iolyoliveira.DSCatalog.repositories;

import com.iolyoliveira.DSCatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
