

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.service.ClienteService;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    @DisplayName("Testa procurar um registro no BD")
    void readById() {
        int id = 3;
        int idErrado = 10;
        Cliente cliente = new ClienteService(new ClienteDAO()).read(3);
        Cliente cliente2 = new ClienteService(new ClienteDAO()).read(10);
        assertEquals(id, cliente.getIdCliente());
        assertNull(cliente2);
    }

    @Test
    @DisplayName("Teste se o registro foi adicionado")
    void create() {
        Cliente cliente = new Cliente("Teste", "renan@gmail.com", "64992626037");
        Cliente erro = new Cliente();
        erro.setNome("erro");
        ClienteService clienteService = new ClienteService(new ClienteDAO());
        assertTrue(clienteService.createCliente(cliente));
        assertFalse(clienteService.createCliente(erro));
    }

    @Test
    @DisplayName("Teste delete")
    void delete() {
        Cliente cliente = new ClienteService(new ClienteDAO()).read(10);
        Cliente clienteErro = new ClienteService(new ClienteDAO()).read(4);
        ClienteService clienteService = new ClienteService(new ClienteDAO());
        assertTrue(clienteService.delete(cliente));
        assertNull(clienteErro);
    }
    @Test
    @DisplayName("Testa alteração de nome")
    void update() {
        ClienteService clienteService = new ClienteService(new ClienteDAO());
        Cliente cliente = clienteService.read(3);
        String  novoNome = "RenanJustino";
        cliente.setNome(novoNome);
        cliente.setTelefone("1");
        clienteService.update(cliente);
        cliente = clienteService.read(3);
        assertEquals(novoNome, cliente.getNome());
    }
}

