<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="/css/reset.css">
</head>
<body>

  
  <main>
  	<form id="create-category-form" action="create" method="post">
  		<div class="input-item">
  			<label class="input-label">id</label>
  			<input name="id" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<div class="input-item">
  			<label class="input-label">name</label>
  			<input name="name" type="text"/>
  			<span class="input-description"></span>
  		</div>
  		<div class="input-item">
  			<label class="input-label">parentId</label>
  			<input name="parentId" type="text"/>
  			<span class="input-description"></span>
  		</div>
		<input type="submit" value="sign-up"/>
  	</form>
  </main>

</body>
</html>