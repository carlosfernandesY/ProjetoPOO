package test;

import org.ifgoiano.barbearia.dao.ClienteDAO;
import org.ifgoiano.barbearia.model.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteTest {
@Test
@DisplayName("teste")
    void salvarPessoaBancoDeDados() {
    Cliente cliente = new Cliente("carlos","cal","dkd");
    assertTrue(new C.create(cliente));
}

}
