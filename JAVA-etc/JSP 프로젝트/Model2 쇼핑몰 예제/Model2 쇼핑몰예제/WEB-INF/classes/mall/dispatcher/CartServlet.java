package mall.dispatcher;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mall.user.*;
import mall.order.*;
import mall.product.*;
import mall.util.*;
/**
 * 장바구니 처리 서블릿 
 */
public class CartServlet extends HttpServlet {
    /** 장바구니 서블릿 init 메소드
     * @param config        ServletConfig     	
     */
	public void init(ServletConfig conf) throws ServletException  {
        super.init(conf);
    }
    
    /** 장바구니 서블릿 service 메소드
     * @param req           HttpServletRequest   
     * @param res           HttpServletResponse 
     */
	public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
		HttpSession session = null;
		HashMap cart = null;
		UserBean user = null;
		String cmd = "";
        String url = "";
		try {
            //세션객체 생성
			session = req.getSession(false);
			if (session == null) {
                res.sendRedirect(req.getContextPath() + "/MainServlet?cmd=LOGIN");
				return;
            } 
                  
            cart = (HashMap)session.getAttribute("cart");        
            user = (UserBean)session.getAttribute("user");
                   
            if (user == null) {
                user = new UserBean();
            }  
            
            if (user.getUserId() == 0) {
                res.sendRedirect(req.getContextPath() + "/MainServlet?cmd=LOGIN");
				return;
            }     
            
            //HTTP request command parameter로 값을 얻음
			cmd = req.getParameter("cmd").toUpperCase();
			//장바구니 화면
            if (cmd.equals("CART")) {
				logger("CART command called");
				setCartPage(req, session);
                url = "/orders/cart.jsp";
                forward(req, res, url); 
            }
			//장바구니에서 수량변경 또는 삭제하는 경우
			else if (cmd.equals("UPDATE")) {
				//수량을 변경할 경우
				if (req.getParameterValues("qty")!= null) {
					String [] quantity = req.getParameterValues("qty");	
					Collection col = cart.values();	
					ItemBean item;
					int i = 0;
					for(Iterator it = col.iterator(); it.hasNext(); i++) {	
						item = (ItemBean)it.next();		
						item.setQty(Integer.parseInt(quantity[i]));
					}	
				}
				
				//장바구니에서 상품을 삭제할 경우
				if(req.getParameterValues("cartitems")!=null) {
					String [] cartitems = req.getParameterValues("cartitems");
					int size = cartitems.length ;		
					for(int i=0; i < size; i++) {		
						if (cart.containsKey(cartitems[i])) {
							cart.remove(cartitems[i]);	
						}		
					}	
				}
				session.setAttribute("cart", cart);     
				setCartPage(req, session);
                url = "/orders/cart.jsp";                        
                forward(req, res, url);      
			} 
			//장바구니에 상품을 추가하는 경우
			else if (cmd.equals("ADD")) {                        
				if (cart == null) {
					cart = new HashMap();
				}                      
				
				String [] pid;
				int size = 0;
				int index = 0;
				String spid = req.getParameter("product_id");
				StringTokenizer st = new StringTokenizer(spid, "-");
				
				// 넘어온 상품 리스트를 저장할 배열의 크기를 구한다.
				while(st.hasMoreTokens()) {
					st.nextToken();
					size += 1;
				}
			
				// 상품들을 pid String 배열에 저장한다.
				pid = new String[size];
				st = new StringTokenizer(spid, "-");
				while(st.hasMoreTokens()) {
					pid[index] = st.nextToken();
					index += 1;
				}
				
				//장바구니의 상품들을 HashMap에 저장
				ItemBean item;
				for(int i=0; i<size; i++) {
					item = new ItemBean();
					item.setProductId(Integer.parseInt(pid[i]));		
					item.setQty(1);		
					if (!cart.containsKey(pid[i])) {
						cart.put(pid[i], item);	
					}	
				} 	        
				session.setAttribute("cart", cart);                    		    
				
				setCartPage(req, session);
                url = "/orders/cart.jsp";                        
                forward(req, res, url);      
               
            } 
            //주문신청인경우
            else if (cmd.equals("CHECKOUT")) {                
                if (cart.size() > 0) {
			        double total_amount = 0;
			        int total_count = 0;
        				
			        Collection col = cart.values();		
			        OrderBean order = new OrderBean();
			        ProductBean pb = null;
			        ItemBean item = null;			
			        //주문 총금액 구하기
			        for(Iterator it = col.iterator(); it.hasNext(); ) {	
				        item = (ItemBean)it.next();		
				        pb = new ProductBean();
				        pb.init(item.getProductId());
				        total_amount = total_amount +(pb.getPrice() * item.getQty());
			        }		
			        //OrderBean 저장
			        order.setUserId(user.getUserId());
			        order.setOrderTotal(total_amount);
			        order.setStatus(OrderBean.NOT_COMPLETED);
			        order.add();
    			    
			        req.setAttribute("order", order);
        			
			        //Item들 저장
			        for(Iterator it = col.iterator(); it.hasNext(); ) {	
				        item = (ItemBean)it.next();		
				        item.setOrderId(order.getOrderId());
				        item.add();			
			        }	
			        cart.clear();			    
		        }		                         
                url = "/orders/checkout.jsp";
                forward(req, res, url); 
            }
        }
        catch(Exception e) {
            req.setAttribute("javax.servlet.jsp.jspException", e);
            forward(req, res, req.getContextPath() + "/error.jsp"); 			
        }
    }
    
	private void setCartPage(HttpServletRequest req, HttpSession session) 
		throws Exception {
		ListObject lo = null;		
		HashMap cart = (HashMap)session.getAttribute("cart");	
		if (cart == null) {
			cart = new HashMap();
		}
		Collection col = cart.values();			
		ProductBean pb;
		ItemBean item;
		int i = 0;
		ItemBean [] items = new ItemBean[cart.size()];
		ProductBean [] products = new ProductBean[cart.size()];
		for(Iterator it = col.iterator(); it.hasNext(); i++) {	
			item = (ItemBean)it.next();		
			pb = new ProductBean();
			pb.init(item.getProductId());			
			items[i] = item;
			products[i] = pb;
		}
		lo = new ListObject();
		lo.add(items);
		req.setAttribute("loItem", lo);
		lo = new ListObject();
		lo.add(products);
		req.setAttribute("loPb", lo);
    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String url)
		throws ServletException, IOException {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);           
        rd.forward(req, res);
    }

	private void logger(String msg) {
        System.out.println(msg);
    }        
        
}

