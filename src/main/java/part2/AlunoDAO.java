package part2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static Aluno getById(int id) {

        Aluno aluno = new Aluno();
        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM aluno WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));;
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e){
            System.out.println("listagem falhou");
            e.printStackTrace();
        }

        return aluno;
    }

    //lista todos os alunos cadastrados no banco
    public List<Aluno> list(){

        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM aluno";

            PreparedStatement stmt = conn.prepareCall(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String estado = rs.getString("estado");

                alunos.add(new Aluno(
                        id,
                        nome,
                        idade,
                        estado
                ));
            }

        } catch (SQLException e) {
            System.out.println("listagem de alunos falhou");
            e.printStackTrace();
        }
        return alunos;
    }

    public void create(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Insercão realizada com sucesso");
        } catch (SQLException e){
            System.out.println("Insercao falhou");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "DELETE FROM aluno WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Deleção bem sucedida");

        } catch (SQLException e){
            System.out.println("Delecao falhou");
            e.printStackTrace();
        }

    }

    public void update(Aluno aluno) {

        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());
            stmt.setInt(4,aluno.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Update realizado com sucesso");
        }catch (SQLException e){
            System.out.println("Update falhou");
            e.printStackTrace();
        }

    }
}