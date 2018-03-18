package com.marsyoung.learning.web.ssb.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

/**
 * @author zhiyuma
 * 
 *         Action实现方式2：
 * 
 *         实现Action接口。
 */
@Controller
@Scope("prototype")
public class DemoAction2 implements Action, Preparable {

	String resp;

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public String execute() throws Exception {
		resp = "success";
		return SUCCESS;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

}
