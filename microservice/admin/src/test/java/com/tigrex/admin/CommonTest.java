package com.tigrex.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tigrex.admin.entity.User;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class CommonTest {

    private Random ra = new Random();

    User user = new User().setId(1);

    @Test
    public void oneTest(){
//        Map<String, Object> data = new LinkedHashMap<>();
//        List<String> dataList = new ArrayList<>();
//        DateFormat ymddf = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat mddf = new SimpleDateFormat("MM月dd日");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(calendar.DATE, -27);
//        String ymdStr = ymddf.format(calendar.getTime());
//        String mdStr = mddf.format(calendar.getTime());
//        data.put(ymdStr, mdStr);
//        dataList.add(mdStr);
//        for (int j = 0; j < 27; j++) {
//            calendar.add(calendar.DATE, +1);
//            ymdStr = ymddf.format(calendar.getTime());
//            mdStr = mddf.format(calendar.getTime());
//            data.put(ymdStr, mdStr);
//            dataList.add(mdStr);
//        }
//        System.out.println(data.toString());
//        System.out.println(dataList.toString());
//        Double x = 0.0d;
//        Double y = 1d;
//        System.out.println(x.doubleValue() == 0);
//        System.out.println(y/x);

//        BigDecimal a = new BigDecimal(12.1);
//        BigDecimal b = new BigDecimal(12.2);
//        System.out.println(a.setScale(1, RoundingMode.UP));
//        System.out.println(b.setScale(1, RoundingMode.UP));
//        System.out.println(a.add(b).setScale(1, RoundingMode.UP));
//        Double db = (double) Math.round(17.223232d * 100) / 100;
//        System.out.println(db);
//        System.out.println(db - (db % 10.0d) + 10.0d);
//        String a = "12.33%";
//        System.out.println(a.replace("%", ""));
//        System.out.println(ra.nextDouble() * (1000 - 0) + 0);
//        System.out.println(12.000000000);
        System.out.println(1.111d / 100);
    }

    @Test
    public void nothingTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Future<String>> futures = new ArrayList<>();
        Set<Callable<String>> set = new LinkedHashSet<>();
        ReentrantLock reentrantLock = new ReentrantLock(false);
        for (int i = 0; i < 1000000; i++) {
            int count = i;
            Callable<String> callable = new Callable<>() {//创建callable
                @Override
                public String call() throws Exception {
                    reentrantLock.lock();
                    try {
//                        synchronized (user) {
                            user.setId(user.getId() + 1);
                            return user.getId().toString();
//                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        return "";
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            };
            set.add(callable);//add进set集合
        }
        try {
            long start = System.currentTimeMillis();
            futures = executorService.invokeAll(set);
            long end = System.currentTimeMillis();
            Iterator<Future<String>> iterator = futures.iterator();
            Integer x = 0;
            while (iterator.hasNext()){
                Future<String> future = iterator.next();
                if(x.intValue() < Integer.parseInt(future.get())){
                    x = Integer.parseInt(future.get());
                }
//                System.out.println(future.get());
            }
            System.out.println((end - start) / 1000 + "s");
            System.out.println("计算最大值" + x);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Runnable myRunnable = () -> {
//            System.out.println("hello world!");
//        };
//        Thread t1 = new Thread(myRunnable);
//        Thread t2 = new Thread(myRunnable);
//        t1.start();
//        t2.start();

//        Callable<String> myCallable = () -> {
//            System.out.println("hello world");
//            return "";
//        };
//
//        FutureTask<String> futureTask = new FutureTask<>(myCallable);
//        Thread thread = new Thread(futureTask, "送餐员");
//        thread.start();

    }

    @Test
    public void countThreadTest(){
        // 计算方法1
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        int totalThread = threadGroup.activeCount();
        System.out.println("当前程序线程总数： " + totalThread);
        Thread[] lstThreads = new Thread[totalThread];
        threadGroup.enumerate(lstThreads);
        for (int i = 0; i < totalThread; i++) {
            System.out.println("线程号：" + lstThreads[i].getId() + " = " + lstThreads[i].getName());
        }
        // 计算方法2
        // 获取java线程管理器MXBean，dumpAllThreads参数：lockedMonitors参数表示是否获取同步的monitor信息，
        //lockedSynchronizers表示是否获取同步的synchronizer
//        ThreadInfo[] threadInfos = ManagementFactory.getThreadMXBean().dumpAllThreads(false, false);
//        for (ThreadInfo threadInfo : threadInfos) {
//            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
//        }
    }

    @Test
    public void jsonTest(){
        Map<String, Object> data = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse("2018-11-21 17:34:24");
            data.put("str", date);
            data.put("nowStr", new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(data));
        String dataStr = JSON.toJSONString(data, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dataStr);

    }

}
