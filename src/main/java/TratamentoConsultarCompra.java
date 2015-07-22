import br.com.sistemalegado.model.Funcionario;
import br.com.sistemalegado.model.Venda;


public class TratamentoConsultarCompra {

	public String tratamentoObjeto(Venda venda) {

		return "Nome:"+venda.getNome()+"\nProduto:"+venda.getProduto()+"\nValor:"+venda.getValor();
	}
}
