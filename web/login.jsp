<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2 class="my-5 w-100 d-flex justify-content-center">WebShopBoots</h2>
<h5 style="text-align: center; margin-top: 20px; margin: auto auto; color: red">${info}</h5>
<div class="w-100 d-flex justify-content-center">
    <form action="login" method="POST" >
      <div class="card border-0 mb-3" style="width: 20em;">
        <div class="form-group">
          <label for="login" class="form-label mt-4">Login</label>
          <input type="text" class="form-control" id="login" name="login" aria-describedby="" placeholder="Login" value="${login}">
          <small id="login" hidden class="form-text text-muted">Error</small>
        </div>
        <div class="form-group">
          <label for="password" class="form-label mt-4">Password</label>
          <input type="password" class="form-control" id="password" name="password" aria-describedby="" placeholder="Password">
          <small id="password" hidden class="form-text text-muted">Error</small>
        </div>
        <input class="btn btn-primary mt-5" type="submit" value="ENTER">
      </div>
      <h4 class="w-100 text-center my-3"><a href="showRegistration">Registration</a></h4>
    </form>
</div>
