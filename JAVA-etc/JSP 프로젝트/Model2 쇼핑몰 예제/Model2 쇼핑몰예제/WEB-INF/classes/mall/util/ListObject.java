package mall.util;
import java.util.*;
import java.io.*;
/**
 * 리스트 처리 클래스 
 */
public class ListObject implements Serializable {		
	private Vector list = null;	
	
	/** ListObject의 생성자
	 */
	public ListObject()	{
		list = new Vector();
	}		
	
	/** ListObject의 지정된 인덱스의 객체를 조회한다.
	 * @param index             int
	 * @return Object 객체
	 */
	public Object get(int index) {
	    Object o = null;
		try	{
			o = list.get(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	    return o;   
	}	

	/** ListObject의 마지막에 객체 추가
	 * @param element			Object
	 */
	public void add(Object o) {	    	   
	    list.add(o);
	}
	
	/** ListObject의 지정된 위치에 객체 추가
	 * @param index				int
	 * @param element			Object
	 */
	public void add(int index, Object o) {	 
		try	{
			list.add(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	}
	
	/** ListObject의 해당 인덱스에 데이터를 저장
	 * @param index				int
	 * @param element			Object
	 */
	public void set(int index, Object o) {
		Object tmp = null;		
		try	{
			tmp = list.set(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	}

	/** ListObject에서 지정된 인덱스의 객체를 제거
	 * @param index				int
	 */
	public void remove(int index) {    
	    try {
			list.remove(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	}	
	
	/** ListObject의 길이를 조회한다.
	 * @return ListObject의 길이
	 */
	public int size() {
	    return list.size();	    
	}		
}
