import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import br.com.sistemalegado.model.Funcionario;

public class Operacao {//implements Callable {


	public String name(Funcionario t) {

		System.out.print(t.getNome());
		return "Nome:" + t.getNome() + "\n" + "CPF:" + t.getCpf();
	}

}
