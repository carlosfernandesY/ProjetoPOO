package test;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.ifgoiano.barbearia.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteTest {

    @Test
    @DisplayName("Testa procurar um registro no BD")
    void readById() {
        int id = 2;
        Cliente cliente = new ClienteService(new ClienteDAO()).read(2);
        assertEquals(2, cliente.getIdCliente());
    }

    @Test
    @DisplayName("Teste se o registro foi adicionado")
    void create() {
        Cliente cliente = new Cliente("Renan", "renan@gmail.com", "00000009");
        ClienteService clienteService = new ClienteService(new ClienteDAO());

    }

    @Test
    @DisplayName("Teste delete")
    void delete() {
        ClienteService clienteService = new ClienteService(new ClienteDAO());
        Cliente cliente = clienteService.read(7);
        assertTrue(clienteService.delete(cliente));
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

