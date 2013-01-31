package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import creamy.db.Model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity 
@Table(name="account")
public class User extends Model {
    @Id
    @NotNull
    private String email;
    
    @NotNull
    private String name;
    
    @NotNull
    private String password;
    
    // -- Queries
    
    public static Model.Finder<String,User> find = new Model.Finder(String.class, User.class);
    
    /**
     * Retrieve all users.
     */
    public static List<User> findAll() {
        return find.all();
    }

    /**
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    /**
     * Authenticate a User.
     */
    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }
    
    public static List<User> findExcluded(List<User> users) {
        List<String> emails = new ArrayList<String>();
        for (User user : users) emails.add(user.email);
        return Ebean.find(User.class).where()
                .not(Expr.in("email", emails)).findList();
    }
    
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
