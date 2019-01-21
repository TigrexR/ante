package com.tigrex.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tigrex.api.vo.UserVo;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class CommonTest {

    private Random ra = new Random();

    private Lock reentrantLock = new ReentrantLock(false);

    private final StampedLock stampedLock = new StampedLock();

    UserVo user = new UserVo().setId(1);

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
        for (int i = 0; i < 1000000; i++) {
            int count = i;
            Callable<String> callable = new Callable<>() {//创建callable
                @Override
                public String call() throws Exception {
                    reentrantLock.lock();
                    try {
                        user.setId(user.getId() + 1);
                        return user.getId().toString();
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

    @Test
    public void quartzTest(){
        String cron = "0 30 11 * * ?";
        Date afterDate = new Date(new Date().getTime() + 600000);
        SimpleDateFormat ymdformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat allformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] a = cron.split(" ");
        int hc = Integer.parseInt(a[2]);
        int mc = Integer.parseInt(a[1]);
        String quartzTime = ymdformat.format(new Date());
        try {
            Date quartzDate = allformat.parse(quartzTime + " " + hc + ":" + mc + ":" + "00");
            if(afterDate.after(quartzDate)){
                //那么就用新的时间去处理
            } else {
                //那么就用原本时间处理
            }
            Date date = allformat.parse("2018-12-12" + " 23:59:59");
//            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double f = 111231.5585;
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
    }

    @Test
    public void httpClintTest(){
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .authenticator(Authenticator.getDefault())
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
//        HttpResponse<String> response = null;
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(response.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println(response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }

    @Test
    public void sortTest(){
        List<UserVo> userVos = Lists.newArrayList(
                new UserVo().setId(1).setAge(13),
                new UserVo().setId(1).setAge(12),
                new UserVo().setId(2).setAge(13),
                new UserVo().setId(2).setAge(12),
                new UserVo().setId(2).setAge(17)
        );
//        HashMap<String, String> map = Maps.newHashMap();
//        LinkedList<UserVo> UserVoList = Lists.newLinkedList();
        userVos.sort(Comparator.comparing(UserVo::getId)
                .thenComparing(UserVo::getAge));
        System.out.println(userVos);
    }

}
