<%-- 
    Document   : addClient
    Created on : Feb 25, 2022, 9:37:22 AM
    Author     : anana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<main>
    <div style = "width: 980px; margin: auto auto">
        <form action="changingProduct" method="POST">
            <fieldset class="form-group">
                <legend style="text-align: center; margin-top: 30px">Change Product</legend>
                <h5 style="text-align: center; margin-top: 20px; margin: auto auto; color: red">${info}</h5>
                    <label class="form-label mt-4" for="product2">Products</label>
                    <select class="form-select" id="product2" name="product2">
                        <c:forEach var="product" items="${products}">
                            <option style="text-align: center" value="${product.id}">||${product.bywho} ${product.modell}|| ||Price: ${product.price}€ || ||Size: ${product.size}|| ||Piece: ${product.piece} in ${product.maxPiece}||</option>
                        </c:forEach>
                    </select>
                    <legend class="mt-4">What do you want to change?</legend>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="1">
                        ByWho
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios2" value="2">
                        Modell
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="3">
                        Price
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="4">
                        Size
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios3" value="5">
                        Piece
                      </label>
                    </div>
                    <input required style="margin-top: 30px" type="text" value="${input}" class="form-control" id="responsse" name="responsse" placeholder="input">
                    <button style="margin-top: 10px" type="submit" class="btn btn-primary" style="width:100px">Change</button>
            </fieldset>
        </form>
    </div>
</main>
