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

2. DIAGRAMA

Abaixo, está o diagrama dos serviços utilizados para montar a aplicação “Melhor Entrega”.

![image](https://user-images.githubusercontent.com/10708492/139909795-39198ab2-a789-404b-bd0d-35b2ba3f34a6.png)
 

3. FLUXOS

Lista de Entregadores: fornece ao gerente uma lista de entregadores através de uma consulta aos webservices (WS) do sistema legado. Tal serviço é utilizado para atrelar o id do entregador à uma entrega. 

Logar Funcionário: permite logar um funcionário através dos campos de login e senha, chamando um WS para isso. 

Consultar Compra: esse fluxo fornece o serviço de Consulta do Status da Entrega. Para isso, ele utiliza os dois WS, um para consultar informações da venda do produto e outro WS verificar as informações da entrega.

Cadastrar Entrega: esse fluxo permite que ao vendedor cadastrar uma entrega, utilizado o WS do sistema legado para buscar as informações da venda e o WS do sistema de entrega para cadastrar no BD.

Agendar Entrega: esse fluxo permite ao gerente agendar uma entrega, faz uso de WS para isso, e após seu uso o fluxo manda um email de notificação para o cliente com as informações da entrega

Entrega Não Agendada: é disponibilidade uma lista de entregas que não foram agendadas, para o gerente fazer o agendamento.

Entrega Não Concluídas: é disponibilidade uma lista de entregas que não foram concluídas.

Remarcar Entrega: permite ao gerente remarcar uma entrega.

Cancelar Entrega: permite o cancelamento de uma entrega.

Validar CEP: esse fluxo faz uso do WS dos correios para validar um CEP de entrega passado por um cliente, o fluxo cadastrar entrega deve chamar esse fluxo para validar o CEP antes fazer o cadastro no BD.

Entrega Concluída: permite ao entregador modificar o status da compra para concluída quando ele finalizar a entrega.

Associar Entrega: esse fluxo permite ao gerente escolher um entregador para definir uma entrega a ele.

4. DESENVOLVIMENTO

O sistema é composto por dois WS, que foram implementados e publicado utilizando Java, com uso da biblioteca JAX-WS e com apoio da IDE Eclipse. Além de um WS dos Correios, no caso para validar CEP. Também foi utilizada a ferramenta Mule ESB (Entreprise Service Bus) para a integração e orquestração do sistema.
O uso do Mule foi um pouco complexo, pois é uma ferramenta com muitas possibilidades de uso, isso requer muita prática para um domínio mais eficaz. A maior dificuldade foi criar subfluxos que pudessem acessar diversos WS em paralelo e depois fazer a junção de forma correta. Na maioria das vezes, a junção dos fluxos retornava um XML ou JSON mal formatado, não deixando os transfomers padrão funcionando como devia. Diante disso, para contornar esse problema, foi necessário utilizar uma classe Java para formartar a junção e deixá-la como um JSON bem formatado, e posteriormente usar um transformers adequadamente .
O serviço de email implementado sofreu dificuldade de ser integrado ao Mule, devido a um bug do Mule, que não reconhece o caractere @ no conector SMTP, tendo que ser substituído pelo %40. 
Em relação a implementação do WS, mais precisamente nas respostas, houve dificuldades pois as anotações, que servem para configurar nomes de operações, parâmetros, etc, não foram utilizadas. Essa ausência de configuração, impactou justamente na formatação da junção dos subfluxos. Talvez com uma configuração melhor, as dificuldades poderiam ser bem menores.  

4.1 Requisitos de Qualidade

Para garantir a robustez do sistema diante de entradas inesperadas e ataques indesejados, algumas práticas foram adotadas, tais como: validação dos dados que são inseridos no banco (para garantir a qualidade dos dados) e a utilização de “prepareStatement” (para evitar SQL injection). 
A primeira, utiliza alguns métodos de verificação. Entre esses métodos estão o validaEntrega (Entrega entrega) e o validaData(String data). O validaEntrega verifica se o cpf, o endereço, o nome do cliente, o produto e o telefone não são null ou vazios (""). Também verifica se o Id da venda não é <= 0 (que corresponde a uma venda inválida) e se a data de cadastro é válida (não sendo null, vazia e nem anterior ao dia atual). Caso não passe nessas validações, a entrega não pode ser inserida no banco. A validação das datas é realizada pelo método validaData, que verifica se o número de dias e meses estão dentro do intervalo correto.
A segunda, é uso do cláusula prepareStatement, em que se busca evitar ataques de injeção de SQL no código. Assim, toda a camada DAO dos dois WS desenvolvidos nesse projeto, foi implementadas usando essa cláusula.

5. TESTES
	 	 	 	
Para a campanha de testes do sistema, como ferramenta de apoio foi utilizado o SoapUI e os serviços que foram testados são: o “cadastrarEntrega” e o “agendarEntrega”. Esses serviços foram escolhidos devido, respectivamente, à complexidade e a semelhança com os demais serviços. Para o desenvolvimento dos testes, foi escolhida a abordagem de testes caixa preta utilizando particionamento de equivalência e análise de vetor limite.
O método cadastrarEntrega recebe como parâmetro uma classe Entrega. Tal classe possui 14 atributos (id_entrega, id_venda,  nome_cliente, telefone, cpf, status, melhorHorario, endereco, dataCadastro, dataEntrega, produto, entregador, ordemEntrega, observacao), porém serão analisados apenas os atributos:
●	id_venda (int), 
●	dataCadastro (String no formato dd/mm/aa ou dd/mm/aaaa), 
●	cpf (String).

Abaixo, estão as tabelas mostrando as partições de equivalência dos atributos selecionados para a realização dos testes. As linhas com a cor preta correspondem as partições de equivalências válidas. Também buscou-se explorar as fronteiras das partições.

Partição de equivalência para o atributo id:

![image](https://user-images.githubusercontent.com/10708492/139910114-cc976d7d-02de-4e71-8882-b26e322cd788.png)

Partição de equivalência para o atributo data:

![image](https://user-images.githubusercontent.com/10708492/139910161-d3673b55-f946-4bd7-8595-29a41fde5f12.png)

Partição de equivalência para o atributo cpf (string):

![image](https://user-images.githubusercontent.com/10708492/139910225-4695e2c1-cc23-49f7-acd5-2616bad42edc.png)

Partição de equivalência para outras situações:

![image](https://user-images.githubusercontent.com/10708492/139910276-ed0c5f3f-6f57-4568-a64f-69dd29b9a835.png)

Para a criação dos casos de testes, considerou a data “hoje” como “16/07/15”. Por isso, a data do sistema operacional que estiver executando o sistema de entrega deverá ser alterada para “16/07/15”, visando não interferir nos testes.
Dessa forma, para executar os casos de testes, é recomendado seguir os seguintes passos: 
1.	Apagar e criar novamente o banco do sistema de entrega;
2.	Alterar a data do sistema onde o sistema de entrega está executando;
3.	Executar os casos de testes.

Como o método agendarEntrega tem como parâmetros os atributos id_venda e data, as partições de equivalência das tabelas acima foram utilizadas para criar os seguintes casos de testes:
(Obs.: as tuplas abaixo estão estruturadas assim: 
< nome_do_método (parâmetros), retorno_esperado, retorno_recebido > )


Casos inválidos
1 < agendarEntrega (11, 11/08/15),   		"false", "true" >
2 < agendarEntrega (1000000, 11/08/15),   	"false", "true" >
3 < agendarEntrega (-1, 11/08/15),    		"false", "false" >
4 < agendarEntrega (-1000000, 11/08/15), 	"false", "false" >
5 < agendarEntrega (0, 11/08/15),     		"false", "false" >
6 < agendarEntrega (1, 111/08/15),    		"false", "false" >
7< agendarEntrega (1, 11/08/A),      		"false", "false" >
8 < agendarEntrega (111, 11/08/2015), 		"false", "false" >
9 < agendarEntrega (A, 11/08/15), 		"false", "false" >
10< agendarEntrega (Texto, 11/08/15), 		"false", "false" >
11< agendarEntrega (1, 11/07/1915), 		"false", "false" >
12< agendarEntrega (1, 15/07/15), 		"false", "false" >
13< agendarEntrega (1, 17/07/15), 		"false", "false" >
14< agendarEntrega (1, 15/07/15), 		"false", "false" >
15< agendarEntrega (1, 18/08/15), 		"false", "true" >
16< agendarEntrega (1, A), 			"false", "false" >
17< agendarEntrega (1, Texto), 			"false", "false" >
18< agendarEntrega (1, 16/07/15), 		"false", "false" >

Casos válidos:
19< agendarEntrega (1, 17/08/15), 		“true”, “true” >
20< agendarEntrega (1, 11/08/15), 		“true”, “true” >
21< agendarEntrega (1, 11/08/2015),		“true”, “true” > 

Os casos de teste em que o retorno não foram o esperado correspondem as situações em que o sistema não funcionou de forma adequada, são eles: 1, 2, 15. Os demais, o sistema funcionou bem diante das entradas passadas.

Analisando os casos de testes criados para a método cadastarEntrega(Entrega entrega), tem-se:

(OBS.: como a classe Entrega possui vários atributos, será considerados apenas os que variam em relação a analise de particionamento de equivalência, são eles: id_venda, dataCadastro,  cpf. )

1< cadastrarEntrega(054.598.914-00, -1000000, 16/07/2015),   null, null > 
2< cadastrarEntrega(054.598.914-00, -1, 16/07/2015),     	null, null >
3< cadastrarEntrega(054.598.914-00, 0, 16/07/2015),      	null, null >
4< cadastrarEntrega(054.598.914-00, 1000000, 16/07/2015),null, true> 
5< cadastrarEntrega(054.598.914-00, A, 16/07/2015),      	null, null >
6< cadastrarEntrega(054.598.914-00, Texto, 16/07/2015),  	null, null >
7< cadastrarEntrega(054.598.914-00, 1, 16/07/2015),      	true, true > 
8< cadastrarEntrega(054.598.914-00, 1, 15/07/2015),      	null, null >
9< cadastrarEntrega(054.598.914-00, 1, 15/07/1915),      	null, null >
10< cadastrarEntrega(054.598.914-00, 2, 16/07/2015),      	true, true > 
11< cadastrarEntrega(054.598.914-00, 3, 17/07/2015),      	null, null > 
12< cadastrarEntrega(054.598.914-00, 4, 15/08/2015),      	null, null > 
13< cadastrarEntrega(054.598.914-00, 5, 16/08/2015),      	null, null > 
15< cadastrarEntrega(054.598.914-00, 1, A),               	null, null > 
16< cadastrarEntrega(054.598.914-00, 1, Texto),           	null, null >
17< cadastrarEntrega(054.598.914-00, 1, 32/07/2015),      	null, null >
18< cadastrarEntrega(054.598.914-00, 1, 15/13/2015),      	null, null >
19< cadastrarEntrega(054.598.914-00, 1, 15/12/12015),     	null, null >
20< cadastrarEntrega("", 1, 16/12/12015),                 		null, null >
21< cadastrarEntrega(05459891400, 1, 16/12/12015),        	true, true >
22< cadastrarEntrega(054.598.914-00, 1, 16/12/12015),     	true, true >
23< cadastrarEntrega(C54.598.914-00, 1, 16/12/12015),     	null, null >
24< cadastrarEntrega(054.598.914-0A, 1, 16/12/12015),     	null, null >
25< cadastrarEntrega(~54.598.914-00, 1, 16/12/12015),     	null, null >
26< cadastrarEntrega(054.598.914-~0, 1, 16/12/12015),     	null, null >
//testa se é possível adicionar se uma entrega cadastrada pode ser adicionada no banco
27< cadastrarEntrega(054.598.914-00, 1, 16/12/12015),     	null, true > 

Os casos em destaque não funcionaram bem, conforme o esperado. Diante desses testes, verificou-se que o sistema não está preparado para verificar se uma determinada entrega já está cadastrada no banco de entregas (caso 27) ou se uma venda foi realizada e está cadastrada no banco do sistema que gerencia as vendas (caso 4).


