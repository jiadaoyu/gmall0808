package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.druid.support.http.stat.WebURIStat;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 统一处理用户的请求
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 处理检查用户名是否可用的请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean b = service.checkUsername(username);
		if(b) {
			//用户名可用 0
			response.getWriter().write("0");
		}else {
			//用户名已存在  1
			response.getWriter().write("1");
			
		}
	}
	
	//注销的请求方法
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//注销
		request.getSession().invalidate();
		
		//重定向到首页让用户继续浏览
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	
	
	//Servlt中所有的业务都是调用service实现
    private UserService service = new UserServiceImpl();   
	//private UserDao  dao = new UserDaoImpl();
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		User loginUser = WebUtils.param2Bean(new User(), request);
		//2、调用其他类处理业务  service
		User user = service.login(loginUser);
		//dao.getUserByUsernameAndPassword(loginUser);
		//3、根据处理结果给用户响应
		if(user==null) {
			//在请求域中设置一个错误消息
			String errorMsg = "账号或密码错误，请重新输入!!!!!！";
			request.setAttribute("errorMsg", errorMsg);
			//如果失败转发到login页面让用户继续登录   转发的路径由服务器解析，基准地址到项目名
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//保持登录状态
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//如果成功，重定向到成功页面 重定向路径浏览器解析，基准地址到服务器
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
			//通过servlet可以写一个动态的页面(页面中可以使用变量的值)
			//response.getWriter().write("<h1>恭喜<span style='color:red; font-size:30px;'>"+user.getUsername()+"</span>登录成功</h1>");
		}
	}
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取验证码参数
		String clientCode = request.getParameter("code");
		//获取服务器存储的验证码
		HttpSession session = request.getSession();
		String serverCode = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
		//判断验证码是否正确
		if(clientCode!=null && clientCode.equals(serverCode)) {
			//验证码正确，可以注册
			User user = WebUtils.param2Bean(new User(), request);
			//2、调用其他类处理业务逻辑 
			//int i = dao.saveUser(user);
			//3、根据处理结果给用户响应(转发或重定向)
			if(service.regist(user)) {
				//注册成功 重定向到成功页面
				response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
				
			}else {
				//设置失败消息       尽量挑选最小域使用  pageContext不能    request转发可以使用 ， session不会
				request.setAttribute("errorMsg", "用户名已存在！");
				
				//注册失败 ， 转发到注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		}else {
			//验证码错误，转发到注册页面 并给出错误提示
			//设置失败消息       尽量挑选最小域使用  pageContext不能    request转发可以使用 ， session不会
			request.setAttribute("errorMsg", "验证码错误！");
			
			//注册失败 ， 转发到注册页面
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			
		}
		//销毁服务器中保存的验证码字符串
		session.removeAttribute("KAPTCHA_SESSION_KEY");
		
		
	}
	
}
