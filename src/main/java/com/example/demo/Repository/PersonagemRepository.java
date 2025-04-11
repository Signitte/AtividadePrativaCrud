package com.example.demo.Repository;

import com.example.demo.Model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository  extends JpaRepository<Personagem,Long> {
}
