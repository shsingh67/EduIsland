<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
 <head>
     <title>Registration</title>
 </head>
 <style>
     /* Full-width input fields */
     input[type=text], input[type=password] {
         width: 100%;
         padding: 12px 20px;
         margin: 8px 0;
         display: inline-block;
         border: 1px solid #ccc;
         box-sizing: border-box;
     }

     /* Set a style for all buttons */
     button {
         background-color: #4CAF50;
         color: white;
         padding: 14px 20px;
         margin: 8px 0;
         border: none;
         cursor: pointer;
         width: 100%;
     }

     /* Extra styles for the cancel button */
     .cancelbtn {
         padding: 14px 20px;
         background-color: #f44336;
     }

     /* Float cancel and signup buttons and add an equal width */
     .cancelbtn,.signupbtn {
         float: left;
         width: 50%;
     }

     /* Add padding to container elements */
     .container {
         padding: 16px;
     }

     /* Clear floats */
     .clearfix::after {
         content: "";
         clear: both;
         display: table;
     }

     /* Change styles for cancel button and signup button on extra small screens */
     @media screen and (max-width: 300px) {
         .cancelbtn, .signupbtn {
             width: 100%;
         }
     }
 </style>
<body>
 <h1>Signup Form</h1>
<form:form id ="regForm" modellAttribute ="userForm" action = "registerProcess" method = "post">
    <div class="container">
        <label><b>Username</b></label>
        <input type="text" placeholder="Enter username" name="userId" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <label><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="Rpassword" required>
        <input type="checkbox" checked="checked"> Remember me

        <div class="clearfix">
            <button type="button" class="cancelbtn">Cancel</button>
            <button type="submit" class="signupbtn">Sign Up</button>
        </div>
    </div>

</form:form>


</body>

</html>