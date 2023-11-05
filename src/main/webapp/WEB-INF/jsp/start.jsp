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
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.SerializationFeature" %>
<%@ page import="com.fasterxml.jackson.databind.module.SimpleModule" %>
<%@ page import="by.webproj.carshowroom.command.dto.StartDto" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
    <link rel="stylesheet" href="styles.css" />
    <title>quiz app</title>
</head>
<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;400&display=swap");
    * {
        box-sizing: border-box;
    }

    .correct-answer {
        background-color: green !important;
        color: white !important;
    }

    .quiz-container {
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 0 10px 2px rgba(100, 100, 100, 0.1);
        width: 600px;
        max-width: 95vw;
        overflow: hidden;
        margin: 0 auto; /* Добавьте эту строку для горизонтального центрирования */
        display: flex;
        flex-direction: column; /* Чтобы содержимое центрировалось вертикально */
        justify-content: center; /* Чтобы содержимое центрировалось вертикально */
        align-items: center; /* Чтобы содержимое центрировалось горизонтально */
    }

    .quiz-header {
        padding: 4rem;
        text-align: center; /* Для центрирования текста внутри .quiz-header */
    }

    h2 {
        padding: 1rem;
        text-align: center;
        margin: 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    ul li {
        font-size: 1.2rem;
        margin: 1rem 0;
    }

    ul li label {
        cursor: pointer;
    }

    button {
        background-color: #8e44ad;
        color: #fff;
        border: none;
        display: block;
        width: 100%;
        cursor: pointer;
        font-size: 1.1rem;
        font-family: inherit;
        padding: 1.3rem;
    }

    button:hover {
        background-color: #732d91;
    }

    button:focus {
        outline: none;
        background-color: #5e3370;
    }

</style>
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
                        <div class="col-md-12">

                                <div class="row">
                                    <div class="col-md-12 text-center">
                                        <div class="quiz-container" id="quiz">
                                            <div class="quiz-header">
                                                <h2 id="question">Question is loading...</h2>
                                                <ul>
                                                    <li> <input type="radio" name="answer" id="a" class="answer" /> <label for="a" id="a_text">Answer...</label> </li>
                                                    <li> <input type="radio" name="answer" id="b" class="answer" /> <label for="b" id="b_text">Answer...</label> </li>
                                                    <li> <input type="radio" name="answer" id="c" class="answer" /> <label for="c" id="c_text">Answer...</label> </li>
                                                    <li> <input type="radio" name="answer" id="d" class="answer" /> <label for="d" id="d_text">Answer...</label> </li>
                                                </ul>
                                            </div> <button id="submit">Submit</button>
                                        </div>
                                       <script>
                                           const quizData = [
                                               <c:forEach var="item" items="${requestScope.data}">
                                               {
                                               question: "${item.questionEntity.text}",
                                               <c:forEach var="ans" items="${item.answerDtos}" varStatus="loop">
                                                <c:if test="${loop.index == 0}">
                                                             a: "${ans.text}",
                                                   <c:if test="${ans.answerTrue eq true}">
                                                   correct: "a",
                                                   </c:if>
                                                </c:if>
                                               <c:if test="${loop.index == 1}">
                                                            b: "${ans.text}",
                                                   <c:if test="${ans.answerTrue eq true}">
                                                   correct: "b",
                                                   </c:if>
                                               </c:if>
                                               <c:if test="${loop.index == 2}">
                                               c: "${ans.text}",
                                                   <c:if test="${ans.answerTrue eq true}">
                                                   correct: "c",
                                                   </c:if>
                                               </c:if>
                                               <c:if test="${loop.index == 3}">
                                                     d: "${ans.text}",
                                                    <c:if test="${ans.answerTrue eq true}">
                                                   correct: "d",
                                                   </c:if>
                                                   </c:if>
                                                   
                                               </c:forEach>
                                               },
                                               </c:forEach>
                                           ];
                                           const quiz = document.getElementById("quiz");
                                           const answerElements = document.querySelectorAll(".answer");
                                           const questionElement = document.getElementById("question");
                                           const a_text = document.getElementById("a_text");
                                           const b_text = document.getElementById("b_text");
                                           const c_text = document.getElementById("c_text");
                                           const d_text = document.getElementById("d_text");
                                           const submitButton = document.getElementById("submit");
                                           let currentQuiz = 0;
                                           let score = 0;
                                           const deselectAnswers = () => {
                                               answerElements.forEach((answer) => (answer.checked = false));
                                           };
                                           const getSelected = () => {
                                               let answer;
                                               answerElements.forEach((answerElement) => {
                                                   if (answerElement.checked) answer = answerElement.id;
                                               });
                                               return answer;
                                           };
                                           const removeHighlight = () => {
                                               const highlightedAnswer = document.querySelector(".correct-answer");
                                               if (highlightedAnswer) {
                                                   highlightedAnswer.classList.remove("correct-answer");
                                               }
                                           };
                                           const loadQuiz = () => {
                                               removeHighlight();
                                               deselectAnswers();
                                               const currentQuizData = quizData[currentQuiz];
                                               questionElement.innerText = currentQuizData.question;
                                               a_text.innerText = currentQuizData.a;
                                               b_text.innerText = currentQuizData.b;
                                               c_text.innerText = currentQuizData.c;
                                               d_text.innerText = currentQuizData.d;
                                           };
                                           loadQuiz();

                                           const highlightCorrectAnswer = () => {
                                               // Найдите правильный ответ в массиве
                                               const correctAnswer = quizData[currentQuiz].correct;

                                               // Подсветите правильный ответ, добавив класс "correct-answer"
                                               const correctAnswerElement = document.getElementById(correctAnswer);
                                               if (correctAnswerElement) {
                                                   correctAnswerElement.parentElement.classList.add("correct-answer");
                                               }
                                           };
                                           submitButton.addEventListener("click", () => {
                                               const answer = getSelected();
                                               if (answer) {
                                                   if (answer === quizData[currentQuiz].correct) {
                                                       score++;
                                                   }
                                                   highlightCorrectAnswer(); // Подсветить правильный ответ
                                                   currentQuiz++;

                                                   if (currentQuiz < quizData.length) {
                                                       // Задержка перед загрузкой следующего вопроса (при необходимости)
                                                       setTimeout(loadQuiz, 1000);
                                                   } else {
                                                       quiz.innerHTML = `<h2>You answered `  + score + `/` +  quizData.length + ` questions correctly</h2>
                <button onclick="history.go(0)">Play Again</button>`;
                                                   }
                                               }
                                           });
                                       </script>
                                    </div>
                                </div>
                                <br>

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

