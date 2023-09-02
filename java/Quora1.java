
import java.text.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Quora1

{

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/SocialMedia?autoReconnect=true&useSSL=false";

	static final String USER = "root";

	static final String PASS = "admin@12345";



	public static void main(String[] args) throws IOException

	{

		Connection conn = null;

		PreparedStatement pstmt = null;

		String query;

		Scanner sc = new Scanner(System.in);

		Connection conn1 = null;

		PreparedStatement pstmt1 = null;



		//Inserting a user

		try

		{

			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			conn1 = DriverManager.getConnection(DB_URL, USER, PASS);

			String user_EmailId,user_Name,user_EduQualifications,user_CurrentInstitution,user_WorkingDetail,user_Password,user_COR;



			//Log in

			int k=0;

			int Id1 = 0;

			String EmailID2;



			do

			{

				System.out.println("Login : ");

				System.out.print("Enter EmailId : ");

				EmailID2 = sc.next();

				sc.nextLine();



				String query20= "select Password from User_Login where EmailId = ?;";

				pstmt = conn.prepareStatement(query20);

				pstmt.setString(1, EmailID2);

				ResultSet rs110 = pstmt.executeQuery();

				String pw = null;



				while(rs110.next())

				{

					pw = rs110.getString("Password");

				}

				rs110.close();



				System.out.print("Enter password : ");

				String password1 = sc.next();



				if(pw.equals(password1))

				{

					String query21= "select UserId from User_Login where EmailId = ? and password = ?";

					pstmt = conn.prepareStatement(query21);

					pstmt.setString(1, EmailID2);

					pstmt.setString(2,password1);

					ResultSet rs111 = pstmt.executeQuery();



					while(rs111.next())

					{

						Id1 = rs111.getInt("UserId");

					}

					rs111.close();



					String sql="select * from User_Login where EmailId=? and Password = ?";

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, EmailID2);

					pstmt.setString(2, password1);

					ResultSet rs1 = pstmt.executeQuery();

					int IDN = 0;



					while(rs1.next())

					{

						IDN = rs1.getInt("UserId");

						user_EmailId=rs1.getString("EmailId");

						user_Name=rs1.getString("Name");

						Date user_DOB=rs1.getDate("DOB");

						user_EduQualifications=rs1.getString("EduQualifications");

						user_CurrentInstitution=rs1.getString("CurrentInstitution");

						user_WorkingDetail=rs1.getString("WorkingDetail");

						user_COR=rs1.getString("COR");

						user_Password=rs1.getString("Password");





						System.out.println("EmailId : " + user_EmailId );

						System.out.println("Name : " + user_Name);

						System.out.println("DOB : " +user_DOB);

						System.out.println("Education Qualifications : " + user_EduQualifications );

						System.out.println("Current Institution : " + user_CurrentInstitution);

						System.out.println("Working Details : " + user_WorkingDetail );

						System.out.println("Country of Residence : " + user_COR);

					}

					rs1.close();

				}

				else

				{

					System.out.println("Something went wrong ! You entered wrong EmailId or password. Try again !");

					k= 1;

				}

			}while(k==1);





			//Procedures after log in/sign in

			int choose =0;

			int ch1 =0;



			do

			{

				System.out.println("\nEnter 1 to display details: ");

				System.out.println("Enter 2 to delete account: ");

				System.out.println("Enter 3 to update: ");

				System.out.println("Enter 4 to follow: ");

				System.out.println("Enter 5 to ask question: ");

				System.out.println("Enter 6 to find number of following users of the logined user: ");

				System.out.println("Enter 7 to find number of followers of a certain person : ");

				System.out.println("Enter 8 to count number of questions asked by user: ");

				System.out.println("Enter 9 to answer a certain question: ");

				System.out.println("Enter 10 to find answers of a particular question:");

				System.out.println("Enter 11 to like an answer: ");

				System.out.println("Enter 12 to comment on an answer: ");

				System.out.println("Enter 13 to find people with similar interests: ");

				System.out.println("Enter 14 to search for user: ");

				System.out.println("Enter 15 to count number of answers user has answered: ");

				System.out.println("Enter 16 to find the list of users from recently logined to oldest: ");

				System.out.print("Enter choice : ");

				ch1 = sc.nextInt();



				switch(ch1)

				{

				case 0:

					//To add interests in profile

					int i = 0 ;

					System.out.println("Add Interests :- ");



					String query1 = "insert into Interests(UserId,Interest) values (?,?)";

					pstmt1 = conn1.prepareStatement(query1);



					do

					{

						System.out.print("\nAdd : ");

						String S1 = sc.next();



						pstmt1.setInt(1, Id1);

						pstmt1.setString(2, S1);

						pstmt1.executeUpdate();



						System.out.println("Interest added successfully !");

						System.out.print("Do you want to add more Interests (1:Yes / 0: No) : ");

						i = sc.nextInt();

					} while(i != 0);

					break;



				case 1:

					//Displaying all users details

					query = "select * from User_Login";

					pstmt = conn.prepareStatement(query);

					ResultSet rs = pstmt.executeQuery(query);

					ResultSet rs1=null;

					int user_UserId = 0;



					while(rs.next())

					{

						user_UserId =rs.getInt("UserId");

						user_EmailId=rs.getString("EmailId");

						user_Name=rs.getString("Name");

						Date user_DOB=rs.getDate("DOB");

						user_EduQualifications=rs.getString("EduQualifications");

						user_CurrentInstitution=rs.getString("CurrentInstitution");

						user_WorkingDetail=rs.getString("WorkingDetail");

						user_COR=rs.getString("COR");

						user_Password=rs.getString("Password");



						String sqlI="select * from Interests where UserId = ?";

						pstmt1 = conn1.prepareStatement(sqlI);

						pstmt1.setInt(1, user_UserId);

						rs1 = pstmt1.executeQuery();

						String InterestsI = null;



						System.out.println("EmailId : " + user_EmailId );

						System.out.println(" Name : " + user_Name);

						System.out.println(" DOB : " +user_DOB);

						System.out.print("Interests : ");

						while(rs1.next())

						{

							InterestsI =rs1.getString("Interest");

							System.out.print(InterestsI + " ");

						}

						System.out.println("\nEducation Qualifications : " + user_EduQualifications );

						System.out.println("Current Institution : " + user_CurrentInstitution);

						System.out.println("Working Details : " + user_WorkingDetail );

						System.out.println("Country of Residence : " + user_COR);

					}

					rs1.close();

					rs.close();

					break;



				case 2:

					//Deleting a user detail

					query = "DELETE FROM User_Login WHERE EmailId=?";

					pstmt=conn.prepareStatement(query);

					pstmt.setString(1, EmailID2);

					int l =pstmt.executeUpdate();



					if(l>0)

					{

						System.out.println("Record deleted successfully !");

					}

					else

					{

						System.out.println("Record couldn't be deleted !");

					}

					break;



				case 3:

					//Updating a user detail

					System.out.println("Update :- ");



					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

					int ch21=0;



					do

					{

						System.out.println("Enter 1 to update EduQualification: ");

						System.out.println("Enter 2 to update Current Institution : ");

						System.out.println("Enter 3 to update Working Details : ");

						System.out.println("Enter 4 to update Country of Residence : ");

						System.out.println("Enter 5 to update Password : ");



						System.out.print("Enter your choice : ");

						ch21= sc.nextInt();



						switch(ch21)

						{

						case 1:

							// Update education qualifications

							System.out.print("Enter your Education Qualification to be updated : ");

							String user_EduQualification = reader.readLine();



							query = "UPDATE User_Login " +

	"SET EduQualifications = ? WHERE EmailId=?";

							pstmt = conn.prepareStatement(query);

							pstmt.setString(1, user_EduQualification);

							pstmt.setString(2, EmailID2);

							int i1= pstmt.executeUpdate();



							if(i1>0)

							{

								System.out.println("Record updated successfully !");

							}

							else

							{

								System.out.println("Couldn't update the record ! Try again.");

							}

							break;



						case 2:

							//Update current institution

							System.out.print("Enter your Current Institution to be updated : ");

							String user_currentInstitution = reader.readLine();



							query = "UPDATE User_Login " +

	"SET currentInstitution = ? WHERE EmailId=?";

							pstmt = conn.prepareStatement(query);

							pstmt.setString(1, user_currentInstitution);

							pstmt.setString(2, EmailID2);

							int i11= pstmt.executeUpdate();



							if(i11>0)

							{

								System.out.println("Record updated successfully !");

							}

							else

							{

								System.out.println("Couldn't update the record ! Try again.");

							}

							break;



						case 3:

							//Update working detail

							System.out.print("Enter your Working Detail to be updated : ");

							String user_WorkingDetails = reader.readLine();



							query = "UPDATE User_Login " +

	"SET WorkingDetail = ? WHERE EmailId=?";

							pstmt = conn.prepareStatement(query);

							pstmt.setString(1,user_WorkingDetails );

							pstmt.setString(2, EmailID2);

							int i2= pstmt.executeUpdate();



							if(i2>0)

							{

								System.out.println("Record updated successfully !");

							}

							else

							{

								System.out.println("Couldn't update the record ! Try again.");

							}

							break;



						case 4:

							//Update country of residence

							System.out.print("Enter your Country of Residence to be updated : ");

							String user_COR1 = reader.readLine();



							query = "UPDATE User_Login " +

	"SET COR = ? WHERE EmailId=?";

							pstmt = conn.prepareStatement(query);

							pstmt.setString(1,user_COR1 );

							pstmt.setString(2, EmailID2);

							int i3= pstmt.executeUpdate();



							if(i3>0)

							{

								System.out.println("Record updated successfully !");

							}

							else

							{

								System.out.println("Couldn't update the record ! Try again.");

							}

							break;



						case 5:

							//Update password

							System.out.print("Enter your Password to be updated : ");

							String user_password = sc.next();

							sc.nextLine();



							query = "UPDATE User_Login " +

									"SET Password = ? WHERE EmailId=?";

							pstmt = conn.prepareStatement(query);

							pstmt.setString(1,user_password );

							pstmt.setString(2, EmailID2);

							int i4= pstmt.executeUpdate();



							if(i4>0)

							{

								System.out.println("Record updated successfully !");

							}

							else

							{

								System.out.println("Couldn't update the record ! Try again.");

							}

							break;



						default:

							System.out.println("Wrong choice !");

							break;

						}

					}while(ch21!=0);

					break;



				case 4:

					//Following a other user

					query = "select UserId,Name,EduQualifications,CurrentInstitution,WorkingDetail from User_Login";

					pstmt = conn.prepareStatement(query);

					ResultSet rs11 = pstmt.executeQuery(query);

					int UserId11 = 0;



					while(rs11.next())

					{

						UserId11 =rs11.getInt("UserId");

						user_Name=rs11.getString("Name");

						user_EduQualifications=rs11.getString("EduQualifications");

						user_CurrentInstitution=rs11.getString("CurrentInstitution");

						user_WorkingDetail=rs11.getString("WorkingDetail");



						System.out.print("UserId : " + UserId11 );

						System.out.println(" Name : " + user_Name);

						System.out.println("Education Qualifications : " + user_EduQualifications );

						System.out.println("Current Institution : " + user_CurrentInstitution);

						System.out.println("Working Details : " + user_WorkingDetail );

					}

					rs11.close();



					System.out.print("Enter UserId of Person you want to follow : ");

					int UId = sc.nextInt();



					String query_0= "select UserId from User_Login ";

					pstmt = conn.prepareStatement(query_0);

					ResultSet rs_0 = pstmt.executeQuery();

					int ab = 0;

					int n =0;



					while(rs_0.next())

					{

						ab = rs_0.getInt("UserId");

						if(UId == ab) 

						{

							n = 1;

							break;

						}

					}

					rs_0.close();



					if(n ==1)

					{

						query = "insert into User_Follower(UserId,FollowingId) values (?,?)";

						pstmt = conn.prepareStatement(query);

						pstmt.setInt(1, Id1);

						pstmt.setInt(2, UId);

						pstmt.executeUpdate();



						System.out.println("You are now following the user !");

					}

					else

					{

						System.out.println("Following the user was unsuccessful !");

					}

					break;



				case 5:

					//Posting a question

					BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));



					System.out.print("Enter your question : ");

					String q1 = reader1.readLine();



					query = "insert into Questions(UserId,Question) values (?,?)";

					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Id1);

					pstmt.setString(2, q1);

					pstmt.executeUpdate();



					System.out.println("The Question is now posted !");

					break;



				case 6:

					//To display no of users a user is following

					query = "select count(*) from user_follower where UserId=? ";

					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Id1);

					ResultSet rs12 = pstmt.executeQuery();

					int Count = 0;



					while(rs12.next())

					{

						Count =rs12.getInt("count(*)");

						System.out.println("No of people you are following : "+Count);

					}

					rs12.close();

					break;



				case 7:

					//To display no users are following a user

					System.out.print("Enter EmailId : ");

					user_EmailId = sc.next();

					sc.nextLine();



					String query00= "select EmailId from User_Login ";

					pstmt = conn.prepareStatement(query00);

					ResultSet rs00 = pstmt.executeQuery();

					String abc = null;

					int m =0;



					while(rs00.next())

					{

						abc = rs00.getString("EmailId");

						if(user_EmailId.equals(abc))

						{

							m = 1;

							break;

						}

					}

					rs00.close();



					if (m ==1)

					{

						String query22= "select UserId from User_Login where EmailId = ? ";

						pstmt = conn.prepareStatement(query22);

						pstmt.setString(1, user_EmailId);

						ResultSet rs24 = pstmt.executeQuery();

						int Id11 = 0;



						if(rs24.next())

						{

							Id11 = rs24.getInt("UserId");

						}

						rs24.close();

						sc.nextLine();



						query = "select count(*) from user_follower where UserId=? ";

						pstmt = conn.prepareStatement(query);

						pstmt.setInt(1, Id11);

						ResultSet rs13 = pstmt.executeQuery();

						int Count1 = 0;



						while(rs13.next())

						{

							Count1 =rs13.getInt("count(*)");

							System.out.println("No of people who are following you : "+Count1);

						}

						rs13.close();

					}

					else

					{

						System.out.println("You have no followers !");

					}

					break;



				case 8:

					//To display no of questions posted by a user

					query = "select count(Question) from Questions where UserId=? ";

					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Id1);



					ResultSet rs14 = pstmt.executeQuery();

					int Count2 = 0;

					while(rs14.next()) {

						Count2 =rs14.getInt("count(Question)");

						System.out.println("No of questions : "+Count2);

					}

					rs14.close();

					break;



				case 9:

					//To post a answer

					System.out.print("Enter EmailId of the person whose questions you want to answer : ");

					String EmailId03 = sc.next();

					sc.nextLine();



					String query020= "select EmailId from User_Login ";

					pstmt = conn.prepareStatement(query020);

					ResultSet rs020 = pstmt.executeQuery();

					String abc3 = null;

					int m7 =0;



					while(rs020.next())

					{

						abc3 = rs020.getString("EmailId");

						if(EmailId03.equals(abc3))

						{

							m7 = 1;

							break;

						}

					}

					rs020.close();



					if(m7==1)

					{

						String query03= "select UserId from User_Login where EmailId = ? ";

						pstmt = conn.prepareStatement(query03);

						pstmt.setString(1, EmailId03);

						ResultSet rs03 = pstmt.executeQuery();

						int IdA = 0;



						while(rs03.next())

						{

							IdA = rs03.getInt("UserId");

						}

						rs03.close();



						query = "select QId,Question from Questions where UserId = ?";

						pstmt = conn.prepareStatement(query);

						pstmt.setInt(1, IdA);

						ResultSet rsQ = pstmt.executeQuery();

						String question = null;

						int QID = 0;



						System.out.println("QId\t\tQuestion");



						while(rsQ.next())

						{

							QID =rsQ.getInt("QId");

							question =rsQ.getString("Question");



							System.out.println(QID + "\t\t" + question);

						}

						rsQ.close();



						System.out.print("Enter QId of Question you want to Answer : ");

						QID = sc.nextInt();

						BufferedReader reader11 = new BufferedReader(new InputStreamReader(System.in));



						String query000= "select QId from Questions where UserId =? ";

						pstmt = conn.prepareStatement(query000);

						pstmt.setInt(1,IdA );

						ResultSet rs000 = pstmt.executeQuery();

						int qi = 0;

						int m1 =0;



						while(rs000.next())

						{

							qi = rs000.getInt("QId");

							if(QID == qi)

							{

								m1 = 1;

								break;

							}

						}

						rs000.close();



						if (m1 ==1)

						{

							System.out.print("Enter your Answer : ");

							String A1 = reader11.readLine();



							query = "insert into Answers(QId,Answer,UserId) values (?,?,?)";

							pstmt = conn.prepareStatement(query);

							pstmt.setInt(1,QID);

							pstmt.setString(2,A1);

							pstmt.setInt(3,Id1);

							pstmt.executeUpdate();



							System.out.println("Answer to the question is posted Successfully !");

						}

						else

						{

							System.out.println("Answer to the question couldn't be posted !");

						}

					}

					else

					{

						System.out.println("That user has not posted any questions yet !");

					}

					break;



				case 10:

					//To find answer to a question posted by you

					query = "select QID,Question from Questions where UserId = ?";

					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Id1);

					ResultSet rsQ1 = pstmt.executeQuery();

					String Question1 = null;

					int QID1 = 0;



					System.out.println("QId\t\tQuestion");



					while(rsQ1.next())

					{

						QID1 =rsQ1.getInt("QID");

						Question1 =rsQ1.getString("Question");



						System.out.println(QID1 + "\t\t" + Question1);

					}

					rsQ1.close();



					System.out.print("Enter QId of the Question Whose Answers you want to find : ");

					int QID2 = sc.nextInt();



					String query0_0= "select QId from Questions where UserId =?";

					pstmt = conn.prepareStatement(query0_0);

					pstmt.setInt(1,Id1);

					ResultSet rs0_0 = pstmt.executeQuery();

					int ab1 = 0;

					int n1 =0;



					while(rs0_0.next())

					{

						ab1 = rs0_0.getInt("QId");

						if(QID2 == ab1)

						{

							n1 = 1;

							break;

						}

					}

					rs0_0.close();



					if(n1 ==1)

					{

						query = "select Answer from Answers where QId = ?";

						pstmt = conn.prepareStatement(query);

						pstmt.setInt(1,QID1 );

						ResultSet rsQ2 = pstmt.executeQuery();

						String Answer2 = null;



						System.out.print("Answers : ");



						int i1 = 1;



						while(rsQ2.next())

						{

							Answer2=rsQ2.getString("Answer");



							System.out.print(i1 + ".");

							System.out.println(Answer2);

							i1++;

						}
						System.out.println("");
						rsQ2.close();

					}

					else

					{

						System.out.println("Answers couldn't be found !");

					}

					break;



				case 11:

						//Liking a answer
					System.out.print("Enter EmailId of the person whose Answers you want to Like : ");
					String EmailId031 = sc.next();
					sc.nextLine();

					String query031= "select UserId from User_Login where EmailId = ? ";

					pstmt = conn.prepareStatement(query031);
					pstmt.setString(1, EmailId031);
					ResultSet rs031 = pstmt.executeQuery();
					int IdA1 = 0;

					while(rs031.next())
					{
						IdA1  = rs031.getInt("UserId");
					}
					rs031.close();

					query = "select AnsId , Answer from Answers where UserId = ?";

					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, IdA1);
					ResultSet rsQ111 = pstmt.executeQuery();
					String Answer1 = null;
					int QID11 = 0;

					System.out.println("AnsId\t\tAnswers");

					while(rsQ111.next())
					{
						QID11 =rsQ111.getInt("AnsId");
						Answer1 =rsQ111.getString("Answer");

						System.out.println(QID11 + "\t\t" + Answer1);
					}
					rsQ111.close();

					System.out.print("Enter AnsId of Answer you want to Like : ");
					QID11 = sc.nextInt();
					System.out.print("Enter 1 to like");
					int l1=sc.nextInt();

					if(l1==1) 
					{
						query = "insert into Likes(AnsId, like1,UserId) values (?,?,?)";

						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1,QID11);
						pstmt.setInt(2,l1);
						pstmt.setInt(3,Id1);
						pstmt.executeUpdate();

						System.out.println("liked successfully!!");
					}
					else
					{
						System.out.println("not Liked !");
					}
					break;



				case 12:

					//To comment on an answer

					System.out.print("Enter EmailId of the person on whose answers you want to comment : ");

					String EmailId032 = sc.next();

					sc.nextLine();



					String query010= "select EmailId from User_Login ";

					pstmt = conn.prepareStatement(query010);

					ResultSet rs010 = pstmt.executeQuery();

					String ab01 = null;

					int n01 =0;



					while(rs010.next())

					{

						ab01 = rs010.getString("EmailId");

						if(EmailId032.equals(ab01))

						{

							n01 = 1;

							break;

						}

					}

					rs010.close();



					if(n01 == 1)

					{

						String query032= "select UserId from User_Login where EmailId = ? ";

						pstmt = conn.prepareStatement(query032);

						pstmt.setString(1, EmailId032);

						ResultSet rs032 = pstmt.executeQuery();

						int IdA2 = 0;



						while(rs032.next())

						{

							IdA2 = rs032.getInt("UserId");

						}

						rs032.close();



						query = "select * from QuesAns where UserId = ?";

						pstmt = conn.prepareStatement(query);

						pstmt.setInt(1, IdA2);

						ResultSet rsQ111C = pstmt.executeQuery();

						String Question1C = null;

						String Answer1C = null;

						int AnsID1C = 0;



						System.out.println("Question\t\tAnsId\t\tAnswers");



						while(rsQ111C.next())

						{

							Question1C =rsQ111C.getString("Question");

							AnsID1C =rsQ111C.getInt("AnsId");

							Answer1C =rsQ111C.getString("Answer");



							System.out.println(Question1C + "\t\t" + AnsID1C + "\t\t" + Answer1C);

						}

						rsQ111C.close();



						System.out.print("Enter AnsId of the Answer on which you want to comment : ");

						int AnsID10 = sc.nextInt();



						String queryy= "select AnsId from Answers ;";

						pstmt = conn.prepareStatement(queryy);

						ResultSet rss = pstmt.executeQuery();

						int abb = 0;

						int n11 =0;



						while(rss.next())

						{

							abb = rss.getInt("AnsId");

							if(AnsID10 == abb) 

							{

								n11 = 1;

								break;

							}

						}

						rss.close();



						if(n11 ==1)

						{

							BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));



							System.out.print("Enter Your Comment : ");

							String Comment = reader2.readLine();



							// Enter your Comment.....Add BufferReader

							query = "insert into Comments(AnsId,UserId,Comment) values (?,?,?)";

							pstmt = conn.prepareStatement(query);

							pstmt.setInt(1,AnsID1C);

							pstmt.setInt(2,Id1);

							pstmt.setString(3,Comment);

							pstmt.executeUpdate();



							System.out.println("Comment added successfully!!");

						}

						else

						{

							System.out.println("Wrong AnswerId !");

						}

					}

					else

					{

						System.out.println("Wrong EmailId !");

					}

					break;



				case 13:

					//To find users with similar interests

					query = "select interest from Interests where UserId = ?";

					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1,Id1);

					ResultSet rsI12 = pstmt.executeQuery();

					String[] Interests = new String[10];

					int i11 = 0;



					while(rsI12.next())

					{

						Interests[i11] = rsI12.getString("Interest");

						i11++;

					}

					rsI12.close();



					int j = 0;

					String Sim_query = null;

					int IDSim = 0;

					String EmaildIdSim = null;

					String NameSim = null;

					String InterestSim = null;

					ResultSet rsIS1 = null;



					System.out.println("UserId\t\t\tEmailId\t\t\tName\t\t\tInterest");



					while(Interests[j]!=null)

					{

						Sim_query = "select * from Similar_Interests where Interest = ?";

						pstmt = conn.prepareStatement(Sim_query);

						pstmt.setString(1,Interests[j]);

						rsIS1 = pstmt.executeQuery();



						while(rsIS1.next())

						{

							IDSim = rsIS1.getInt("UserId");

							EmaildIdSim = rsIS1.getString("EmailId");

							NameSim = rsIS1.getString("Name");

							InterestSim = rsIS1.getString("Interest");



							System.out.println(IDSim+"\t\t\t"+EmaildIdSim+"\t\t\t"+NameSim+"\t\t\t"+InterestSim);

						}

						j++;

					}

					break;



				case 14:

					//To search a user

					System.out.print("Enter the name of the person you want to search for : ");

					String Sim_Name = sc.next();

					sc.nextLine();

					Sim_Name = "%"+Sim_Name+"%";

					System.out.println(Sim_Name);



					query = "select UserId,EmailId,Name from User_Login where Name like ?";

					pstmt = conn.prepareStatement(query);

					pstmt.setString(1,Sim_Name);

					ResultSet rsSN = pstmt.executeQuery();

					String Sim_name = null;

					String Sim_EmailId = null;

					int Sim_UserId = 0;



					System.out.println("UserId\t\t\tName\t\t\tEmailId");



					while(rsSN.next())

					{ 

						Sim_UserId = rsSN.getInt("UserId");

						Sim_name = rsSN.getString("Name");

						Sim_EmailId= rsSN.getString("EmailId");



						System.out.println(Sim_UserId + "\t\t\t" + Sim_name + "\t\t\t" + Sim_EmailId );

					}

					rsSN.close();

					break;



				case 15:

					//To display no of answers posted by a user

					query = "select count(Answer) from Answers where UserId=? ";

					pstmt = conn.prepareStatement(query);

					pstmt.setInt(1, Id1);

					ResultSet rs104 = pstmt.executeQuery();

					int Count4 = 0;



					while(rs104.next()) 

					{

						Count2 =rs104.getInt("count(Answer)");

						System.out.println("No of answers : "+Count4);

					}

					rs104.close();

					break;

				case 16:

					//To display list of recently joined users to oldest users

					query = "select UserID,Name,LDate from User_Login order by LDate desc ;";

					pstmt = conn.prepareStatement(query);

					ResultSet rrs = pstmt.executeQuery();

					String UserName = null;

					int UserID00 = 0;

					Date LogDate ;



					System.out.println("UserId\t\t\tName\t\t\tLDate");



					while(rrs.next())

					{    

						UserID00 = rrs.getInt("UserId");

						UserName = rrs.getString("Name");

						LogDate= rrs.getDate("LDate");



						System.out.println(UserID00 + "\t\t\t" +  UserName + "\t\t\t" + LogDate );

					}

					rrs.close();

					break;	

				default:
					System.out.println("Invalid Option !");
					break;

				}

				System.out.print("Do you want to continue ? ");

				choose = sc.nextInt();

			} while(choose!=17);



			pstmt.close();

			conn.close();

			conn1.close();

			sc.close();

		}

		catch(ClassNotFoundException e) 

		{

			e.printStackTrace();

		}

		catch(SQLException se)

		{

			se.printStackTrace();

		}

	}

}

