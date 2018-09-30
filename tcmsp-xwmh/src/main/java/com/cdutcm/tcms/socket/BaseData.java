package com.cdutcm.tcms.socket;

public class BaseData {

	private String timeSwitch;	// 代表状态01为开启状态，00为关闭，仅针对TM一项有效
	private int dataDirection;// 数据方向
	private int waveType;	// 波形
	private int schedule;	// 定时时间
	private int freq;	// 频率，对应10到50Hz
	private int A1;
	private int A2;
	private int A3;
	private int A4;
	private int A5;
	private int A6;
	
	
	public String getTimeSwitch() {
		return timeSwitch;
	}
	public void setTimeSwitch(String timeSwitch) {
		this.timeSwitch = timeSwitch;
	}
	public int getDataDirection() {
		return dataDirection;
	}
	public void setDataDirection(int dataDirection) {
		this.dataDirection = dataDirection;
	}
	public int getWaveType() {
		return waveType;
	}
	public void setWaveType(int waveType) {
		this.waveType = waveType;
	}
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public int getA1() {
		return A1;
	}
	public void setA1(int a1) {
		A1 = a1;
	}
	public int getA2() {
		return A2;
	}
	public void setA2(int a2) {
		A2 = a2;
	}
	public int getA3() {
		return A3;
	}
	public void setA3(int a3) {
		A3 = a3;
	}
	public int getA4() {
		return A4;
	}
	public void setA4(int a4) {
		A4 = a4;
	}
	public int getA5() {
		return A5;
	}
	public void setA5(int a5) {
		A5 = a5;
	}
	public int getA6() {
		return A6;
	}
	public void setA6(int a6) {
		A6 = a6;
	}
	
}
