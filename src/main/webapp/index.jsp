<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Responsive Login Form</title>
    <link href="styles.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <div class="container">
    <div class="title">Login</div>
    <div class="content">
      <form action="webapi/myresource/login" method="post">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Username</span>
            <input type="text" placeholder="Enter your username" name="uname" required>
          </div>
          <div class="input-box">
            <span class="details">Password</span>
            <input type="password" placeholder="Enter your password" name="pswd" required>
          </div>
        </div>
        
        <div class="button">
          <input type="submit" value="Login">
        </div>
      </form>
      <div class="signup-link">
        <p>Don't have an account? <a href="signup.html">Sign up here</a></p>
      </div>
      <div>
      
      </div>
    </div>
  </div>
  
</body>
</html>
