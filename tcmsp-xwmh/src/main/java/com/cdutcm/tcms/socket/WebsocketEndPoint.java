package com.cdutcm.tcms.socket;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import redis.clients.jedis.Jedis;

import com.cdutcm.core.util.PropertiesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebsocketEndPoint extends TextWebSocketHandler {
	private Timer timer;
	private boolean stop = false;
	private String sendStr = "SendString";
//	  private String netAddress = "192.168.43.198";
	  private String netAddress = "192.168.112.121";//
	  private final int PORT_NUM = 8000;
	 
	  private DatagramSocket datagramSocket;
	  private DatagramPacket datagramPacket;

//	@Autowired
//	private EacClientHandler eacClientHandler;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
		if(!session.isOpen()) {
			this.timer.cancel();
			return;
		}
		super.handleTextMessage(session, message);
		session.sendMessage(message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.timer = new Timer(true);
		this.stop = false;
		long delay = 0;
		netAddress = PropertiesUtil.getValue("equipment.port");
		System.out.println(netAddress);
		OrderTimeTask orderTimeTask = new OrderTimeTask(session);
		// 设定指定的时间time,此处为1分钟
		this.timer.schedule(orderTimeTask, delay, 2000);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.stop = true;
		System.out.println("Connection Closed！");
	}
//	public EacClientHandler getEacClientHandler() {
//		return eacClientHandler;
//	}
//	public void setEacClientHandler(EacClientHandler eacClientHandler) {
//		this.eacClientHandler = eacClientHandler;
//	}

	private NumberCode getNumberCode(String data) {
		NumberCode result = null;
		
		// 时间
		String time = data.substring(14, 16);
		// 频率
		String freq = data.substring(22, 24);
		// 通道1-6的强度等级
		String strength1 = data.substring(30, 32);
		String strength2 = data.substring(38, 40);
		String strength3 = data.substring(46, 48);
		String strength4 = data.substring(54, 56);
		String strength5 = data.substring(62, 64);
		String strength6 = data.substring(70, 72);
				
		String electricity = strength1 + "." + strength2 + "." + strength3 + "." + strength4 + "." + 
				strength5 + "." + strength6;
		
		result = new NumberCode(time, freq, electricity, "");
		
//		String numberCode = "12.12.00.";
//		for(int i = 0; i < 13; i++) {
//			int number = (int)(Math.random() * 100 + 1);
//			numberCode += (number < 10 ? "0" + number : number) + ".";
//		}
//		Jedis jedis = new Jedis("localhost");
//		Object eactStr = (Object)jedis.hmget("eact2", "mapKey");
//		List<BaseData> bds = new Gson().fromJson(eactStr.toString(), new TypeToken<List<BaseData>>() {}.getType());
//		BaseData bd = bds.get(0);
//		String str = new String(numberCode.substring(0, numberCode.length() - 1));
//		if(str != null)
//			str = str.trim();
//		if (str != null && !"".equals(str)) {
//			// 时间
//			String time = str.substring(0, 8);
//			time = bd.getSchedule() + "";
//			System.out.println("时间：" + time);
//			// 频率
//			String freq = str.substring(9, 11);
//			freq = bd.getFreq() + "";
//			System.out.println("频率：" + freq);
//			// 强度
//			String strength = str.substring(12, 26);
//			System.out.println("电流：" + strength);
//			// 电流
//			String electricity = str.substring(27, 44);
//
//			double floor = Math.floor(Math.random()*10);
//			System.out.println(floor);
//			
//			bd.setA1(bd.getA1() + new Double(floor).intValue());
//			electricity = bd.getA1() + "." + bd.getA2() + "." + bd.getA3() + "." + bd.getA4() + "." + 
//					bd.getA5() + "." + bd.getA6();
//			System.out.println("强度：" + electricity);
//			
//			result = new NumberCode(time, freq, electricity, strength);
//		}
		return result;
	}

	private class OrderTimeTask extends TimerTask {
		private WebSocketSession session;

		public OrderTimeTask(WebSocketSession session) {
			this.session = session;
		}
		@Override
		public void run() {
			while (true) {
				try {
//					if(stop) {
//						this.cancel();
//					}
					try {
			            /*** 发送数据***/
			            // 初始化datagramSocket,注意与前面Server端实现的差别
			            datagramSocket = new DatagramSocket();
			            // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
			            byte[] buf = sendStr.getBytes();
			            InetAddress address = InetAddress.getByName(netAddress);
			            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
			            // 发送数据
			            datagramSocket.send(datagramPacket);
			           
			            /*** 接收数据***/
			            byte[] receBuf = new byte[1024];
			            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
			            datagramSocket.receive(recePacket);
			           
			            String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
			            System.out.println("Client Rece Ack:" + receStr);
			            //WV000001TM010009FQ000010A1000001A2000000A3000000A4000000A5000000A6000000
			            handleMessage(this.session, new TextMessage(new Gson().toJson(getNumberCode(receStr))));
	//		            System.out.println(recePacket.getPort());
			           
			           
			        } catch (SocketException e) {
			            e.printStackTrace();
			        } catch (UnknownHostException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally {
			            // 关闭socket
			            if(datagramSocket != null){
			                datagramSocket.close();
			            }
			        }
	//				UdpClient client = new UdpClient();
	//				final String serverData = client.serverData;
	//				System.out.println(serverData+">>>>");
//					handleMessage(this.session, new TextMessage(new Gson().toJson(getNumberCode())));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 电针仪临时实体
	 * @author FJL
	 */
	public class NumberCode implements Serializable {
		private static final long serialVersionUID = 6770335941808834371L;
		// 时间
		private String time;
		// 频率
		private String freq;
		// 电流
		private String electricity;
		// 强度
		private String strength;

		public NumberCode() {  }
		public NumberCode(String time, String freq, String electricity, String strength) {
			this.time = time;
			this.freq = freq;
			this.electricity = electricity;
			this.strength = strength;
		}

		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getFreq() {
			return freq;
		}
		public void setFreq(String freq) {
			this.freq = freq;
		}
		public String getElectricity() {
			return electricity;
		}
		public void setElectricity(String electricity) {
			this.electricity = electricity;
		}
		public String getStrength() {
			return strength;
		}
		public void setStrength(String strength) {
			this.strength = strength;
		}
	}
}
