package org.ifgoiano.barbearia.dao;

import org.ifgoiano.barbearia.connection.ConnectionFactory;
import org.ifgoiano.barbearia.dao.interfaceClass.EntidadeDAO;
import org.ifgoiano.barbearia.model.Atendimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
========================================================
CLASSE: AtendimentoDAO
RESPONSABILIDADE:
Realiza operações CRUD da entidade Atendimento
no banco de dados utilizando JDBC.
========================================================
*/
public class AtendimentoDAO implements EntidadeDAO<Atendimento> {
    Connection connection;

    public AtendimentoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

<<<<<<< Updated upstream
    @Override
    public boolean create(Atendimento object) {
        String sql = "INSERT INTO Atendimento(idClienteFK,idBarbeiro,data,valorTotal) VALUES(?, ?, ?, ?);";
=======
    /*
  ----------------------------------------------------
  CREATE

  Insere um novo atendimento no banco de dados.

  Retorno:
  - true  -> inserção realizada com sucesso
  - false -> falha na inserção
  ----------------------------------------------------
  */
    @Override
    public boolean create(Atendimento object) {
        String sql = "INSERT INTO Atendimento(idClienteFK,idBarbeiroFK,data,valorTotal) VALUES(?, ?, ?, ?);";
>>>>>>> Stashed changes
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, object.getCliente().getIdCliente());
            preparedStatement.setInt(2, object.getBarbeiro().getIdBarbeiro());
            preparedStatement.setDate(3, object.getData());
            preparedStatement.setDouble(4, object.getValorTotal());
<<<<<<< Updated upstream
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
=======
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar atendimento", e);
        }
>>>>>>> Stashed changes
    }

    /*
  ----------------------------------------------------
  READ BY ID

  Busca um atendimento pelo ID.

  Parâmetro:
  - id -> identificador do atendimento

  Retorno:
  - objeto Atendimento encontrado
  - null caso não exista
  ----------------------------------------------------
  */
    @Override
    public Atendimento readById(Integer id) {

        String sql = "SELECT * FROM Atendimento WHERE idAtendimento = ?;";

        Atendimento atendimento = null;
<<<<<<< Updated upstream
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            atendimento = new Atendimento();
            atendimento.setIdAtendimento(resultSet.getInt("idAtendimento"));
            atendimento.setData(resultSet.getDate("data"));
            atendimento.setValorTotal(resultSet.getDouble("valorTotal"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
=======

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                atendimento = new Atendimento();

                atendimento.setIdAtendimento(
                        resultSet.getInt("idAtendimento")
                );

                atendimento.setData(
                        resultSet.getDate("data")
                );

                atendimento.setValorTotal(
                        resultSet.getDouble("valorTotal")
                );

                ClienteDAO clienteDAO = new ClienteDAO();
                BarbeiroDAO barbeiroDAO = new BarbeiroDAO();

                atendimento.setCliente(
                        clienteDAO.readById(
                                resultSet.getInt("idClienteFK")
                        )
                );

                atendimento.setBarbeiro(
                        barbeiroDAO.readById(
                                resultSet.getInt("idBarbeiroFK")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar atendimento", e);
>>>>>>> Stashed changes
        }
        return atendimento;
    }

    /*
    ----------------------------------------------------
    DELETE

    Remove um atendimento do banco de dados.

    Retorno:
    - true  -> remoção realizada
    - false -> falha na remoção
    ----------------------------------------------------
    */
    @Override
    public boolean deleteById(Atendimento atendimento) {
<<<<<<< Updated upstream
        String sql = "DELET FROM Atendiente WHERE idAtendimento = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, atendimento.getIdAtendimento());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
=======

        String sql = "DELETE FROM Atendimento WHERE idAtendimento = ?;";

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, atendimento.getIdAtendimento());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

>>>>>>> Stashed changes
        return false;
    }

    /*
   ----------------------------------------------------
   UPDATE

   Atualiza os dados de um atendimento existente.

   Retorno:
   - true  -> atualização realizada
   - false -> falha na atualização
   ----------------------------------------------------
   */
    @Override
<<<<<<< Updated upstream
    public void updateById(Atendimento object) {
        String sql = "UPDATE Atendimento SET data = ?, valorTotal = ? WHERE idAtendimento = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, object.getData());
            preparedStatement.setDouble(2, object.getValorTotal());
            preparedStatement.setInt(3, object.getIdAtendimento());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
=======
    public boolean updateById(Atendimento object) {
        String sql = "UPDATE Atendimento SET idClienteFK = ?, idBarbeiroFK = ?, data = ?, valorTotal = ? WHERE idAtendimento = ?;";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, object.getCliente().getIdCliente());
            preparedStatement.setInt(2, object.getBarbeiro().getIdBarbeiro()); // CORRIGIDO: Removida duplicidade do índice 2
            preparedStatement.setDate(3, object.getData());
            preparedStatement.setDouble(4, object.getValorTotal());
            preparedStatement.setInt(5, object.getIdAtendimento());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar atendimento: " + e.getMessage());
            return false;
        }
    }
    /*
   ----------------------------------------------------
   READ ALL

   Busca todos os atendimentos cadastrados.

   Retorno:
   - Lista contendo todos os atendimentos
   ----------------------------------------------------
   */
    public List<Atendimento> readAll() {
        String sql = "SELECT * FROM Atendimento;";
        List<Atendimento> atendimentos = new ArrayList<>();

        // Instancia os DAOs fora do laço de repetição para otimizar o desempenho do banco
        ClienteDAO clienteDAO = new ClienteDAO();
        BarbeiroDAO barbeiroDAO = new BarbeiroDAO();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setIdAtendimento(resultSet.getInt("idAtendimento"));
                atendimento.setData(resultSet.getDate("data"));
                atendimento.setValorTotal(resultSet.getDouble("valorTotal"));

                // Vincula os objetos completos de Cliente e Barbeiro associados ao atendimento
                atendimento.setCliente(clienteDAO.readById(resultSet.getInt("idClienteFK")));
                atendimento.setBarbeiro(barbeiroDAO.readById(resultSet.getInt("idBarbeiro")));

                atendimentos.add(atendimento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar atendimentos: " + e.getMessage());
>>>>>>> Stashed changes
        }

        return atendimentos;
    }
}
