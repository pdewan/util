package util.models;

public interface ArrayBasedBoundedBuffer <ElementType>{
	public String getName() ;

	public void  put(ElementType element) ;
	
//	public void put(int anIndex, ElementType anElement);

	public void  put(ElementType element, long timeOut) ;

	public  ElementType get() ;

	public  ElementType get(long timeOut) ;

}
