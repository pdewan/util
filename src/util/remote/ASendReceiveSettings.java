package util.remote;

import util.annotations.Column;
import util.annotations.Row;
import util.annotations.Visible;
import util.misc.ClearanceManagerFactory;
import util.misc.ThreadSupport;

public class ASendReceiveSettings implements SendReceiveSettings {
	boolean waitForSend;
	boolean waitForReceive;
	
//	boolean delaySend;
	int sendDelay;
	
//	boolean delayReceive;
	int receiveDelay;
	@Visible(false)
	public boolean isDelayReceive() {
		return sendDelay != 0;
	}
//	public void setDelayReceive(boolean delayReceive) {
//		this.delayReceive = delayReceive;
//	}
//	@Row(0)
	public int getReceiveDelay() {
		return receiveDelay;
	}
	public void setReceiveDelay(int receiveDelay) {
		this.receiveDelay = receiveDelay;
	}
//	@Row(2)
//	@Column(1)
	public boolean isWaitForReceive() {
		return waitForReceive;
	}
	public void setWaitForReceive(boolean waitForReceive) {
		this.waitForReceive = waitForReceive;
	}
//	@Row(2)
//	@Column(0)
	public boolean isWaitForSend() {
		return waitForSend;
	}
	public void setWaitForSend(boolean waitForSend) {
		this.waitForSend = waitForSend;
	}
	@Visible(false)
	public boolean isDelaySend() {
		return sendDelay != 0;
	}
//	public void setDelaySend(boolean delaySend) {
//		this.delaySend = delaySend;
//	}
//	@Row(1)
	public int getSendDelay() {
		return sendDelay;
	}
	public void setSendDelay(int sendDelay) {
		this.sendDelay = sendDelay;
	}
	@Override
	public  boolean maybeDelaySend() {
		if (SendReceiveSettingsFactory.getOrCreateSingleton().isWaitForSend()) {
			return ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
			// poor man's network delay simulator, need a elay thread in general
		} else if (SendReceiveSettingsFactory.getOrCreateSingleton().isDelaySend()) {
			ThreadSupport.sleep((SendReceiveSettingsFactory.getOrCreateSingleton().getSendDelay()));
			return true;
		}
		return false;
	}
	@Override
	public  boolean maybeDelayReceive() {
		if (SendReceiveSettingsFactory.getOrCreateSingleton().isWaitForReceive()) {
			return ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
		// poor man's network delay simulator, need a elay thread in general
		} else if (SendReceiveSettingsFactory.getOrCreateSingleton().isDelayReceive()) {
			ThreadSupport.sleep((SendReceiveSettingsFactory.getOrCreateSingleton().getReceiveDelay()));
			return true;
		}
		return false;
	}

}
