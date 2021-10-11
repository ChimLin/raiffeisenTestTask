package com.ruslan.raiffeisen.repository;

import com.ruslan.raiffeisen.entity.SocksEntity;
import org.springframework.data.repository.CrudRepository;

public interface SocksRepository extends CrudRepository<SocksEntity, Long> {
    Iterable<SocksEntity> findByColor(String color);
}
