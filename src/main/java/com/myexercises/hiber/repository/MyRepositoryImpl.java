/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.repository;

import com.myexercises.hiber.entity.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Acer
 */
@Repository
@Transactional
public class MyRepositoryImpl implements MyRepository {
    @Autowired
    HibernateTemplate hibernateTemplate;

    @Override
    public <S extends Data> S save(S entity) {
        hibernateTemplate.save(entity);
        return entity;
    }

    @Override
    public <S extends Data> Iterable<S> save(Iterable<S> entities) {
        entities.forEach((S entity) -> {
            hibernateTemplate.save(entity);
        });
        return entities;
    }

    @Override
    public Data findOne(UUID id) {
        return hibernateTemplate.get(Data.class, id);
    }

    @Override
    public boolean exists(UUID id) {
        return Objects.nonNull(hibernateTemplate.get(Data.class, id));
    }

    @Override
    public Iterable<Data> findAll() {
        return hibernateTemplate.loadAll(Data.class);
    }

    @Override
    public Iterable<Data> findAll(Iterable<UUID> ids) {
        List<UUID> idList = new ArrayList<>();
        ids.forEach(idList::add);
        DetachedCriteria criteria = DetachedCriteria.forClass(Data.class);
        criteria.add(Restrictions.in("id", idList));
        List result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    @Override
    public long count() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Data.class);
        criteria.setProjection(Projections.rowCount());
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Long count = (Long) criteria.getExecutableCriteria(session).uniqueResult();
        return count;
    }

    @Override
    public void delete(UUID id) {
        Data data = new Data();
        data.setId(id);
        hibernateTemplate.delete(data);
    }

    @Override
    public void delete(Data entity) {
        hibernateTemplate.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Data> entities) {
        List<Data> dataList = new ArrayList<>();
        entities.forEach(dataList::add);
        hibernateTemplate.deleteAll(dataList);
    }

    @Override
    public void deleteAll() {
        hibernateTemplate.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from Data")
                .executeUpdate();
    }
    
}
