package br.com.caiobruno.restspring.data.vo.v1.security;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialVO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName ;
    private String password;

    public AccountCredentialVO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountCredentialVO that)) return false;
        return Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword());
    }
}