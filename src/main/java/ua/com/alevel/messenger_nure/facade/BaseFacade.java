package ua.com.alevel.messenger_nure.facade;

import ua.com.alevel.messenger_nure.persistence.entity.BaseEntity;

import java.sql.SQLException;

public interface BaseFacade<ENTITY extends BaseEntity> {

    void create(ENTITY entity) throws SQLException, ClassNotFoundException;

    void update(ENTITY entity);

    void delete(Long id);

    ENTITY findById(Long id);

}