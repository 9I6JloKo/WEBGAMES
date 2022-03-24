<%-- 
    Document   : revenue
    Created on : 24-Mar-2022, 15:02:42
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<main>
    <div style = "width: 750px; margin: auto auto">
        <form method="POST" action="revenue">
            <h2 class="my-5 w-100 d-flex justify-content-center">Profit</h2>
            <label class="form-label mt-4" style="font-weight: 900" for="revenue">What period of time do you prefer?</label>
            <select class="form-select" id="revenue" name="revenue">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
                <option value="13">For whole YEAR</option>
            </select>
        </form>
        <p></p>
    </div>
</main>
