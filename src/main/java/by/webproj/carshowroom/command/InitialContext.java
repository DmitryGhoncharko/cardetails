package by.webproj.carshowroom.command;


import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.controller.SimpleRequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.*;
import by.webproj.carshowroom.model.service.*;
import by.webproj.carshowroom.securiy.BcryptWithSaltHasherImpl;
import by.webproj.carshowroom.securiy.PasswordHasher;
import by.webproj.carshowroom.validator.SimpleUserValidator;
import by.webproj.carshowroom.validator.UserValidator;


public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final UserDao simplePageDao = new SimpleUserDao(hikariCPConnectionPool);
    private final UserValidator simplePageServiceValidator = new SimpleUserValidator();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();

    private final UserService simpleUserService = new SimpleUserService(simplePageServiceValidator, simplePageDao, bcryptWithSaltHasher);
    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();
    private final TestDao testDao= new TestDao(hikariCPConnectionPool);
    private final AnswerDao answerDao=  new AnswerDao(hikariCPConnectionPool);
    private final QuestionDao questionDao = new QuestionDao(hikariCPConnectionPool);
    public Command lookup(String commandName) {

        switch (commandName) {
            case "logincmnd":
                return new LoginCommand(simpleUserService, simpleRequestFactory);
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            case "registration":
                return new ShowRegistrationPageCommand(simpleRequestFactory);
            case "registrationcmnd":
                return new RegistrationCommand(simpleUserService, simpleRequestFactory);
            case "test":
                return new ShowTestPageCommand(testDao,simpleRequestFactory);
            case "startTest":
                return new StartTestCommand(answerDao,questionDao,simpleRequestFactory);
            case "cabAdmin":
                return new ShowCabAdminPageCommand(simpleRequestFactory, (SimpleUserDao) simplePageDao);
            case "showTests":
                return new ShowTestsAdminCommand(simpleRequestFactory,testDao);
            case "createTest":
                return new ShowCreateTestPageCommand(simpleRequestFactory);
            case "addTest":
                return new AddTestCommand(simpleRequestFactory,testDao,answerDao,questionDao);
            case "editTest":
                return new ShowEditTestPageCommand(simpleRequestFactory,testDao,questionDao,answerDao);
            case "updateTest":
                return new UpdateTestCommand(simpleRequestFactory,testDao);
            case "updateQuestion":
                return new UpdateQuestionCommand(simpleRequestFactory,questionDao);
            case "updateAnswer":
                return new UpdateAnswerCommand(simpleRequestFactory,answerDao,questionDao);
            case "addQuestion":
                return  new ShowAddNewQuestionPageCommand(simpleRequestFactory,testDao);
            case "addNewQuestion":
                return new AddQuestionComand(simpleRequestFactory,questionDao,answerDao);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }
    }
}
