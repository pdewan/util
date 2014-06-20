package util.models;

public interface BoundedBuffer <ElementType>{
	public String getName() ;

	public void  put(ElementType element) ;

	public void  put(ElementType element, long timeOut) ;

	public  ElementType get() ;

	public  ElementType get(long timeOut) ;

}
