<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<html>
<head>
<title>Редактирование теста</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="header.jsp"></jsp:include>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="text-center">
                                Test English Now
                            </h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <c:forEach var="dt" items="${requestScope.data}">
                                <a class="btn btn-primary" href="/controller?command=addQuestion&testId=${dt.questionEntity.testEntity.id}">Добавить новый вопрос</a>
                                <br>
                                <br>
                                <form action="/controller?command=updateTest" method="post">
                                    <input hidden name="testId" value="${dt.questionEntity.testEntity.id}" type="text">
                                    <label >Название теста:</label>
                                    <input style="width: 70%"  name="testName" value="${dt.questionEntity.testEntity.testName}" type="text">
                                    <br>
                                    <label >Видим ли тест:</label>
                                    <c:set var="isChecked" value="${dt.questionEntity.testEntity.testVisible ? 'checked' : ''}" />
                                    <input  name="testVisible"  type="checkbox" ${isChecked}>
                                    <br>
                                    <button type="submit">Обновить</button>
                                </form>
                                <br>
                                <form action="/controller?command=updateQuestion" method="post">
                                    <input style="width: 70%" hidden name="questionId" value="${dt.questionEntity.id}" type="text">
                                    <label >Вопрос:</label>
                                    <input style="width: 70%" name="questionText" value="${dt.questionEntity.text}" type="text">
                                    <br>
                                    <button type="submit">Обновить</button>
                                </form>
                                <br>
                                <c:forEach var="d" items="${dt.answerDtos}">
                                    <form action="/controller?command=updateAnswer" method="post">
                                        <input style="width: 70%" hidden name="answerId" value="${d.id}" type="text">
                                        <label >Ответ:</label>
                                        <input style="width: 70%" name="answerText" value="${d.text}" type="text">
                                        <br>
                                        <label >Правильный ли ответ:</label>
                                        <c:set var="isChecked1" value="${d.answerTrue ? 'checked' : ''}" />
                                        <input  name="answerTrue" type="checkbox" ${isChecked1}>
                                        <br>
                                        <button type="submit">Обновить</button>
                                    </form>
                                    <br>
                                </c:forEach>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="footer.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

