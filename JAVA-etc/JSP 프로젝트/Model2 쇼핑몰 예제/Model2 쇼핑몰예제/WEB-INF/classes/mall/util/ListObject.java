package mall.util;
import java.util.*;
import java.io.*;
/**
 * ����Ʈ ó�� Ŭ���� 
 */
public class ListObject implements Serializable {		
	private Vector list = null;	
	
	/** ListObject�� ������
	 */
	public ListObject()	{
		list = new Vector();
	}		
	
	/** ListObject�� ������ �ε����� ��ü�� ��ȸ�Ѵ�.
	 * @param index             int
	 * @return Object ��ü
	 */
	public Object get(int index) {
	    Object o = null;
		try	{
			o = list.get(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	    return o;   
	}	

	/** ListObject�� �������� ��ü �߰�
	 * @param element			Object
	 */
	public void add(Object o) {	    	   
	    list.add(o);
	}
	
	/** ListObject�� ������ ��ġ�� ��ü �߰�
	 * @param index				int
	 * @param element			Object
	 */
	public void add(int index, Object o) {	 
		try	{
			list.add(index, o);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	}
	
	/** ListObject�� �ش� �ε����� �����͸� ����
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

	/** ListObject���� ������ �ε����� ��ü�� ����
	 * @param index				int
	 */
	public void remove(int index) {    
	    try {
			list.remove(index);
		}
		catch(ArrayIndexOutOfBoundsException aiobe) {}
	}	
	
	/** ListObject�� ���̸� ��ȸ�Ѵ�.
	 * @return ListObject�� ����
	 */
	public int size() {
	    return list.size();	    
	}		
}
