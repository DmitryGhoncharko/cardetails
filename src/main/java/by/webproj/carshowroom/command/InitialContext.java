package by.webproj.carshowroom.command;


import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.controller.SimpleRequestFactory;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.connection.HikariCPConnectionPool;
import by.webproj.carshowroom.model.dao.*;


public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();

    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();
    private final CategoryDao categoryDao = new CategoryDao(hikariCPConnectionPool);
    private final TaskDao taskDao = new TaskDao(hikariCPConnectionPool);
    private final DtDao dtDao = new DtDao(hikariCPConnectionPool);
    private final DataDao dataDao = new DataDao(hikariCPConnectionPool);
    private final UserDao userDao = new UserDao(hikariCPConnectionPool);

    public Command lookup(String commandName) {

        switch (commandName) {
            case "logincmnd":
                return new LoginCommand(simpleRequestFactory);
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            case "category":
                return new ShowCategoryPageCommand(simpleRequestFactory,categoryDao);
            case "newcategory":
                return new ShowNewCategoryPageCommand(simpleRequestFactory);
            case "createCategory":
                return new CreateCategoryCommand(simpleRequestFactory,categoryDao);
            case "getCategory":
                return new ShowGetCategoryPageCommand(simpleRequestFactory,categoryDao);
            case "updateCategory":
                return new UpdateCategoryCommand(simpleRequestFactory,categoryDao);
            case "taskByCategory":
                return new ShowTasksByCategory(simpleRequestFactory,taskDao);
            case "deleteTask":
                return new DeleteTaskCommand(simpleRequestFactory,taskDao);
            case "updateTask":
                return new ShowUpdateTaskPage(simpleRequestFactory,taskDao);
            case "updatetaskk":
                return new UpdateTaskCommand(simpleRequestFactory,taskDao);
            case "createNewTask":
                return new ShowCreateTaskPage(simpleRequestFactory);
            case "createNewTaskk":
                return new AddNewTask(simpleRequestFactory,taskDao);
            case "taskRev":
                return new ShowTaskRevCommand(simpleRequestFactory,taskDao,categoryDao,dtDao);
            case "submit":
                return new SubmitTaskCommand(simpleRequestFactory,dtDao,taskDao,userDao,dataDao);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }
    }
}
