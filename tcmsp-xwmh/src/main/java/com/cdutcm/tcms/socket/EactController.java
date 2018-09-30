package com.cdutcm.tcms.socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/eact")
public class EactController {

	@ResponseBody
	@RequestMapping("/start")
	public String start(){
		System.out.println("--------1----------");
		EacClient client = new EacClient();
		try {
			client.connect("10.126.6.28", 10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------2----------");
		return null;
	}
}
