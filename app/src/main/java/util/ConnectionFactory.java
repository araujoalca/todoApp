package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3307/tarefasprojeto";
    public static final String USER = "root";
    public static final String PASS = "";
    
    //crie a dependência odbc, a qual não vem com as dependências padrão
    //das bibliotecas do pacote "Java Dependences".
    //Quem cuida disso é o gradle, que a gente escolheu ao criar 
    //o nosso projeto java, e devemos informá-lo de que iremos usar o jdbc.
    //Vá em: Build Scripts --> build.gradle e abra-o com um clique duplo.
    //a aba Nome-do-Projeto:app irá abir para edição.
    //Na sessão dependencies { ... } acrescente a seguinte linha:
        //------------
        //antes procure no google "mysql-connector-java gradle"
        //e entre no link do site mvnrepository
        //e copie o códito que está na aba Gradle:
        //------------
    //    //https://mvnrepository.com/artifact/mysql/mysql-connector-java
    //implementation group: 'mysql', name: 'mysql-connector-java', version: '5.1.13'
        
    
    //Criando os métodos para manipular o db:
    
    //Cria a conexão com o db. Qdo for chamada, precisará ser fechada depois.
    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            throw new RuntimeException("Erro de conexão com o banco de dados.", ex);
        }
    }
    
    public static void closeConnection(Connection connection){
       try{
           if (connection != null) {
               connection.close();
           }
       } catch (Exception ex) {
           throw new RuntimeException("Erro ao fechar a conexão com o banco de "
                   + "dados", ex);
       } 
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement){
       try{
           if (connection != null) {
               connection.close();
           }
           
           if(statement != null) {
               statement.close();
           }
       } catch (Exception ex) {
           throw new RuntimeException("Erro ao fechar a conexão com o banco de "
                   + "dados", ex);
       } 
    }
    
    public static void closeConnection(Connection connection, 
            PreparedStatement statement, ResultSet resultSet){
       try{
           if (connection != null) {
               connection.close();
           }
           
           if(statement != null) {
               statement.close();
           }
           
           if(resultSet != null){
               resultSet.close();
           }
       } catch (Exception ex) {
           throw new RuntimeException("Erro ao fechar a conexão com o banco de "
                   + "dados", ex);
       } 
    }
    
}
