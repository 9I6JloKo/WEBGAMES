<%-- 
    Document   : addClient
    Created on : Feb 25, 2022, 9:37:22 AM
    Author     : anana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<main>
    <div style = "width: 750px; margin: auto auto">
        <form action="BuyingProduct" method="POST">
            <fieldset>
                <legend style="text-align: center; margin-top: 30px">Buy product</legend>
                <h5 style="text-align: center; margin-top: 20px; margin: auto auto; color: red">${info}</h3>
                <label class="form-label mt-4" for="clientt">Clients</label>
                <select class="form-select"  id="clientt" name="clientt">
                    <c:forEach var="client" items="${clients}">
                        <option style="text-align: center" value="${client.id}">||${client.clientName} ${client.clientSurname}|| ||Number: ${client.clientNumber}|| ||Money: ${client.clientMoney}|| ||Log & Pass: @${client.login}@ @${client.password}@|| ||Level: ${client.level}||</option>
                    </c:forEach>
                </select>
                <label class="form-label mt-4" for="productt">Products</label>
                <select class="form-select"  id="productt" name="productt">
                    <c:forEach var="product" items="${products}">
                        <option style="text-align: center" value="${product.id}">||${product.bywho} ${product.modell}||    ||Size: ${product.size}||     ||Price: ${product.price}€||     ||Amount: ${product.piece} pairs||</option>
                    </c:forEach>
                </select>
                <div style="margin: 30px auto; text-align: center">
                    <button type="submit" class="btn btn-primary" style=" width:100px">Submit</button>
                    <button type="reset" class="btn btn-primary" style="margin-left: 10px; width:100px">FIRST PAR</button>
                </div>
            </fieldset>
        </form>
    </div>
</main>
