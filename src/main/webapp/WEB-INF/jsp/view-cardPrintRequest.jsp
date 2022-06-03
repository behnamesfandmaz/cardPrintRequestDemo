<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="resources/css/searchForm.css" rel="stylesheet" type="text/css">
</head>
<script src="https://cdn.jsdelivr.net/gh/akjpro/form-anticlear/base.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<script>
    function updateTable(page) {
        var keyWord = document.getElementById("branchCodeId").value;
        var pageSize = document.getElementById("pageSize").value;
        console.log(keyWord);
        if (keyWord.length != 0) {
            location.href = "/getAllCardPrintRequestWithBranchCode?branchCode=" + keyWord + "&pageNo=" + page + "&pageSize=" + pageSize;
            return;
        }
        location.href = "/getAllCardPrintRequest?pageNo=" + page + "&pageSize=" + pageSize;
    }

    function clearInput() {
        document.getElementById("branchCodeId").value = '';
        document.getElementById("pageSize").value = 5;
    }

</script>
<div class="container">
    <form class="form-anticlear">
        <input type="text" placeholder="Search Branch Code" id="branchCodeId" name="branchCode"/>
        <button type="submit" formaction="javascript:updateTable(1)"><i class="fas fa-search"></i></button>
        <button type="submit" formaction="javascript:clearInput()"><i class="fas fa-trash"></i></button>
        <select id="pageSize" style="width: 40px;height: 40px">
            <option value='5'>5</option>
            <option value='10'>10</option>
            <option value='15'>15</option>
        </select>
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
                       onclick="clearInput()"
                       href="/updateCardPrintRequest?ipAddress=${cardPrintRequestDto.ipAddress}&branchCode=${cardPrintRequestDto.branchCode}">Update</a>
                </td>
                <td><a type="button" class="btn btn-warning"
                       onclick="clearInput()"
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
                    <a class="page-link" href="javascript:updateTable(${paging.prevPageNo})"
                       tabindex="-1">Previous</a>
                </li>
            </c:if>
            <c:if test="${cardPrintRequestDTOs.currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="javascript:updateTable(${cardPrintRequestDTOs.currentPage - 1})"
                       tabindex="-1">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${cardPrintRequestDTOs.totalPages}" var="i">
                <c:choose>
                    <c:when test="${cardPrintRequestDTOs.currentPage eq i}">
                        <li class="page-item active"><a class="page-link">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="javascript:updateTable(${i})">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${cardPrintRequestDTOs.currentPage eq cardPrintRequestDTOs.totalPages}">
                <li class="page-item hide">
                    <a class="page-link" href="javascript:updateTable(${cardPrintRequestDTOs.currentPage + 1})">Next</a>
                </li>
            </c:if>
            <c:if test="${cardPrintRequestDTOs.currentPage lt cardPrintRequestDTOs.totalPages}">
                <li class="page-item">
                    <a class="page-link" href="javascript:updateTable(${cardPrintRequestDTOs.currentPage + 1})">Next</a>
                </li>
            </c:if>
            <li class="page-information">
                <a>Number of Items:${cardPrintRequestDTOs.totalItems}</a>
            </li>
        </ul>

    </nav>
</div>
<%@ include file="common/footer.jspf" %>