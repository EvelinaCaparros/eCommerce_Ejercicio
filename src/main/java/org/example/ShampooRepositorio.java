package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampooRepositorio extends JpaRepository<Shampoo, Integer> {
    List<Shampoo> findByEstado(int estado);
}
