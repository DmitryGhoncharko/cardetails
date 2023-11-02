package by.webproj.carshowroom.entity;

public enum Role {
    CLIENT(1,"CLIENT"), ADMIN(2,"ADMIN"), INITIAL(0,"INITIAL");
    long roleId;
    String roleName;

    Role(long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
    public Role getRoleById(Long id){
        if(id==1){
            return Role.CLIENT;
        }else if (id == 2){
            return Role.ADMIN;
        }
        return Role.CLIENT;
    }
    public long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
