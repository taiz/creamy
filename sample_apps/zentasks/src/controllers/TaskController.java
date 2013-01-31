package controllers;

import creamy.annotation.Bind;
import creamy.mvc.Controller;
import creamy.mvc.Result;
import java.util.*;
import models.Project;
import models.Task;
import views.taskcontroller.Folder;
import views.taskcontroller.Item;


public class TaskController extends Controller {
    private Project project;
    private Map<String,List<Task>> folders = new HashMap<String,List<Task>>();
    
    public Result index(Integer projectid) {
        project = Project.find.byId(projectid);
        for (Task task : project.getTasks()) {
            if (folders.containsKey(task.getFolder()))
                folders.get(task.getFolder()).add(task);
            else
                folders.put(task.getFolder(), new ArrayList<Task>(Arrays.asList(task)));
        }
        return ok(this);
    }
    
    private Task task;
    private Boolean editable;
    
    public Result add(Integer project, String folder, @Bind("title") String title) {
        task = Task.create(project, folder, title);
        editable = true;
        return ok(Item.class);
    } 

    public Result update(Integer id, @Bind("done") Boolean done) {
        Task.markAsDone(id, done);
        return ok();
    }
    
    public Result delete(Integer task) {
        Task.find.ref(task).delete();
        return ok();
    }
    
    private String folder;
    private List<Task> tasks = new ArrayList<Task>();
    
    public Result addFolder(Integer projectId) {
        project = Project.find.ref(projectId);
        folder = "New Folder";
        return ok(Folder.class);
    }
    
    public Result renameFolder(Integer project, String folder, @Bind("name") String name) {
        Task.renameFolder(project, folder, name);
        return ok();
    }
}
