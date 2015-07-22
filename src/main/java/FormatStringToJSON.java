import java.util.ArrayList;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.python.antlr.ast.Str;

public class FormatStringToJSON{ 

	public String parserMessage(String venda) {


		venda = venda.substring(11);
		venda = venda.replace("]", "");
		venda = venda.replace("{", "");
		venda = venda.replace("}", "");
			
		return "{"+venda+"}";
	}

}
