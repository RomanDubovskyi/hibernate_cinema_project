package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Role;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long roleId = (Long) session.save(role);
            transaction.commit();
            role.setId(roleId);
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert role entity", e);
        }
    }

    @Override
    public Role getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery("FROM Role WHERE roleName =: n", Role.class);
            query.setParameter("n", name);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Cant' find role by name " + e);
        }
    }
}
