<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="/css/reset.css">
<script src="/js/jquery-3.3.1.min.js"></script>
<script>

</script>
</head>
<body>
  <main>
  	<form id="sign-up-form" action="sign-up" method="post">
  		<div class="input-item">
  			<label class="input-label">User Name</label>
  			<input name="username" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<div class="input-item">
  			<label class="input-label">E-mail</label>
  			<input name="email" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<div class="input-item">
  			<label class="input-label">Password</label>
  			<input name="password" type="text"/>
  			<span class="input-description"></span>
  		</div>
<!--   		<div class="input-item">
  			<label class="input-label">Password Confirmation</label>
  			<input name="password-confirmation" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		 -->
		<input type="submit" value="sign-up"/>
  	</form>
  </main>
</body>
</html>