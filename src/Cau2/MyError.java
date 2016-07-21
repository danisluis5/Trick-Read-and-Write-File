package Cau2;

@SuppressWarnings("all")
public class MyError {
	private String msgx;
	private String msgy;
	public MyError(String msgx, String msgy) {
		super();
		this.msgx = msgx;
		this.msgy = msgy;
	}
	public MyError() {
		super();
	}
	public String getMsgx() {
		return msgx;
	}
	public void setMsgx(String msgx) {
		this.msgx = msgx;
	}
	public String getMsgy() {
		return msgy;
	}
	public void setMsgy(String msgy) {
		this.msgy = msgy;
	}
}
