package ua.com.alevel.messenger_nure.persistence.dao;

import ua.com.alevel.messenger_nure.persistence.entity.BaseEntity;

public interface BaseDao<ENTITY extends BaseEntity> {
    void update(ENTITY entity);

    void delete(Long id);

    boolean existById(Long id);

    ENTITY findById(Long id);
}
