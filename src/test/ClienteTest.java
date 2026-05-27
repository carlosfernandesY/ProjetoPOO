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
        assertEquals(2,cliente.getIdCliente());
      }
      @Test
      void testeCreate(){
         Cliente  cliente = new Cliente("carlos","carlosroberto6870@gmail.com","99262037");
         ClienteService clienteService = new ClienteService(new ClienteDAO());
         clienteService.createCliente(cliente);
        }
        //Teste básico para ver se todo CRUD funciona
//        Cliente cliente = new Cliente("Gui", "guiSteeam@outlook.com", "1234-5678");
//        ClienteService clienteService = new ClienteService(new ClienteDAO());
//        clienteService.createCliente(cliente);
    }

