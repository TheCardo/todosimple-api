package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity //definindo que a classe User é uma entidade
@Table(name = User.TABLE_NAME) // definindo o nome da tabela como user
public class User {
    public interface CreateUser{}
    public interface UpdateUser{}

    public static final String TABLE_NAME = "user";


    @Id// anotação que isso é um id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremento
    @Column(name = COLUMN_ID_NAME, unique = true, nullable = false) //define as propriedades da coluna
    private Integer id;
    public static final String COLUMN_ID_NAME = "id";


    @Column(name = USERNAME_COLUMN_NAME, unique = true, nullable = false, length = 100)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min= 2, max = 100)
    private String username;
    public static final String USERNAME_COLUMN_NAME = "username";

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = PASSWORD_COLUMN_NAME, nullable = false, length = 60)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;
    public static final String PASSWORD_COLUMN_NAME = "password";

    // private List<task> tasks = new ArrayList<task>();

    public User(){

    }
    public User(Integer id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (!(obj instanceof User other)){
            return false;
        }

        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);


    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
