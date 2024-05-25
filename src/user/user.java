package user;

import javax.lang.model.element.NestingKind;

public class user {
    private String name;
    private String password;

    public user() {
    }

    public user(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return this.name+"&"+this.password;
    }
}
