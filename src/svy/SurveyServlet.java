package svy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SurveyServlet
 */
/* @WebServlet("*.sv") */
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServletContext context;
	String driver;
	String url;
	String user;
	String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		context = config.getServletContext();
		
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url");
		user = config.getInitParameter("user");
		password = config.getInitParameter("password");
		
		System.out.println("driver:"+driver);
		System.out.println("url:"+url);
		System.out.println("user:"+user);
		System.out.println("password:"+password);
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		surveyDAO sdao = surveyDAO.getInstance(driver,url,user,password);
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int len = contextPath.length();
		String command = uri.substring(len);
		System.out.println(command);
		String viewPage = "";
		
		
		if(command.contentEquals("/insert.sv")) {
			String name = request.getParameter("name");
			String company = request.getParameter("company");
			String email = request.getParameter("email");
			String satisfaction = request.getParameter("satisfaction");
			String[] part_imsi = request.getParameterValues("part");
			String part = "";
			if(part_imsi == null) {
				part = "선택한 관심 분야가 없습니다.";
			}else {
				for(int i = 0; i<part_imsi.length;i++) {
					part += part_imsi[i];
					if(i != part_imsi.length-1) part += ",";
				}
			}
			String howto = request.getParameter("howto");
			String agree_imsi = request.getParameter("agree");
			int agree = 0;
			if(agree_imsi == null) {
				agree = 0;
			}else if(agree_imsi.equals("1")) {
				agree = 1;
			}
			
			surveyDTO survey = new surveyDTO(0,name, company, email, satisfaction, part, howto, agree);
			boolean send = (boolean)context.getAttribute("send");
			if(!send) {
			sdao.insertSvy(survey);
			context.setAttribute("send", true);
			}
			viewPage = "/list.sv";
		}
		else if(command.contentEquals("/list.sv")) {
			ArrayList<surveyDTO> lists = sdao.surveyLists();
			request.setAttribute("lists", lists);
			viewPage ="surveyResult.jsp";
		}
		else if(command.contentEquals("/updateForm.sv")) {
			String no = request.getParameter("no");
			surveyDTO sdto = sdao.oneGetSurvey(no);
			request.setAttribute("sdto", sdto);
			
			viewPage ="surveyUpdate.jsp";
		}
		else if(command.contentEquals("/update.sv")) {
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String company = request.getParameter("company");
			String email = request.getParameter("email");
			String satisfaction = request.getParameter("satisfaction");
			String[] part_imsi = request.getParameterValues("part");
			String part = "";
			if(part_imsi == null) {
				part = "선택한 관심 분야가 없습니다.";
			}else {
				for(int i = 0; i<part_imsi.length;i++) {
					part += part_imsi[i];
					if(i != part_imsi.length-1) part += ",";
				}
			}
			String howto = request.getParameter("howto");
			String agree_imsi = request.getParameter("agree");
			System.out.println(agree_imsi);
			int agree = 0;
			if(agree_imsi == null) {
				agree = 0;
			}else if(agree_imsi.equals("1")) {
				agree = 1;
			}
			
			surveyDTO survey = new surveyDTO(no,name, company, email, satisfaction, part, howto, agree);
			sdao.updateSurvey(survey);
			
			viewPage = "/list.sv";
		}
		else if(command.contentEquals("/delete.sv")) {
			String no = request.getParameter("no");
			sdao.deleteSurvey(no); 
			viewPage = "/list.sv";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}
}
