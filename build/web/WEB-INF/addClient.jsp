<%-- 
    Document   : addClient
    Created on : Feb 25, 2022, 9:37:22 AM
    Author     : anana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<main>
    <div style = "width: 680px; margin: auto auto">
        <form action="addingClient" method="POST">
            <fieldset>
                <legend style="text-align: center; margin-top: 30px">Add Client to DB</legend>
                <h5 style="text-align: center; margin-top: 20px; margin: auto auto; color: red">${info}</h3>
                <div class="form-group">
                    <label class="form-label mt-4" for="clientName">Client name</label>
                    <input required type="text" class="form-control" id="clientName" name="clientName" placeholder="Name">
                </div>
                <div class="form-group">
                    <label for="clientSurname" class="form-label mt-4">Client surname</label>
                    <input required type="text" class="form-control" id="clientSurname" name="clientSurname" placeholder="Surname">
                </div>
                <div class="form-group">
                    <label for="clientNumber" class="form-label mt-4">Client number</label>
                    <input required type="number" class="form-control" id="clientNumber" name="clientNumber"  placeholder="e.g : (53993935)" min="5000000" step="1">
                </div>
                <div class="form-group">
                    <label for="clientMoney" class="form-label mt-4">Client money(euros)</label>
                    <input required type="number" class="form-control" id="clientMoney" name="clientMoney" placeholder="Money" min="0" step="0.01">
                </div>
                <div class="form-group">
                    <label for="clientLogin" class="form-label mt-4">Client login</label>
                    <input required type="text" class="form-control" id="clientLogin" name="clientLogin" placeholder="Login">
                </div>
                <div class="form-group">
                    <label for="clientPassword" class="form-label mt-4">Client password</label>
                    <input required type="text" class="form-control" id="clientPassword" name="clientPassword" placeholder="Password" minlength="6">
                </div>
                <div style="margin: 30px auto; text-align: center">
                    <button type="submit" class="btn btn-primary" style=" width:100px">Submit</button>
                    <button type="reset" class="btn btn-primary" style="margin-left: 10px; width:100px">Reset</button>
                </div>
            </fieldset>
        </form>
    </div>
</main>
