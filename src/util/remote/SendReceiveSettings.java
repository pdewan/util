package util.remote;

public interface SendReceiveSettings {
	public boolean isWaitForReceive() ;
	public void setWaitForReceive(boolean waitForReceive) ;
	public boolean isWaitForSend() ;
	public void setWaitForSend(boolean waitForSend) ;
	public boolean isDelaySend() ;
	public void setDelaySend(boolean delaySend) ;
	public int getSendDelay() ;
	public void setSendDelay(int sendDelay) ;
	public boolean isDelayReceive() ;
	public void setDelayReceive(boolean delaySend) ;
	public int getReceiveDelay() ;
	public void setReceiveDelay(int sendDelay) ;
	void maybeDelaySend();
	void maybeDelayReceive();


}