package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.*;
import creamy.db.Model;
import javax.validation.constraints.NotNull;

/**
 * Task entity managed by Ebean
 */
@Entity 
public class Task extends Model {

    @Id
    private Integer id;
    
    @NotNull
    private String title;
    
    private boolean done = false;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;
    
    @ManyToOne
    private User assignedTo;
    
    private String folder;
    
    @ManyToOne
    private Project project;
    
    // -- Queries
    
    public static Model.Finder<Integer,Task> find = new Model.Finder(Integer.class, Task.class);
    
    /**
     * Retrieve todo tasks for the user.
     */
    public static List<Task> findTodoInvolving(String user) {
       return find.join("project")
           .where()
                .eq("done", false)
                .eq("project.members.email", user)
           .findList();
    }
    
    /**
     * Find tasks related to a project
     */
    public static List<Task> findByProject(Long project) {
        return Task.find.where()
            .eq("project.id", project)
            .findList();
    }
    
    /**
     * Rename a folder
     */
    public static String renameFolder(Integer project, String folder, String newName) {
        Ebean.createSqlUpdate(
            "update task set folder = :newName where folder = :folder and project_id = :project"
        ).setParameter("folder", folder)
            .setParameter("newName", newName)
            .setParameter("project", project)
            .execute();
        return newName;
    }
    
    /**
     * Create a task
     */
    public static Task create(Integer project, String folder, String title) {
        Task task = new Task();
        task.project = Project.find.ref(project);
        task.folder = folder;
        task.title = title;
        task.save();
        return task;
    }
    
    /**
     * Mark a task as done or not
     */
    public static void markAsDone(Integer taskId, Boolean done) {
        /*
        Task task = Task.find.ref(taskId);
        task.done = done;
        task.update();*/
        Ebean.createSqlUpdate(
            "update task set done = :done where id = :id"
        ).setParameter("id", taskId).setParameter("done", done).execute();
    }
    
    /**
     * Check if a user is the owner of this task
     */
    public static boolean isOwner(Integer task, String user) {
        boolean result = find.where()
            .eq("project.members.email", user)
            .eq("assigned_to_email", user)
            .eq("id", task)
            .findRowCount() > 0;
        System.out.println(result);
        return result;
    }

    public User getAssignedTo() { return assignedTo; }

    public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }

    public boolean isDone() { return done; }

    public void setDone(boolean done) { this.done = done; }

    public Date getDueDate() { return dueDate; }

    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public String getFolder() { return folder; }

    public void setFolder(String folder) { this.folder = folder; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Project getProject() { return project; }

    public void setProject(Project project) { this.project = project; }
}
