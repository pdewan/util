package util.models;

import java.util.concurrent.BlockingQueue;

public interface BoundedBuffer <ElementType> extends BlockingQueue<ElementType>{
	public String getName() ;

	public void  put(ElementType element) throws InterruptedException ;
	
//	public void put(int anIndex, ElementType anElement);

//	public void  put(ElementType element, long timeOut) throws InterruptedException;

	public  ElementType get() throws InterruptedException ;

//	public  ElementType get(long timeOut) throws InterruptedException;

}
