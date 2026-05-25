package org.ifgoiano;

import org.ifgoiano.connection.ConnectionFactory;
import org.ifgoiano.dao.ClienteDAO;
import org.ifgoiano.model.Cliente;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
          Cliente clt = new ClienteDAO().readById(1);
          clt.setNome("Renan");
          clt.setTelefone("111-222-3333");
          new ClienteDAO().deleteById(clt);

    }
}
