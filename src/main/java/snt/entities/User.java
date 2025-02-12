package snt.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "USERNAME",length = 50)
    private String username;
    @Basic
    @Column(name = "PASSWORD",length = 500)
    private String password;
    @Basic
    @Column(name = "email",length = 500)
    private String email;
    @Basic
    @Column(name = "ENABLED")
    private boolean enabled;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ENABLE_TIME")
    @JsonIgnore
    private Date enableTime;

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @JsonProperty
    public long getEnableChangeTime() {
        return this.enableTime.getTime();
    }
    @Override
    public String toString() {
        return "UsersEntity{" +
                "username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", enabled:" + enabled +
                ", enableTime:" + enableTime.getTime() +
                ", createTime:" + createTime.getTime() +
                '}';
    }
}
