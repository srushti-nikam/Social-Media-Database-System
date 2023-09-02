import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/SocialMedia";
	String USER = "root";
	String PASS = "admin@12345";

	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement pstmt = null;
	String query;
    public Login() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String user_EmailId=request.getParameter("EmailId");
		String user_Password=request.getParameter("Password");

		try{
		
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Enter your details: ");
			Scanner sc = new Scanner(System.in);
			String query20= "select Password from User_Login where EmailId = ?;";
			pstmt = conn.prepareStatement(query);
			int Id1 = 0;

			pstmt.setString(1,user_EmailId );
			pstmt.setString(8,user_Password);
			
			
			String query201= "select Password from User_Login where EmailId = ?;";

			pstmt = conn.prepareStatement(query201);

			pstmt.setString(1, user_EmailId);

			ResultSet rs110 = pstmt.executeQuery();
			String pw = null;
			while(rs110.next())

			{

				pw  = rs110.getString("Password");

			}
			rs110.close();

			if(pw.equals(user_Password))

			{	
				String query21= "select UserId from User_Login where EmailId = ? and password = ?";
				pstmt = conn.prepareStatement(query21);
				pstmt.setString(1,user_EmailId);
				pstmt.setString(2,user_Password);
				ResultSet rs111 = pstmt.executeQuery();

				while(rs111.next()){

					Id1  = rs111.getInt("UserId");
				} 
				request.setAttribute("UserId1", Id1);
				rs111.close();

				RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);


			}
			else

			{

				System.out.println("Something went wrong ! You entered wrong EmailId or password. Try again !");

				int k= 1;

			}


			
			int i1=pstmt.executeUpdate();
			if(i1>0) {
				System.out.println("Logged in successfully");
			}
			else {
				System.out.println("UnSuccessfull");
			}
		}catch(Exception e) {
			out.print(e);
		}
	}

	}

