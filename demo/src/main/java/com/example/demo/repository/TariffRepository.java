package com.example.demo.repository;

import com.example.demo.entity.TariffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//?
public interface TariffRepository extends JpaRepository<TariffEntity, String> {

        Optional<TariffEntity> findById(Integer id);
        Set<TariffEntity> findAllByNameIn(List<String> searchWords);

    }


