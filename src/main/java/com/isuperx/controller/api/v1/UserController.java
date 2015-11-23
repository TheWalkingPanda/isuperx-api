package com.isuperx.controller.api.v1;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isuperx.domain.User;
import com.isuperx.service.interfaces.IUserSV;
import com.isuperx.util.Return;

@Controller
@RequestMapping("/api/v1")
public class UserController {
	private static Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	private IUserSV userSV;
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	@ResponseBody
	public Return addUser(HttpServletRequest request){
		Return ret = new Return();
		try {
			Map<String, String[]> paramMap = request.getParameterMap();
			Map<String, String> newUserMap = new HashMap<String, String>();
			for(String key : paramMap.keySet()){
				String value = paramMap.get(key)[0];
				newUserMap.put(key, value);
			}
			System.out.println(newUserMap.toString());
			String userJsonObj = request.getParameter("a");
			
			ObjectMapper objectMapper = new ObjectMapper();
			User newUser = objectMapper.readValue(userJsonObj, User.class);
			
			long newUserId = userSV.saveUser(newUser);
			
			ret.setSuccess(true);
			ret.setData(newUser);
		} catch (Exception e) {
			log.error(e);
			ret.setSuccess(false);
			ret.setMessage("请求异常："+e.toString());
		}
		return ret;
	}
	
	@RequestMapping(value="/users/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public Return getUser(@PathVariable("userId") long userId) {
		Return ret = new Return();
		try {
			User user = userSV.getUserById(userId);
			ret.setSuccess(true);
			
			if(user!=null){
				ret.setData(user);
			}else{
				ret.setMessage("user[id="+userId+"]不存在！");
			}
		} catch (Exception e) {
			log.error(e);
			ret.setSuccess(false);
			ret.setMessage("请求异常："+e.toString());
		}
		return ret;
	}
}
