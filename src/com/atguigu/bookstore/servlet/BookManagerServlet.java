package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 处理管理员图书相关请求
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();   
    /**
     * 处理管理员分页的请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findPage(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//1、获取请求参数
    	String pageNumber = request.getParameter("pageNumber");
    	//定义每页显示的记录条数
    	int size = 4;
    	//2、调用service处理业务
    	Page<Book> page = service.findPage(pageNumber, size);
    	//动态获取分页需要使用的路径    /项目名/BookManagerServlet?type=findPage
    	//给page类添加新属性，用来携带分页需要的路径参数
    	//将path属性值设置给page对象
    	page.setPath(WebUtils.getPath(request));
    	request.setAttribute("page", page);
    	
    	
    	//3、转发到book_manager.jsp页面显示分页数据
    	request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    	
    	
    	
    }
    
    /**
     * 处理修改图书的请求方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取请求参数中的ref参数：代表修改之前的页面地址
    	String ref = request.getParameter("ref");
    	//1、获取请求参数并封装为book对象 [提交参数的name值和 javabean的属性名一样]
    	Book book = WebUtils.param2Bean(new Book(), request);
    	//2、调用service修改图书
    	boolean b = service.updateBook(book);
    	//3、给用户响应   跳转到显示图书的页面
    	response.sendRedirect(ref);
    }
    /**
     * 
     * 处理查询指定图书请求方法
     */
    protected void findBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取修改之前的referer地址
    	String referer = request.getHeader("referer");
    	//设置到域中共享
    	request.setAttribute("ref", referer);
    	//1、获取图书id
    	String bookId = request.getParameter("bookId");
    	//2、调用业务层处理业务
    	Book book = service.findBook(bookId);
    	//将book设置到域中
    	request.setAttribute("book", book);
    	//3、给用户响应 到book_edit.jsp页面中显示要修改的图书数据
    	request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    	
    }
    
    /**
     * 处理添加图书的请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//如果提交的请求参数 name属性值和javabean的属性名一样，可以使用BeanUtils.jar自动将数据封装为对象
    	//1、必须有数据源[map.put("title","tushubiaoti")]2、空的javabean[new Book()] 
    	//使用BeanUtils时需要导入两个jar包：BeanUtils.jar和依赖包  logging.jar
    	//1、获取请求参数       
    	//获取请求参数的map集合
    	Book book = WebUtils.param2Bean(new Book(), request);
    	//2、调用service处理保存的业务
    	boolean b = service.addBook(book);
    	//3、根据处理结果给用户响应
    	response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=findPage");
    	
    }
    
    
    
    /**
     * 处理删除图书的请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//1、获取删除图书的id   请求参数
    		//request.getAttribute("bookId");获取request域中设置的属性值
    	String bookId = request.getParameter("bookId");
    	//2、调用业务层处理业务
    	boolean b = service.deleteBook(bookId);
    	//3、给用户响应    跳转回book_manager.jsp
    	//如果直接重定向，没有数据显示  response.sendRedirect(request.getContextPath()+"/pages/manager/book_manager.jsp");
    	//仍然需要先查询数据然后再到book_manager页面显示
    	//referer:  访问当前的servlet是从哪个页面跳转过来的  地址
    	//删除后跳转回删除之前的页面
    	String referer = request.getHeader("referer");//其实就是http://localhost:8080/BookStore_EG04/BookManagerServlet?type=findAllBooks
    	//由于referer地址是一个完整的url地址，必须使用重定向
    	response.sendRedirect(referer);
    	
    }
    
    
	/**
	 * 查询所有图书的请求方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用service处理业务
		List<Book> list = service.findAllBooks();
		//将集合存到request 域中共享
		request.setAttribute("list", list);
		//转发到管理员图书集合显示页面 /pages/manager/book_manager.jsp
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		//在book_manager页面中取出集合遍历显示
	}

	

}
