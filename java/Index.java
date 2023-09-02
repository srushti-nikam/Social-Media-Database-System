
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Scanner;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/SocialMedia";
	String USER = "root";
	String PASS = "admin@12345";

	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement pstmt = null;
	String query;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String user_EmailId=request.getParameter("EmailId");
		String user_Name=request.getParameter("Name");
		String user_DOB = request.getParameter("DOB");
		String user_EduQualifications=request.getParameter("EduQualifications");
		String user_CurrentInstitution=request.getParameter("CurrentInstitutionstNo");
		String user_WorkingDetail=request.getParameter("WorkingDetail");
		String user_COR=request.getParameter("COR");
		String user_Password=request.getParameter("Password");
		RequestDispatcher dispatcher =null;
		
		try{
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Enter your details: ");
			Scanner sc = new Scanner(System.in);
			query = "insert into user_login(EmailId,Name,DOB,EduQualifications,CurrentInstitution,WorkingDetail,COR,Password) values (?,?,?,?,?,?,?,?)";		
			pstmt = conn.prepareStatement(query);

            dispatcher=request.getRequestDispatcher("Login.jsp");
			pstmt.setString(1,user_EmailId );
			pstmt.setString(2,user_Name );
			pstmt.setString(3,user_DOB);
			pstmt.setString(4,user_EduQualifications );
			pstmt.setString(5,user_CurrentInstitution);
			pstmt.setString(6,user_WorkingDetail );
			pstmt.setString(7,user_COR );
			pstmt.setString(8,user_Password);
			
			
			
			int i1=pstmt.executeUpdate();
			if(i1>0) {
				System.out.println("record added Successfully");
			}
			else {
				System.out.println("UnSuccessfull");
			}
			response.sendRedirect("Login.jsp");
		}catch(Exception e) {
			out.print(e);
		}
	}

}
