<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <title>Sign up from</title>
  <head>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <style>
    body {
  background-image: url('https://images.squarespace-cdn.com/content/v1/544be87de4b05d5a7fec8f52/1613493007733-5U0HSOXJDK6F14YOCMA3/BOOM_Blog+Design_Trends+2021_BlogHeader_v1.png?format=1500w');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
 
  }
      html, body {
      display: flex;
      justify-content: center;
      font-family: Roboto, Arial, sans-serif;
      font-size: 20px;
      }
      form {
      border: 3px solid #f1f1f1;
      }
      input[type=text], input[type=password] {
      width: 100%;
      padding: 16px 8px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      box-sizing: border-box;
      }
      .icon {
      font-size: 110px;
      display: flex;
      justify-content: center;
      color:#3F497F;;
      }
      button {
      background-color: #3F497F;
      color: white;
      padding: 14px 0;
      margin: 10px 0;
      border: none;
      cursor: grab;
      width: 48%;
      }
      h1 {
      text-align:center;
      font-size:10;
      }
      button:hover {
      opacity: 0.8;
      }
      .formcontainer {
      text-align: center;
      margin: 24px 50px 12px;
      }
      .container {
      padding: 16px 0;
      text-align:left;
      }
      span.psw {
      float: right;
      padding-top: 0;
      padding-right: 15px;
      }
      /* Change styles for span on extra small screens */
      @media screen and (max-width: 300px) {
      span.psw {
      display: block;
      float: none;
      }
    </style>
  </head>
  <body>
    <form action="Index" method="post">
      <h1>SIGN UP</h1>
      <div class="icon">
        <i class="fas fa-user-circle"></i>
      </div>
      <div class="formcontainer">
      <div class="container">
        <label>Enter Email</label>
        <br>
    
<input type="email" name="EmailId" value="" style="width: 500px; height: 43px;" required>
<br>

<label>Enter your name</label>
<input type="text" name="Name" value="" required>
<br>
<label>Enter DOB</label>
<input type="text" name="DOB" value="" required>
<br>
<label>Enter Education Qualification</label>
<input type="text" name="EduQualifications" value="">
<br>
<label>Enter Current Institution</label>
<input type="text" name="CurrentInstitutionstNo" value="">
<br>
<label>Enter Working Detail</label>
<input type="text" name="WorkingDetail" value="">
<br>
<label>Enter Country of Residence</label>
<input type="text" name="COR" value="">
<br>
<label>Enter Password</label>
<input type="text" name="Password" value="" required>
<br>

      </div>
      <button type="submit"><strong>SIGN UP</strong></button>
      <div class="container" style="background-color:#eee">
        <label style="padding-left: 15px">
        <input type="checkbox"  checked="checked" name="remember"> Remember me
        </label>
             </div>
    </form>
    
  </body>
</html>

