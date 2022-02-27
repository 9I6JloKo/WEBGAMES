<%-- 
    Document   : addClient
    Created on : Feb 25, 2022, 9:37:22 AM
    Author     : anana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<main>
    <div style = "width: 680px; margin: auto auto">
        <form action="addingProduct" method="POST">
            <fieldset>
                <legend style="text-align: center; margin-top: 30px">Add Product to DB</legend>
                <h5 style="text-align: center; margin-top: 20px; margin: auto auto; color: red">${info}</h3>
                <div class="form-group">
                    <label for="productByWho" class="form-label mt-4">Product company</label>
                    <input type="text" class="form-control" id="clientNumber" name="productByWho"  placeholder="Company"  value="${productByWho}">
                </div>
                <div class="form-group">
                    <label class="form-label mt-4" for="productModell">Product model</label>
                    <input type="text" class="form-control" id="clientName" name="productModell" placeholder="Model"  value="${productModell}">
                </div>
                <div class="form-group">
                    <label for="productSize" class="form-label mt-4">Product size</label>
                    <input type="number" class="form-control" id="clientSurname" name="productSize" placeholder="Size" min="3" step="0.5" value="${productSize}">
                </div>
                <div class="form-group">
                    <label for="productPrice" class="form-label mt-4">Product price(euros)</label>
                    <input type="number" class="form-control" id="clientMoney" name="productPrice" placeholder="Price" min="0.01" step="0.01"  value="${productPrice}">
                </div>
                <div class="form-group">
                    <label for="productPiece" class="form-label mt-4">Product amount(in pairs)</label>
                    <input type="number" class="form-control" id="clientLogin" name="productPiece" placeholder="Amount" min="1" step="1" value="${productPiece}">
                </div>
                <div style=" margin: 30px auto; text-align: center">
                    <button type="submit" class="btn btn-primary" style=" width:100px">Submit</button>
                    <button type="reset" class="btn btn-primary" style="margin-left: 10px; width:100px">Reset</button>
                </div>
            </fieldset>
        </form>
    </div>
</main>
