package util.session;

import java.util.List;

public interface DelayManager {

	public abstract int getMinimumDelayToServer();

	public abstract void setMinimumDelayToServer(int theDelay);

	public abstract int getDelayVariation();

	public abstract void setDelayVariation(int theDelay);

	public abstract long calculateDelay(long messageTime);

	public int getMinimumDelayToPeer(String thePeer);

	public void setMinimumDelayToPeer(String thePeer, int theDelay);

	public void refreshClients();

	public void refreshAndSortClients();

	public List<UserDelayRecord> getSortedClients();

}