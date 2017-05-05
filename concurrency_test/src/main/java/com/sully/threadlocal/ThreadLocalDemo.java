package com.sully.threadlocal;

import java.util.Random;

/**
 * Creator: lei.s
 * Create Date: 2017年05月05日
 * 类功能描述：
 */
public class ThreadLocalDemo implements Runnable {
    //创建线程局部变量studentLocal，在后面你会发现用来保存Student对象
    private final static ThreadLocal studentLocal = new ThreadLocal();

    public static void main(String[] agrs) {
        ThreadLocalDemo td = new ThreadLocalDemo();
        Thread t1 = new Thread(td, "a");
        Thread t2 = new Thread(td, "b");
        t1.start();
        t2.start();
    }

    public void run() {
        accessStudent();
    }

    /**
     * 示例业务方法，用来测试
     */
    public void accessStudent() {
        //获取当前线程的名字
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running!");
        //产生一个随机数并打印
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println("thread " + currentThreadName + " set age to:" + age);

        //获取一个Student对象，并将随机数年龄插入到对象属性中,
        //此时是a线程第一次调用Threadlocal的get方法，get返回null，实例化一个student并且将a线程放进threadlocal。
        //当b线程到来是，与a线程相同的历程
        getStudent().setAge(age);
        //当a线程执行，此处调用getStudent方法，返回a线程的副本（student）
        //当b线程执行，返回b线程的副本
        System.out.println("thread " + currentThreadName + " first read age is:" + getStudent().getAge());
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName + " second read age is:" + getStudent().getAge());
    }

    protected Student getStudent() {
        //获取本地线程变量并强制转换为Student类型
        Student student = (Student) studentLocal.get();
        //线程首次执行此方法的时候，studentLocal.get()肯定为null
        if (student == null) {
            System.out.println("--------");
            //创建一个Student对象，并保存到本地线程变量studentLocal中
            student = new Student();
            studentLocal.set(student);
        }
        return student;
    }
}