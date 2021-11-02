<h3>Projeto executado na disciplina de Engenharia de Software, do Programa de Mestrado em Informática 
da Universidade Federal de Alagoas (UFAL). Trata-se de um sistema SOA com integração ao ESB Mule.</h3>

<h2 align="center">Melhor Entrega</h2>

1.	DESCRIÇÃO DO SISTEMA

A solução em questão será um sistema web baseada em SOA, que pretende atender o gerenciamento de entregas de encomendas locais. Pois caso aconteça algum imprevisto na entrega, como: cliente ausente ou o entregador não encontra endereço; a outra parte interessada poderá ser avisada para evitar perda de tempo e, consequentemente, dinheiro. 
O sistema vai oferecer à empresa uma ferramenta para gerenciar as entregas, guardando informações como cliente, endereço, status do pedido, produto, entregador que realizou o serviço, etc.
A aplicação funcionará conforme o exemplo a seguir. O cliente ao comprar algo na loja, irá passar as informações para a entrega da mercadoria. O vendedor vai colher as informações do local da entrega. Após registrar a entrega, o responsável pelas entregas que pode ser o gerente, vai agendar a entrega, considerando as informações passadas pelo cliente. O cliente vai receber um email informando a data da entrega, caso não possa, poderá solicitar para remarcar a entrega ou cancelar até decidir um dia para remarcar, para ambas será enviado um email informando. 

1.1. Lista de serviços

Entre os serviços analisados para fazer parte do sistema, estão:

●	Serviço de Email: enviar email informando a data da entrega e status;
●	Serviço de Status de Compra: permite aos clientes verificar o status da sua compra;
●	Serviço Validador de Endereço: verifica se o endereço está cadastrado e correto;
●	Sistema Legado: utilizado para gerenciar as vendas e os funcionários cadastrados, disponibilizando alguns serviços, tais como, login, busca venda, etc; 
●	Sistema de Entrega: disponibiliza os serviços para gerenciar as entregas de mercadorias, tais como: cadastrar, consultar, agendar uma entrega, entre outros.


1.2. Funcionalidades
Entre as funcionalidades do sistema, estão:

●	Logar funcionários;
●	Consultar venda;
●	Consultar entrega;
●	Lista entregadores;
●	Listar entregas não agendadas;
●	Listar entregas não concluídas;
●	Modificar o status da entrega; 
●	Agendar entrega;
●	Remarca entrega;
●	Cancelar entrega;
●	Associar entrega a um entregador;
●	O sistema avisa ao usuário, através de e-mail, o dia da entrega e status da entrega.

