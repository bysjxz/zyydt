package com.cdutcm.tcms.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
    
/**   
 * 使用java线程扫描局域网ip简单方案   
 * @author Administrator   
 *   
 */    
public class LanIP {     
         
    public static void main(String[] args) {     
//        System.out.println("开始......");     
//        LanIP ip = new LanIP();     
//        ArrayList<String> list =  ip.getLanIPArrayList();     
//        System.out.println("最后有多少个===>"+list.size());  
    	List<String> iPs = getIPs();
    	getHostnames(iPs);
    }    
    
    public static List<String>  getIPs()
    {
        List<String> list = new ArrayList<String>();
        boolean flag = false;
        int count=0;
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec("arp -a");
            BufferedReader br = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String inline;
            while ((inline = br.readLine()) != null) {
                if(inline.indexOf("接口") > -1){
                    flag = !flag;
                    if(!flag){
                        //碰到下一个"接口"退出循环
                        break;
                    }
                }
                if(flag){
                    count++;
                    if(count > 2){
                        //有效IP
                        String[] str=inline.split(" {4}");
                        list.add(str[0]);
                    }
                }
                System.out.println(inline);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }
    
    public static Map<String,String> getHostnames(List<String> ips){

        Map<String,String> map = new HashMap<String,String>();
        System.out.println("正在提取hostname...");
        for(String ip : ips){
            String command = "ping -a " + ip;
            Runtime r = Runtime.getRuntime();
            Process p;
            try {
                p = r.exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p
                        .getInputStream()));
                String inline;
                while ((inline = br.readLine()) != null) {
                    if(inline.indexOf("[") > -1){
                        int start = inline.indexOf("Ping ");
                        int end = inline.indexOf("[");
                        String hostname = inline.substring(start+"Ping ".length(),end-1);
                        System.out.println(hostname);
                        map.put(ip,hostname);
                    }
                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("提取结束！");
        return map;
    }
         
    public ArrayList<String> getLanIPArrayList() {     
        ArrayList<String> arrayIP = null;     
        try {     
            InitSystem initSystem = null;     
            initSystem = new InitSystem();     
            Thread thread = new Thread(initSystem);     
            thread.start();     
            thread.join();     
            arrayIP = initSystem.getArrayIPUsed();     
        } catch (UnknownHostException e) {     
            e.printStackTrace();     
        } catch (InterruptedException e) {     
            e.printStackTrace();     
        }     
        return arrayIP;     
    }     
    
    private class InitSystem implements Runnable {     
        private int firstIP = 2;// 查询的 IP 地址的最后一位起始点     
    
        private int lastIP = 255;// 查询的 IP 地址的最后一位结束点     
    
        private volatile ArrayList<Thread> arrayThread;// 子线程段     
    
        private final int MAXTHREADNUM = 30; // 最大同时进行的子线程数量     
    
        private int threadNumNow;// 当前正在进行的子线程数量     
    
        private volatile ArrayList<String> arrayIP;// 局域网查询所有可能的 IP 地址的结果集     
    
        private volatile ArrayList<String> arrayIPUsed;// 局域网查询已经使用的 IP 地址的结果集     
    
        private InitSystem(String ip) {     
            System.out.println("IP===>"+ip);     
            arrayIP = new ArrayList<String>();     
            arrayIPUsed = new ArrayList<String>();     
            arrayThread = new ArrayList<Thread>();     
            setIPAddressList(ip);     
        }     
    
        private InitSystem() throws UnknownHostException {     
            this(InetAddress.getLocalHost().getHostAddress());     
        }     
    
        private synchronized ArrayList<String> getArrayIPUsed() {     
            try {     
                System.out.println("getArrayIPUsed:  arrayIP.size===>"+arrayIP.size());     
                while (arrayIP.size() > 0) {     
                    Thread.sleep(300);     
                }     
            } catch (InterruptedException e) {     
                e.printStackTrace();     
            }     
            return arrayIPUsed;     
        }     
    
        private void setIPAddressList(String ip) {     
            // 根据这个 ip 地址查询它所在的局域网的所有可能 IP 地址的集合     
            int lastPointIndex = ip.lastIndexOf('.');     
            String stringIPHead = ip.substring(0, ++lastPointIndex);     
            System.out.println("stringIPHead===>"+stringIPHead);     
            String stringIP = null;     
            for (int i = firstIP; i <= lastIP; i++) {     
                stringIP = stringIPHead + i;     
                arrayIP.add(stringIP);     
            }     
            System.out.println("进放到这里...arrayIP的总个数："+arrayIP.size());     
        }     
    
        public void run() {     
            synchronized (this) {     
                try {     
                    System.out.println("run()  arrayIP.size()===>"+arrayIP.size());     
                    System.out.println("run()  threadNumNow===>"+threadNumNow);     
                    System.out.println("arrayThread.size()"+arrayThread.size());     
                    while (arrayIP.size() > 0) {     
                        while (threadNumNow >= MAXTHREADNUM) {     
                            System.out.println("线程超出30，中止后面的...");     
                            for (Thread thread : arrayThread) {     
                                if (!thread.getState().equals(     
                                        Thread.State.TERMINATED)) {     
                                    thread.join(5);     
                                }     
                                --threadNumNow;     
                            }     
                            arrayThread = new ArrayList<Thread>();     
                        }     
                        Thread thread = new Thread(new InnerClass(arrayIP     
                                .remove(0)));     
                        thread.start();     
                        threadNumNow++;     
                        arrayThread.add(thread);     
                    }     
                } catch (Exception e) {     
                    e.printStackTrace();     
                }     
            }     
        }     
    
        private class InnerClass implements Runnable {     
            // 线程查询一个 IP 是否是可以连接的 是则加入到相应的 IP 数组     
            private String ip;     
    
            private InnerClass(String ip) {     
                System.out.println("InnerClass ip===>"+ip);     
                this.ip = ip;     
            }     
    
            private boolean isUsedIPAddress(String ip) {     
                System.out.println("isUsedIPAddress===>"+ip);     
                synchronized (this) {     
                    System.out.println("进入此地.....");     
                    // 判断这个 IP 地址在当前局域网中是否是可连接的 IP     
                    Process process = null;     
                    BufferedReader bufReader = null;     
                    String bufReadLineString = null;     
                    try {     
                        process = Runtime.getRuntime().exec(     
                                "ping " + ip + " -w 100 -n 1");     
                        bufReader = new BufferedReader(new InputStreamReader(     
                                process.getInputStream()));     
                        for (int i = 0; i < 6 && bufReader != null; i++) {     
                            bufReader.readLine();     
                        }     
                        bufReadLineString = bufReader.readLine();     
                        System.out.println("bufReadLineString===>"+bufReadLineString);     
                        if (bufReadLineString == null) {     
                            process.destroy();     
                            return false;     
                        }     
                        if (bufReadLineString.indexOf("timed out") > 0    
                                || bufReadLineString.length() < 17    
                                || bufReadLineString.indexOf("invalid") > 0) {     
                            process.destroy();     
                            return false;     
                        }     
                    } catch (IOException e) {     
                        e.printStackTrace();     
                    }     
                    process.destroy();     
                    return true;     
                }     
            }     
    
            public void run() {     
                synchronized (this) {     
                    if (isUsedIPAddress(ip)) {     
                        arrayIPUsed.add(ip);     
                    }     
                }     
            }     
        }     
    }     
}