package com.dev.cinema.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory initSessionFactory() {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
            return sessionFactory;
        } catch (Exception e) {
            throw new RuntimeException("Can't create Session Factory", e);
        }
    }
}
