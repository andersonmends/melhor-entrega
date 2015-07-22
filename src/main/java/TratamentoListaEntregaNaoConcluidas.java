import java.util.ArrayList;

import model.Entrega;
import br.com.sistemalegado.model.Funcionario;

public class TratamentoListaEntregaNaoConcluidas {

	public String name(ArrayList<Entrega> t) {

		String u ="";
		for (Entrega entrega : t) {

			u = u + "Nome: " + entrega.getNomeCliente() + "\nProduto: "
					+ entrega.getProduto() + "\nEndereço: "
					+ entrega.getEndereco() + "\nStatus: "
					+ entrega.getStatus() + "\nData de Cadastro: "
					+ entrega.getDataCadastro() + "\nTelefone: "
					+ entrega.getTelefone() + "\n\n";
		}

		return u;
	}
}
