package com.lijian.design_pattern.structure.brige;

import java.util.List;

public class TelephoneMsgSender implements MsgSender {
	private List<String> telephones;
	
	public TelephoneMsgSender(List<String> telephones) {
		this.telephones = telephones;
	}
	
	@Override
	public void send(String message) {
		//...
	}
}