package com.cdutcm.tcms.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.StringTokenizer;
import java.util.Vector;


public class Network1 {
    public static InetAddress[] getAllOnline() {
        // TODO Auto-generated method stub
        Vector v = new Vector(50);
        try {
            // Process process1 =
            // Runtime.getRuntime().exec("ping -w 2 -n 1 192.168.1.%i");
            // process1.destroy();
            Process process = Runtime.getRuntime().exec("arp -a");
            InputStreamReader inputStr = new InputStreamReader(
                    process.getInputStream(), "GBK");
            BufferedReader br = new BufferedReader(inputStr);
            String temp = "";
            br.readLine();
            br.readLine();
            br.readLine();// 此后开始读取IP地址，之前为描述信息，忽略。
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                    StringTokenizer tokens = new StringTokenizer(temp);
                    String x;
                    InetAddress add=null;
                    try {
                        add = InetAddress.getByName(x = tokens
                                .nextToken());
                    } catch (java.net.UnknownHostException e) {
                        continue;
                    }
                    // System.out.println(x);
                    v.add(add);
                    // System.out.println(add);
            }
            v.add(InetAddress.getLocalHost());
            process.destroy();
            br.close();
            inputStr.close();
        } catch (Exception e) {
            System.out.println("可能是网络不可用。");
            e.printStackTrace();
        }
        int cap = v.size();
        InetAddress[] addrs = new InetAddress[cap];
        for (int i = 0; i < cap; i++) {
            addrs[i] = (InetAddress) v.elementAt(i);
             System.out.println(addrs[i]);
        }
        return addrs;

    }

    public static void main(String args[]) {
        InetAddress[] i = new Network1().getAllOnline();
    }
}
