package com.sully.threadlocal;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Creator: lei.s
 * Create Date: 2017年05月05日
 * 类功能描述：
 */
public class HibernateUtil {
    private static Logger log = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory;     //定义SessionFactory

    static {
        try {
            // 通过默认配置文件hibernate.cfg.xml创建SessionFactory
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            log.error("初始化SessionFactory失败！", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //创建线程局部变量session，用来保存Hibernate的Session
    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    /**
     * 获取当前线程中的Session
     * @return Session
     * @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        Session s =  session.get();
        // 如果Session还没有打开，则新开一个Session
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);         //将新开的Session保存到线程局部变量中
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        //获取线程局部变量，并强制转换为Session类型
        Session s = session.get();
        session.set(null);
        if (s != null)
            s.close();
    }
}