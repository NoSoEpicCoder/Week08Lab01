<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <link rel="stylesheet" href="<c:url value='styles/notes.css' />" />
    </head>
    <body>
        <h1>Manage Users</h1>
        <h2>Notes</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>Note Id</th>
                <th>Date Created</th>
                <th>Content</th>
            </tr>
            <c:forEach var="note" items="${notes}">
                <tr>
                    <td>${note.noteId}</td>
                    <td>${note.dateCreated}</td>
                    <td>${note.content}</td>
                    <td>
                        <form action="notes" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${note.noteId}">
                        </form>
                    </td>
                    <td>
                        <form action="users" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedNoteId" value="${note.noteId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedNoteId == null}">
            <h3>Add Note</h3>
            
            <form action="notes" method="POST">
                Contents: <input type="text" name="content"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedUser != null}">
            <h3>Edit Notes</h3>
            <form action="notes" method="POST">
                Note id: <input type="text" name="noteID" value="${selectedNoteID.noteID}" readonly><br>
                Date Created: <input type="text" name="dateCreated" value="${selectedNoteID.dateCreated}" readonly><br>
                Content: <input type="text" name="content" value="${selectedNoteID.content}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>