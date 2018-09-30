package com.cdutcm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

import com.google.gson.Gson;

public class Test2 {

	public static void main(String[] args) throws Exception,
			UnsupportedEncodingException {

		String name = "admin";
		String s = "{\"name\":\"张三\",\"age\":\"34\"}";
		Gson gson = new Gson();
		Person person = gson.fromJson(s, Person.class);
		System.out.println(person.getName());

		String en = EncoderByMd5("admin");
		System.out.println(en);
	}

	public static String EncoderByMd5(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		return base64en.encode(md5.digest(str.getBytes("utf-8")));
	}

	protected class Person {
		private String name;
		private int age;
		private String addr;
		private String a;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
	}
}
