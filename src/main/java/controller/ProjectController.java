package controller;

//////////import java.awt.Component;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//////////import javax.swing.JOptionPane;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    //Criando um método para cada funcionalidade (requisito) das minhas entidadess, 
    //tal como criar, alterar e deletar um registro...
    
    public void save(Project project) {
        String sql = "INSERT INTO projects ("
                + "name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) "
                + "VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());            
            //atenção para o tipo data, pois a data do java.util.Date não
            //tem o mesmo formato do tipo data do sql, então, em vez de fazer o
            //importa para java.util, faça o import para java.sql.Date .
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            
            statement.execute(); 
            
        } catch (Exception ex) {
        ////////////} catch (SQLException ex) {
            ////////Component rootPane = null;
        
            throw new RuntimeException("Erro ao salvar o projeto "
                    + ex.getMessage(), ex);
            /////////JOptionPane.showMessageDialog(rootPane, "Erro ao salvar o projeto: " + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    
    
    public void update(Project project) {
        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conexão com DB
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            //atenção para o tipo data, pois a data do java.util.Date não
            //tem o mesmo formato do tipo data do sql, então, em vez de fazer o
            //importa para java.util, faça o import para java.sql.Date .
            statement.setDate(3, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(4, project.getId());
            
            System.out.println("sql = " + sql);
            
            statement.execute(); 
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa " 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
            
    }
    
    
    public void removeById(int idProject) throws SQLException {
        
        
        //Vai precisar remover também todas as tarefas que possuam o id
        //deste proejto.
        //criar lista com todas as tarefeas where idProject = projectId
        //e então percorrer cada tarefa para recuperar seu id
        //deleta estas tarefas com taskController.removeById(taskId);
        //somente após deletar todas as tarefas deste projeto é que
        //este projeto deverá ser deletado, com o código abaixo:
        
        
        String sql = "DELETE FROM projects WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, idProject);  // o nº 1 significa que o 1º '?' 
                                   //encontrado na String sql será substituído 
                                   //pelo valor de taskId.
            
            //Executando a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o projeto: " 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }        
    }
    
    
    
    public List<Project> getAll() {
        
        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;        
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);            
            //statement.setInt(1, idProjetct);  
            
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir a tarefa." 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }        
        
        //Lista dos registros criada com os dados do DB
        return projects;
    }
    
}
