package com.sully.string;

/**
 * Creator: lei.s
 * Create Date: 2017年04月14日
 * 类功能描述：
 * 1.synchronized锁的对象需要时同一个同步才有效
 * 2.
 */
public class StringThread implements Runnable {
    private static final String LOCK_PREFIX = "XXX---";

    private String ip;

    public StringThread(String ip){
        this.ip = ip;
    }
    @Override
    public void run() {
        String lock = this.createLockStr();
        synchronized (lock){
            System.out.println("[" + Thread.currentThread().getName() + "]开始运行了");
            // 休眠5秒模拟脚本调用
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + Thread.currentThread().getName() + "]结束运行了");
        }
    }

    public String createLockStr(){
        StringBuilder lockBuilder = new StringBuilder();
        lockBuilder.append(LOCK_PREFIX);
        lockBuilder.append(this.ip);

        String lock = lockBuilder.toString().intern();
        System.out.println("线程"+Thread.currentThread().getName()+"构造了锁["+lock+"]");
        return lock;
    }
}
