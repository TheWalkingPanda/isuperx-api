package com.isuperx.controller.api.v1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isuperx.domain.User;
import com.isuperx.service.interfaces.IUserSV;
import com.isuperx.util.Return;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
	private static Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	private IUserSV userSV;
	
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public Return getUser(@PathVariable("userId") long userId) {
		Return ret = new Return();
		
		try {
			User user = userSV.getUserById(userId);
			if(user!=null){
				ret.setSuccess(true);
				ret.setStatusCode(HttpStatus.OK);
				ret.setData(user);
			}else{
				ret.setSuccess(true);
				ret.setStatusCode(HttpStatus.OK);
				ret.setMessage("说");
				ret.setData(user);
			}
		} catch (Exception e) {
			log.error(e);
			ret.setSuccess(false);
			ret.setStatusCode(HttpStatus.OK);
			ret.setData(e);
		}
		return ret;
	}
}
