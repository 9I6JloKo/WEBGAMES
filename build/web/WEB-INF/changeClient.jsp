<%-- 
    Document   : addClient
    Created on : Feb 25, 2022, 9:37:22 AM
    Author     : anana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<main>
    <div style = "width: 680px; margin: auto auto">
        <form action="ChangingClient" method="POST">
            <fieldset class="form-group">
                <legend style="text-align: center; margin-top: 30px">Change Client</legend>
                <h5 style="text-align: center; margin-top: 20px; margin: auto auto; color: red">${info}</h3>
                    <label class="form-label mt-4" for="clientt2">Clients</label>
                    <select class="form-select"  id="clientt2" name="clientt2">
                        <c:forEach var="client" items="${clients}">
                            <option style="text-align: center" value="${client.id}">||${client.clientName} ${client.clientSurname}|| ||Number: ${client.clientNumber}|| ||Money: ${client.clientMoney}|| ||Log & Pass: @${client.login}@ @${client.password}@|| ||Level: ${client.level}||</option>
                        </c:forEach>
                    </select>
                    <legend class="mt-4">What do you want to change?</legend>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="1">
                        Client name
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios2" value="2">
                        Client surname
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="3">
                        Client number
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="4">
                        Client money(euros)
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="5">
                        Client login
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="6">
                        Client password
                      </label>
                    </div>
                    <input required style="margin-top: 30px" type="text" value="${input}" class="form-control" id="responsse" name="responsse" placeholder="input">
                    <button style="margin-top: 10px" type="submit" class="btn btn-primary" style="width:100px">Change</button>
            </fieldset>
        </form>
    </div>
</main>
