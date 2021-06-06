<%@ page import="java.util.List" %>
<%@ page import="Model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<center>
    <h1>
        Lista Książek
    </h1>
    <table border="1">
        <tr>
            <th>
                Id
            </th>
            <th>
                Title
            </th>
            <th>
                Author
            </th>
            <th>
                Price
            </th>
        </tr>
        <%
            List<Book> bookList = (List<Book>) request.getAttribute("List");
            for (int i = 0; i < bookList.size(); i++) {
        %>
        <tr>
            <td>
                <%=bookList.get(i).getIdBook()%>
            </td>
            <td>
                <%=bookList.get(i).getBookName()%>
            </td>
            <td>
                <%=bookList.get(i).getAuthor()%>
            </td>
            <td>
                <%=bookList.get(i).getPrice()%>
            </td>
        </tr>
        <%}%>
    </table>
</center>
<style>
    body {
        background : #dcc724;
        margin-top: 100px;
        font-size: 300%;
    }

</style>
</body>
</html>
