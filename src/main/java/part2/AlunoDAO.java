package part2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

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

}