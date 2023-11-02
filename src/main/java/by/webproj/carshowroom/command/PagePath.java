package by.webproj.carshowroom.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"), INDEX_PATH("/"), REGISTRATION_PAGE("/WEB-INF/jsp/registration.jsp"),
    TEST_PAGE("/WEB-INF/jsp/test.jsp"), TEST_ADMIN_PAGE("/WEB-INF/jsp/testAdmin.jsp"), START_PAGE("/WEB-INF/jsp/start.jsp"), ADMIN_CAB("/WEB-INF/jsp/admin.jsp"),
    CREATE_PAGE("/WEB-INF/jsp/create.jsp"), EDIT_PAGE("/WEB-INF/jsp/edit.jsp"),ADD_PAGE("/WEB-INF/jsp/add.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
