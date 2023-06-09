package ua.com.alevel.messenger_nure.service;

import ua.com.alevel.messenger_nure.persistence.entity.BaseEntity;

public interface BaseService<ENTITY extends BaseEntity> {

    void update(ENTITY entity);

    void delete(Long id);

    ENTITY findById(Long id);

    boolean existById(Long id);
}