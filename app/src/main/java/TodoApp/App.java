package TodoApp;

import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        
        ProjectController projectController = new ProjectController();
        Project project = new Project();
        
        //project.setName("Projeto 01");
        //project.setDescription("Meu primeiro projeto");        
        //projectController.save(project);
        
        project.setName("Projeto 04");
        project.setId(4);
        project.setDescription("Meu Quarto projeto alterado");
        projectController.update(project);
        
        
        //List<Project> projects = projectController.getAll();
        //System.out.println("Total de projetos = " + projects.size());
        
        
        
        
        
        
        
        
        
        
        //Connection c = ConnectionFactory.getConnection();
        //ConnectionFactory.closeConnection(c);
        
    }
}
