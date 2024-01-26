package by.webproj.carshowroom.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"), INDEX_PATH("/"),  CATEGORY("/WEB-INF/jsp/category.jsp"),
    NEW_CATEGORY("/WEB-INF/jsp/newcategory.jsp"), UPDATE_CATEGORY("/WEB-INF/jsp/updateCategory.jsp"),
    TASK_PAGE("/WEB-INF/jsp/task.jsp"), UPDATE_TASK("/WEB-INF/jsp/updateTask.jsp"),CREATE_TASK("/WEB-INF/jsp/createtask.jsp"),
    TASK_REV("/WEB-INF/jsp/taskRev.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
