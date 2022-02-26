package com.lijian.design_pattern.structure.brige;

public class SevereNotification extends Notification {
	public SevereNotification(MsgSender msgSender) {
		super(msgSender);
	}
	
	@Override
	public void notify(String message) {
		msgSender.send(message);
	}
}