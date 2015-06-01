package com.marsyoung.learning.web.ssb.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

/**
 * @author zhiyuma 
 * 
 * Action实现方式2： 
 * 
 * 实现Action接口。
 */
@Controller
public class DemoAction2 implements Action, Preparable {

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public String execute() throws Exception {
		return null;
	}

}
