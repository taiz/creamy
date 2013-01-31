package models;

import com.avaje.ebean.Ebean;
import creamy.db.Model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity 
public class Project extends Model {

    @Id
    private Integer id;

    private String name;
    
    private String folder;
    
    @ManyToMany
    public List<User> members = new ArrayList<User>();
    
    @OneToMany
    private List<Task> tasks;
    
    public Project() {}
    
    public Project(String name, String folder, User owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
    }
    
    // -- Queries
    
    public static Model.Finder<Integer,Project> find = new Model.Finder(Integer.class, Project.class);
    
    /**
     * Retrieve project for user
     */
    public static List<Project> findInvolving(String user) {
        return find.where()
            .eq("members.email", user)
            .findList();
    }
    
    /**
     * Delete all project in a folder
     */
    public static void deleteInFolder(String folder) {
        Ebean.createSqlUpdate(
            "delete from project where folder = :folder"
        ).setParameter("folder", folder).execute();
    }
    
    /**
     * Create a new project.
     */
    public static Project create(String name, String folder, String owner) {
        Project project = new Project(name, folder, User.find.ref(owner));
        project.save();
        project.saveManyToManyAssociations("members");
        return project;
    }
    
    /**
     * Rename a project
     */
    public static String rename(Integer projectId, String newName) {
        Ebean.createSqlUpdate(
            "update project set name = :newName where id = :id"
        ).setParameter("id", projectId).setParameter("newName", newName).execute();
        return newName;
    }
    
    /**
     * Rename a folder
     */
    public static String renameFolder(String folder, String newName) {
        Ebean.createSqlUpdate(
            "update project set folder = :newName where folder = :folder"
        ).setParameter("folder", folder).setParameter("newName", newName).execute();
        return newName;
    }
    
    /**
     * Add a member to this project
     */
    public static void addMember(Integer project, String user) {
        Project p = Project.find.setId(project).fetch("members", "email").findUnique();
        p.members.add(
            User.find.ref(user)
        );
        p.saveManyToManyAssociations("members");
    }
    
    /**
     * Remove a member from this project
     */
    public static void removeMember(Integer project, String user) {
        Project p = Project.find.setId(project).fetch("members", "email").findUnique();
        p.members.remove(
            User.find.ref(user)
        );
        p.saveManyToManyAssociations("members");
    }
    
    /**
     * Check if a user is a member of this project
     */
    public static boolean isMember(Long project, String user) {
        return find.where()
            .eq("members.email", user)
            .eq("id", project)
            .findRowCount() > 0;
    } 
    
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
    
    public String getFolder() { return folder; }

    public void setFolder(String folder) { this.folder = folder; }

    public List<User> getMembers() { return members; }

    public void setMembers(List<User> members) { this.members = members; }

    public List<Task> getTasks() { return tasks; }

    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}
