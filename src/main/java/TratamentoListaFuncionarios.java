import java.util.ArrayList;
import java.util.Collection;

import model.Entrega;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import br.com.sistemalegado.model.Funcionario;


public class TratamentoListaFuncionarios {//implements Callable {
	
	
	
	public String name(ArrayList<Funcionario> t) {
		
		String u ="";
		for (Funcionario funcionario : t) {

			u = u + "Entregador: " + funcionario.getNome()+ "\n\n";
		}
		
		
		return u;
	}

}
