package controllers;

import creamy.annotation.Bind;
import creamy.mvc.Controller;
import creamy.mvc.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import models.Project;
import models.User;
import views.projectcontroller.Dashboard;
import views.projectcontroller.Group;
import views.projectcontroller.Item;
import views.projectcontroller.TeamItem;


public class ProjectController extends Controller {
    private List<Project> projects;
    private HashMap<String,List<Integer>> projectGroups = new HashMap<String,List<Integer>>();
    private User user;
    
    public Result index() {
        String email = (String)getContinualData().get("email");
        projects = Project.findInvolving(email);
        for (Project project : projects) {
            String folder = project.getFolder();
            if (projectGroups.containsKey(folder))
                projectGroups.get(folder).add(project.getId());
            else
                projectGroups.put(folder, new ArrayList<Integer>(Arrays.asList(project.getId())));
        }
        user = User.find.byId(email);
        return ok(Dashboard.class);
    }
    
    private String group;
    
    public Result group(String group) {
        this.group = group;
        return ok(this);
    }
    
    public Result addGroup() {
        this.group = "New Group";
        return ok(Group.class);
    }
    
    public Result renameGroup(String oldGroup, String newGroup) {
        Project.renameFolder(oldGroup, newGroup);
        return ok();
    }
    private Project project;
    
    public Result item(Integer id) {
        project = Project.find.byId(id);
        return ok(this);
    }
    
    public Result delete(Integer id) {
        Project.find.ref(id).delete();
        return ok();
    }
    
    public Result add(String group) {
        project = Project.create(
            "New project", group, (String)getContinualData().get("email"));
        return ok(Item.class);
    }
    
    public Result rename(Integer project, String name) {
        Project.rename(project, name);
        return ok();
    }
 
    public Result deleteGroup(String group) {
        Project.deleteInFolder(group);
        return ok();
    }
    
    private List<User> projectMembers;
    private List<User> remainUsers;
    private Integer projectId;

    public Result teamMenu(Integer projectId) {
        projectMembers = Project.find.ref(projectId).getMembers();
        remainUsers = User.findExcluded(projectMembers);
        this.projectId = projectId;
        return ok(this);
    }
    
    private Boolean isMember;
    
    public Result addUser(Integer project, @Bind("email") String email) {
        Project.addMember(project, email);
        user = User.find.ref(email);
        isMember = true;
        return ok(TeamItem.class);
    }
    
    public Result removeUser(Integer project, @Bind("email") String email) {
        Project.removeMember(project, email);
        user = User.find.ref(email);
        isMember = false;
        return ok(TeamItem.class); 
    }
}
