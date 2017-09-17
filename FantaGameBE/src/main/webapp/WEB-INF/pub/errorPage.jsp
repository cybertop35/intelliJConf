<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2 style="color: red;" >Error:</h2>
<table>
  <tr>
    <th style="height: 27px; ">Message</th>
    <th style="width: 565px; "><c:out value="${message}"></c:out></th>
  </tr>
   <tr>
    <th style="height: 27px; ">Cause</th>
    <th style="width: 565px; "><c:out value="${cause}"></c:out></th>
  </tr>
</table>
 
<center><h5 style="" >Contact administrator</h5></center>