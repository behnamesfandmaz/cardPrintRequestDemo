<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="resources/css/searchForm.css" rel="stylesheet" type="text/css">
</head>
<script src="https://cdn.jsdelivr.net/gh/akjpro/form-anticlear/base.js"></script>
<script>
    function PageMove(page) {
        var keyWord = document.getElementById("branchCodeId").value;
        console.log(keyWord);
        if (keyWord.length != 0) {
            location.href = "/getAllCardPrintRequestWithBranchCode?pageNo=" + page + "&branchCode=" + keyWord;
            return;
        }
        location.href = "/getAllCardPrintRequest?pageNo=" + page;
    }

    function clearInput() {
        document.getElementById("branchCodeId").value = '';
        location.href = "/getAllCardPrintRequest?pageNo=" + 1;
    }
</script>
<div class="container">
    <form class="form-anticlear">
        <input type="text" placeholder="Search Branch Code" id="branchCodeId" name="branchCode"/>
        <button type="submit" formaction="javascript:PageMove(1)"><i class="fa fa-search"></i></button>
        <button type="submit" formaction="javascript:clearInput()"><i class="fa fa-close"></i></button>
    </form>
    <table class="table table-striped table-bordered table-sm">
        <caption>Your Card Print Requests</caption>
        <thead>
        <tr>
            <th>ip Address</th>
            <th>branch Code</th>
            <th>issued Date</th>
            <th>personnel Code</th>
            <th>card Pan</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cardPrintRequestDTOs.cardPrintRequestDTOs}" var="cardPrintRequestDto">
            <tr>
                <td>${cardPrintRequestDto.ipAddress}</td>
                <td>${cardPrintRequestDto.branchCode}</td>
                <td><fmt:formatDate value="${cardPrintRequestDto.issuedDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${cardPrintRequestDto.personnelCode}</td>
                <td>${cardPrintRequestDto.cardPan}</td>
                <td><a type="button" class="btn btn-success"
                       href="/updateCardPrintRequest?ipAddress=${cardPrintRequestDto.ipAddress}&branchCode=${cardPrintRequestDto.branchCode}">Update</a>
                </td>
                <td><a type="button" class="btn btn-warning"
                       href="/deleteCardPrintRequestById?ipAddress=${cardPrintRequestDto.ipAddress}&branchCode=${cardPrintRequestDto.branchCode}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="cardPrintRequestDTOs dataTable Paggination">
        <ul class="pagination">
            <c:if test="${cardPrintRequestDTOs.currentPage == 1}">
                <li class="page-item hide">
                    <a class="page-link" href="javascript:PageMove(${paging.prevPageNo})"
                       tabindex="-1">Previous</a>
                </li>
            </c:if>
            <c:if test="${cardPrintRequestDTOs.currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="javascript:PageMove(${cardPrintRequestDTOs.currentPage - 1})"
                       tabindex="-1">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${cardPrintRequestDTOs.totalPages}" var="i">
                <c:choose>
                    <c:when test="${cardPrintRequestDTOs.currentPage eq i}">
                        <li class="page-item active"><a class="page-link">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="javascript:PageMove(${i})">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${cardPrintRequestDTOs.currentPage eq cardPrintRequestDTOs.totalPages}">
                <li class="page-item hide">
                    <a class="page-link" href="javascript:PageMove(${cardPrintRequestDTOs.currentPage + 1})">Next</a>
                </li>
            </c:if>
            <c:if test="${cardPrintRequestDTOs.currentPage lt cardPrintRequestDTOs.totalPages}">
                <li class="page-item">
                    <a class="page-link" href="javascript:PageMove(${cardPrintRequestDTOs.currentPage + 1})">Next</a>
                </li>
            </c:if>
            <li class="page-information">
                <a>Number of Items:${cardPrintRequestDTOs.totalItems}</a>
            </li>
        </ul>
        <%--        <form method="get" action="/getAllCardPrintRequest?pageNo=1">--%>
        <%--            <select name='pageNo' onchange='if(this.value != 0) { this.form.submit(); }'>--%>
        <%--                <option value='0'>Contact List</option>--%>
        <%--                <option value='5'>blah</option>--%>
        <%--                <option value='10'>blah2</option>--%>
        <%--                <option value='20'>blah3</option>--%>
        <%--            </select>--%>
        <%--        </form>--%>
    </nav>
</div>
<%@ include file="common/footer.jspf" %>