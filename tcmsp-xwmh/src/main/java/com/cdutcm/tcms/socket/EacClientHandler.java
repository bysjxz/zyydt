package com.cdutcm.tcms.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.biz.model.EnLog;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

@Component("eacClientHandler")
public class EacClientHandler extends ChannelInboundHandlerAdapter{
	
	
	public String eacData = "01010100100101001010";
	static String fre = "1010";
	public int changeCount = 0;
	
	// 接收server端的消息，并打印出来
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf result = (ByteBuf) msg;
		byte[] result1 = new byte[result.readableBytes()];
		result.readBytes(result1);
		String str = new String(result1);
		System.out.println("channelRead:" + str);
		if(!str.contains("Are")){
		if(str!=null)
		str = str.trim();
		String[] strV2 = {"WV000003","TM010005","FQ000009","A1000015","A2000025","A3000025","A4000026","A5000027","A6000028"};	
		if (str != null && !"".equals(str)) {
			String str1 = str.substring(0, 8); // 时间
			String str2 = str.substring(8, 10); // 频率
			String str3 = str.substring(10, 27); // 电流
			String str4 = str.substring(27, 44); // 强度
			String strbuff = str.substring(44, 45);
			int i = Integer.parseInt(strbuff);
			if (i == 1) {
				System.out.println("连续波:" + new String(strbuff));
			} else if (i == 2) {
				System.out.println("疏密波:" + new String(strbuff));
			} else if (i == 3) {
				System.out.println("断续波:" + new String(strbuff));
			}
			EacClientHandler clientHandler = new EacClientHandler();
			clientHandler.setEacData(str);
			eacData = str;
			System.out.println("时间：" + str1);
			System.out.println("频率：" + fre);
			System.out.println("电流：" + str3);
			System.out.println("强度：" + str4);
			if(!fre.equals(str2)){
			}
//			EacClientHandler eacClientHandler = new EacClientHandler();
			EacClientHandler.fre = str2;
			System.out.println(changeCount++ + ":" + EacClientHandler.fre);	
			HashMap<String,String> map = new HashMap<String,String>();  
			BaseData bd = parseStrArray(strV2);
			EnLog en = BaseDataToEnLog(bd);
			String bdStr = new Gson().toJson(bd);
			Jedis jedis = new Jedis("localhost");
			// 判断redis 里有无缓存
			List<String> s  = jedis.hmget("eact2","mapKey");
			if(s.size()<=0){
				// 存储到数据库
				String enStr = "id="+en.getId()+"&freq="+en.getFreq()+"&ip="+en.getIp()+"&schedule="+en.getSchedule()+"&wavetype="+en.getWavetype()+"&strength1="+en.getStrength1()+
						"&strength2="+en.getStrength2()+"&strength3="+en.getStrength3()+"&strength4="+en.getStrength4()+"&strength5="+en.getStrength5()+"&strength6="+en.getStrength6()+
						"&data="+en.getDate()+"&visitNo="+en.getVisitno();
				@SuppressWarnings("static-access")
				String re = this.sendPost("http://localhost:8080/eact-18/enLog/insert", enStr);
				map.put("mapKey", bdStr);
				jedis.hmset("eact2", map);
				System.out.println("数据库成功插入"+re+"条数据");
				return;
			}
			System.out.println(bdStr);
			// 如果redis缓存有值  判断误差值
			boolean m =	ifStorage(bd);
			if(m){
				String enStr = "id="+en.getId()+"&freq="+en.getFreq()+"&ip="+en.getIp()+"&schedule="+en.getSchedule()+"&wavetype="+en.getWavetype()+"&strength1="+en.getStrength1()+
						"&strength2="+en.getStrength2()+"&strength3="+en.getStrength3()+"&strength4="+en.getStrength4()+"&strength5="+en.getStrength5()+"&strength6="+en.getStrength6()+
						"&data="+en.getDate()+"&visitNo="+en.getVisitno();
				@SuppressWarnings("static-access")
				String re = this.sendPost("http://localhost:8080/eact-18/enLog/insert", enStr);
				System.out.println("数据库成功插入"+re+"条数据");
			}
			map.put("mapKey", bdStr);
			jedis.hmset("eact2", map);
		}
		}
		result.release();
	}
	//
	public boolean ifStorage(BaseData bsdata){
		Jedis jedis = new Jedis("localhost");
		List<String> a	= jedis.hmget("eact2","mapKey");
		// 处理大写属性转换不成功的情况
		String bdstr = a.get(0).replace("A", "a");
		JSONObject jsonObject=JSONObject.fromObject(bdstr);
		BaseData bs = (BaseData) JSONObject.toBean(jsonObject, BaseData.class);
		if( Math.abs(bs.getA1()-bsdata.getA1())>=2 ||
			Math.abs(bs.getA2()-bsdata.getA2())>=2 ||
			Math.abs(bs.getA3()-bsdata.getA3())>=2 ||
			Math.abs(bs.getA4()-bsdata.getA4())>=2 ||
			Math.abs(bs.getA5()-bsdata.getA5())>=2 ||
			Math.abs(bs.getA6()-bsdata.getA6())>=2 
			) {
			return true;
		}
		return false;
	}
	public BaseData parseStrArray(String[] strV2){
		BaseData bd = new BaseData();
		for (String str : strV2) {
			if(str.contains("WV")){
				bd.setDataDirection(Integer.parseInt(str.substring(4, 6)));
				bd.setWaveType(Integer.parseInt(str.substring(6, 8)));	// 波形
			}
			if(str.contains("TM")){
				bd.setTimeSwitch(str.substring(2, 4));// 开关
				bd.setSchedule(Integer.parseInt(str.substring(6, 8)));	// 定时时间
			}
			if(str.contains("FQ")){
				bd.setFreq(Integer.parseInt(str.substring(6, 8)));	// 频率
			}
			if(str.contains("A1")){
				bd.setA1(Integer.parseInt(str.substring(6, 8)));	// 强度1
			}
			if(str.contains("A2")){
				bd.setA2(Integer.parseInt(str.substring(6, 8)));	// 强度2
			}
			if(str.contains("A3")){
				bd.setA3(Integer.parseInt(str.substring(6, 8)));	// 强度3
			}
			if(str.contains("A4")){
				bd.setA4(Integer.parseInt(str.substring(6, 8)));	// 强度4
			}
			if(str.contains("A5")){
				bd.setA5(Integer.parseInt(str.substring(6, 8)));	// 强度5
			}
			if(str.contains("A6")){
				bd.setA6(Integer.parseInt(str.substring(6, 8)));	// 强度6
			}
		}
		
		return bd;
	}
    /**
     * 转换对象
     * @param baseData
     * @return
     */
	public EnLog BaseDataToEnLog(BaseData baseData){
		EnLog e = new EnLog();
		e.setId(new IdWorker().nextId());
		e.setFreq(String.valueOf(baseData.getFreq()));
		e.setDate(new Date());
		e.setSchedule(String.valueOf(baseData.getSchedule()));
		//TODO IP暂时不传   visitNo 暂时不传
		e.setIp("");
		e.setVisitno("");
		e.setStrength1(String.valueOf(baseData.getA1()));
		e.setStrength2(String.valueOf(baseData.getA2()));
		e.setStrength3(String.valueOf(baseData.getA3()));
		e.setStrength4(String.valueOf(baseData.getA4()));
		e.setStrength5(String.valueOf(baseData.getA5()));
		e.setStrength6(String.valueOf(baseData.getA6()));
		e.setWavetype(String.valueOf(baseData.getWaveType()));
		return e;
	}
	// 连接成功后，向server发送消息
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String msg = "Are you ok?";
		ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
		encoded.writeBytes(msg.getBytes());
		ctx.write(encoded);
		ctx.flush();
	}

	public String getEacData() {
		return eacData;
	}

	public void setEacData(String eacData) {
		this.eacData = eacData;
	}
	
	 /** 
     * 向指定URL发送POST方法的请求 
     *@paramurl 发送请求的URL 
     *@returnURL所代表远程资源的响应 
     */ 
   public static String sendPost(String url, String param) { 
      OutputStreamWriter out= null;
         BufferedReader in= null;
         String result= "";
        try{
             URL realUrl= new URL(url);
            // 打开和URL之间的连接
             URLConnection conn= realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流             
            out= new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in= new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
             String line;
            while((line= in.readLine()) !=null) {
                result+= line;
             }
         }catch(Exception e) {
             System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
         }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                 }
                if(in!=null){
                    in.close();
                 }
             }
            catch(IOException ex){
                ex.printStackTrace();
             }
         }
        return result;
     }   
}
