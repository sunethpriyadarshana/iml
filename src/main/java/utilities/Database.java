/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import org.hibernate.Session;

/**
 *
 * @author Suneth Priyadarshana
 */
public class Database {

    private static Session session;

    static {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
    }

    public static Session getSession() {
        return session;
    }

}
