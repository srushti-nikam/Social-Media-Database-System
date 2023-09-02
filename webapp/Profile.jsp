
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>

<%
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SocialMedia", "root", "admin@12345");
%> 
   
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<style>

body{
    margin-top:20px;
    background: #00b39b;
}

.ui-w-100 {
    width: 100px !important;
    height: auto;
}

.card {
    background-clip: padding-box;
    box-shadow: 0 1px 4px rgba(24,28,33,0.012);
}

.user-view-table td:first-child {
    width: 9rem;
}
.user-view-table td {
    padding-right: 0;
    padding-left: 20;
    border: 0;
}

.text-light {
    color: #babbbc !important;
}

.card .row-bordered>[class*=" col-"]::after {
    border-color: rgba(24,28,33,0.075);
}    

.text-xlarge {
    font-size: 170% !important;
}
table {
  width: 250%;
}

</style>

</head>


<body>
<%
String emailid = request.getParameter("EmailId");
//out.print(emailid);
String password = request.getParameter("Password");
//>out.print("EmailId: " + emailid + "<br>");
//out.print("Password : " + password);
%>
<%
Statement stmt = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT * FROM User_Login where EmailId = ? and Password= ?");
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM User_Login WHERE EmailId=? AND Password=?");
pstmt.setString(1, emailid);
pstmt.setString(2, password);
ResultSet rs = pstmt.executeQuery();
%>

<%
int IDN =0; 
String user_EmailId=emailid;
String user_Name=null;
Date user_DOB=null;
String user_EduQualifications=null;
String user_CurrentInstitution=null;
String user_WorkingDetail=null;
String user_COR=null;
String user_Password=password;
%>



<%
while (rs.next()) {

    IDN= rs.getInt("UserId");
    user_Name= rs.getString("Name");
    user_DOB= rs.getDate("DOB");
    user_EduQualifications=rs.getString("EduQualifications");
	user_CurrentInstitution=rs.getString("CurrentInstitution");
	user_WorkingDetail=rs.getString("WorkingDetail");
	user_COR=rs.getString("COR");

}
rs.close();
%>

  <div class="container bootdey flex-grow-1 container-p-y">

            <div class="media align-items-center py-3 mb-3">
              <img src="https://www.pngitem.com/pimgs/m/274-2748514_profile-icon-material-design-hd-png-download.png" alt="" class="d-block ui-w-100 rounded-circle">
              <div class="media-body ml-4">
                <h4 class="font-weight-bold mb-0"><%=user_Name %> </h4>
                <div class="text-muted mb-2">ID: <%=IDN %></div>
                <a href="javascript:void(0)" class="btn btn-default btn-sm">Profile</a>&nbsp;
                <a href="javascript:void(0)" class="btn btn-default btn-sm icon-btn"><i class="fa fa-mail"></i></a>
              </div>
            </div>

            <div class="card mb-4">
             
              <hr class="border-light m-0">
             
            </div>
               
            <div class="card mb-4">
             
              <hr class="border-light m-0">
             
            
           
<%PreparedStatement pstmt1 = null; %>


<%

int Following_Count = 0;
Statement stmt1 = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT * FROM User_Login where EmailId = ? and Password= ?");
 pstmt1 = conn.prepareStatement("select count(*) from User_Follower where UserId=? ");
pstmt1.setInt(1, IDN);

ResultSet rs1 = pstmt1.executeQuery();

while(rs1.next())
{
	Following_Count =rs1.getInt("count(*)");
	
}
rs1.close();

%>
<%

int Follower_Count = 0;
Statement stmt2 = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT * FROM User_Login where EmailId = ? and Password= ?");
pstmt1 = conn.prepareStatement("select count(*) from user_follower where FollowingId =? ");
pstmt1.setInt(1, IDN);

ResultSet rs2 = pstmt1.executeQuery();

while(rs2.next())
{
	Follower_Count =rs2.getInt("count(*)");
	
}
rs2.close();

%>
<%

int Question_Count = 0;
Statement stmt3 = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT * FROM User_Login where EmailId = ? and Password= ?");
 pstmt1 = conn.prepareStatement("select count(*) from Questions where UserId =? ");
pstmt1.setInt(1, IDN);

ResultSet rs3 = pstmt1.executeQuery();

while(rs3.next())
{
Question_Count =rs3.getInt("count(*)");
	
}
rs3.close();

%>
<%

int Answer_Count = 0;
Statement stmt4= conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT * FROM User_Login where EmailId = ? and Password= ?");
 pstmt1 = conn.prepareStatement("select count(*) from Answers where UserId =? ");
pstmt1.setInt(1, IDN);

ResultSet rs4 = pstmt1.executeQuery();

while(rs4.next())
{
	Answer_Count =rs4.getInt("count(*)");
	
}
rs4.close();

%>

 
<table class="table user-view-table m-0">
<tbody>
<tr>
<td>
<h6 class="mt-4 mb-3">Personal info</h6>
</td>


</tr>
    <tr>
        <td>UserId:</td>
        <td><p><%= IDN %></p></td>
    </tr>
    <tr>
        <td>EmailId:</td>
        <td><p><%= user_EmailId %></p></td>
    </tr>
    <tr>
        <td>Name:</td>
        <td><p><%= user_Name %></p></td>
    </tr>
    <tr>
         <td>Education Qualification:</td>
         <td><p><%=user_EduQualifications%></p></td>
     </tr>
     <tr>
         <td>Current Institution:</td>
        <td><p><%= user_CurrentInstitution%></p></td>
      </tr>
       <tr>
            <td>Working Detail:</td>
            <td><p><%= user_WorkingDetail %></p></td>
       </tr>
       </tbody>
   </table>

        
        <table class="table user-view-table m-0">
<tbody>
    <tr>
        <td>DOB:</td>
        <td><p><%= user_DOB %></p></td>
    </tr>
    <tr>
        <td>COR:</td>
        <td><p><%= user_COR %></p></td>
    </tr> 
    
    
    
     </tbody>
     </table>    

 
  
  

              </div>
         

<div class="card mb-4">
             
              <hr class="border-light m-0">
             
                <div class="container">
 <div class="row no-gutters row-bordered">
    <div class="col-sm-4">
      <div class="d-flex col-md align-items-center">
        <a href="javascript:void(0)" class="card-body d-block text-body">
        <div class="text-muted small line-height-1">Questions Posted</div>
        <div class="text-xlarge"><p><%= Question_Count %></p></div>
        </a>
      </div>
    </div>
   
    <div class="col-sm-4">
      <div class="d-flex col-md align-items-center">
        <a href="javascript:void(0)" class="card-body d-block text-body">
        <div class="text-muted small line-height-1">Answers Posted</div>
        <div class="text-xlarge"><p><%=Answer_Count %></p></div>
        </a>
      </div>
    </div>
     </div>
   
   
   <div class="row no-gutters row-bordered">
    <div class="col-sm-4">
      <div class="d-flex col-md align-items-center">
        <a href="javascript:void(0)" class="card-body d-block text-body">
        <div class="text-muted small line-height-1">Followers</div>
        <div class="text-xlarge"><p><%= Follower_Count %></p></div>
        </a>
      </div>
    </div>
   
    <div class="col-sm-4">
      <div class="d-flex col-md align-items-center">
        <a href="javascript:void(0)" class="card-body d-block text-body">
        <div class="text-muted small line-height-1">Following</div>
        <div class="text-xlarge"><p><%= Following_Count %></p></div>
        </a>
      </div>
    </div>
   
  </div>
</div>
</div>
  
  
   
  </body>
</html>
