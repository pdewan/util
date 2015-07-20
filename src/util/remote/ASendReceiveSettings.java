package util.remote;

import util.misc.ClearanceManagerFactory;
import util.misc.ThreadSupport;

public class ASendReceiveSettings implements SendReceiveSettings {
	boolean waitForSend;
	boolean waitForReceive;
	
	boolean delaySend;
	int sendDelay;
	
	boolean delayReceive;
	int receiveDelay;

	public boolean isDelayReceive() {
		return delayReceive;
	}
	public void setDelayReceive(boolean delayReceive) {
		this.delayReceive = delayReceive;
	}
	public int getReceiveDelay() {
		return receiveDelay;
	}
	public void setReceiveDelay(int receiveDelay) {
		this.receiveDelay = receiveDelay;
	}
	
	public boolean isWaitForReceive() {
		return waitForReceive;
	}
	public void setWaitForReceive(boolean waitForReceive) {
		this.waitForReceive = waitForReceive;
	}
	public boolean isWaitForSend() {
		return waitForSend;
	}
	public void setWaitForSend(boolean waitForSend) {
		this.waitForSend = waitForSend;
	}
	public boolean isDelaySend() {
		return delaySend;
	}
	public void setDelaySend(boolean delaySend) {
		this.delaySend = delaySend;
	}
	public int getSendDelay() {
		return sendDelay;
	}
	public void setSendDelay(int sendDelay) {
		this.sendDelay = sendDelay;
	}
	@Override
	public  void maybeDelaySend() {
		if (SendReceiveSettingsFactory.getOrCreateSettings().isWaitForSend()) {
			ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
			// poor man's network delay simulator, need a elay thread in general
		} else if (SendReceiveSettingsFactory.getOrCreateSettings().isDelaySend()) {
			ThreadSupport.sleep((SendReceiveSettingsFactory.getOrCreateSettings().getSendDelay()));
		}
	}
	@Override
	public  void maybeDelayReceive() {
		if (SendReceiveSettingsFactory.getOrCreateSettings().isWaitForReceive()) {
			ClearanceManagerFactory.getOrCreateClearanceManager().waitForClearance();
		// poor man's network delay simulator, need a elay thread in general
		} else if (SendReceiveSettingsFactory.getOrCreateSettings().isDelayReceive()) {
			ThreadSupport.sleep((SendReceiveSettingsFactory.getOrCreateSettings().getReceiveDelay()));
		}
	}

}
