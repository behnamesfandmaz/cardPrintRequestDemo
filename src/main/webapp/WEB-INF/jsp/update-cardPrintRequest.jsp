<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
    <title>First Web Application</title>
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>
<div class="container">
    <form:form method="post" modelAttribute="cardPrintRequestDTO">
        <fieldset class="form-group">
            <form:label path="branchCode">Branch Code</form:label>
            <form:input path="branchCode" name="branchCode" type="text" disabled="true" value=""
                   class="form-control" required="required"/>
            <form:errors path="branchCode" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="ipAddress">IpAddress</form:label>
            <form:input path="ipAddress" name="ipAddress" type="text" disabled="true" value=""
                        class="form-control" required="required"/>
            <form:errors path="ipAddress" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="cardPan">cardPan</form:label>
            <form:input path="cardPan" name="cardPan" type="text"
                        class="form-control" required="required"/>
        </fieldset>
        <button type="submit" class="btn btn-success">Add</button>
    </form:form>
</div>

<script src="/webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>