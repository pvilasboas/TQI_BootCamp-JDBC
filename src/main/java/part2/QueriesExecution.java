package part2;

import java.util.List;

public class QueriesExecution {

    public static void main(String[] args){

        AlunoDAO alunoDAO = new AlunoDAO();

        //Consulta todos os alunos
        List<Aluno> alunos = alunoDAO.list();
        System.out.println("Retorno de todos os registros:");
        alunos.stream().forEach(System.out::println);

        //consulta aluno por id
        Aluno alunoParaConsulta = AlunoDAO.getById(3);
        System.out.println("Retorno da consulta por ID:");
        System.out.println(alunoParaConsulta);

        //Insert:
        Aluno alunoParaInsercao = new Aluno(
                "Matheus",
                43,
                "SP"
        );
//        alunoDAO.create(alunoParaInsercao); //Comentado to stop adding

//        Delete:
//        alunoDAO.delete(5); // Comentado para parar delete

//        Update:
        Aluno alunoParaAtualizar = alunoDAO.getById(3);
        alunoParaAtualizar.setNome("Joaquim");
        alunoParaAtualizar.setIdade(18);
        alunoParaAtualizar.setEstado("RS");

//        alunoDAO.update(alunoParaAtualizar); //comentado para parar o update

    }
}