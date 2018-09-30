package com.cdutcm.tcms.sys.controller;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.service.UserService;

/**
 * /**
 * 
 * @author zhufj
 *
 */
@Controller
@RestController
public class ForgetPassController1 {

	@Autowired
	private UserService userService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */

	@RequestMapping("/getImage.do")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 System.out.println("#######################生成数字和字母的验证码#######################");  
	        BufferedImage img = new BufferedImage(68, 22,  
	  
	        BufferedImage.TYPE_INT_RGB);  
	  
	        // 得到该图片的绘图对象    
	  
	        Graphics g = img.getGraphics();  
	  
	        Random r = new Random();  
	  
	        Color c = new Color(200, 150, 255);  
	  
	        g.setColor(c);  
	  
	        // 填充整个图片的颜色    
	  
	        g.fillRect(0, 0, 68, 22);  
	  
	        // 向图片中输出数字和字母    
	  
	        StringBuffer sb = new StringBuffer();  
	  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	  
	        int index, len = ch.length;  
	  
	        for (int i = 0; i < 4; i++) {  
	  
	            index = r.nextInt(len);  
	  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
	  
	            (255)));  
	  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // 输出的  字体和大小                      
	  
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //写什么数字，在图片 的什么位置画    
	  
	            sb.append(ch[index]);  
	  
	        }  
	  
	        //把验证码的值存储在session中目的是校验
	        request.getSession().setAttribute("piccode", sb.toString());  
	  
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	@RequestMapping("/ForgetPass")
	public String ForgetPass(String capthca, HttpSession session, String phoneNumber){

		System.out.println(phoneNumber);
		User user = userService.getUserByAccount(phoneNumber);
		String capthca1 = capthca.toUpperCase();
		System.out.println(capthca1);
		String result = null;
		String rCap = (String) session.getAttribute("piccode");

		System.out.println(rCap);
		if(capthca1.equals(rCap) && user != null){			
			result = "success";
		}else if(capthca1.equals(rCap) && user == null){
			result = "nouser";
		}else if(!capthca1.equals(rCap) && user != null){
			result = "nocap";
		}else if(!capthca1.equals(rCap) && user == null){
			result = "noall";
		}
		return result;
	}
	
	
}