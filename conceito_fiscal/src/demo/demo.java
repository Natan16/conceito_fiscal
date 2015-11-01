package demo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import conceito_fiscal.*;
import banco_dados.*;

public class demo 
{
	// Variáveis usadas para os testes
	static NF_Builder builderNF_;
	PS_Concrete produto, ps1, ps2;
	IV item, item1;
	NF myNF_;
	
	
	// Algumas classes maiores e mais complexas são criadas
	//  apenas uma vez BeforeClass, como os bancos de dados:
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		builderNF_ = new NF_Builder();
	}

	// Antes de cada teste, criamos algumas instâncias comuns
	//   para os testes, como por exemplo uma nova NF:
	@Before
	public void setUp() throws Exception {
		myNF_ = builderNF_.constructNF();
		ps1 = BDPS_Facade.getPS(3);
		ps2 = BDPS_Facade.getPS(7);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**************************************/
	//
	// Bateria de Testes de Requisitos
	//
	/**************************************/

	@Test
	public void teste_Requisito_01() {
		/**************************************/
		// Requisito #01:
		// Restrição legal: NF não pode ter zero IV. Deve ter 1 ou mais.
		/**************************************/
		// A classe builderNF se encarrega de construir uma NF com
		//   exatamente um IV inicial, sendo que ela não inicia vazia:
		myNF_ = builderNF_.constructNF();
		assertEquals(1, myNF_.sizeIVs());
		
		// Quando tentamos remover algum elemento da NF,
		//    a NF garante que nunca ficará vazia.
		myNF_.removeIV(1);
		assertEquals(1, myNF_.sizeIVs());
	}
	
	@Test
	public void teste_Requisito_02() {
		/**************************************/
		// Requisito #02:
		// Restrição legal: Todo IV deve pertencer a exatamente uma NF.
		/**************************************/
		// Os IVs são criados dentro da própria NF, garantindo que
		//   eles sempre irão pertencer a exatamente uma NF:
		item = myNF_.addNewIV(ps1, 2, 100);
		assertEquals(myNF_, item.getNF_());
		
		// Como a NF do IV é determinada em seu construtor e o campo
		//  NF de IV é privado, cada IV irá pertencer sempre àquela mesma NF. 
	}
	
	@Test
	public void teste_Requisito_03() {
		/**************************************/
		// Requisito #03:
		// Restrição legal: Todo IV se referirá a exatamente um produto ou serviço.
		/**************************************/
		// Os IVs são criados dentro do método addNewIV da NF, em que
		//   um de seus parâmetros é um dado produto PS. Assim todo
		//   IV se referirá sempre a exatamente um produto ou serviço PS.
		item = myNF_.addNewIV(ps1, 2, 100);
		produto = (PS_Concrete) item.getPS_();
		assertEquals(ps1, produto);
		
		// Posso no máximo alterar o seu produto ou serviço usando um Setter,
		//  mas não removê-lo, pois o campo PS de item é privado.
		item.setPS_(ps2);
		produto = (PS_Concrete) item.getPS_();
		assertEquals(ps2, produto);
	}
	
	@Test
	public void test_Requisito_04(){
		/**************************************/
		// Requisito #04:
		// Restrição legal: Um P/S deve sempre pertencer a um IV ou a um outro P/S.
		/**************************************/
		// [APAGAR ISSO AQUI] Explicar o DP composite, demonstrar funcionamento:
		
		// Item possui P/S:
		//item.getPS_();
		
		// P/S possui P/S:
		//produto.getPS_();
		
		fail();
	}
	
	
	@Test
	public void test_Requisito_05(){
		/**************************************/
		// Requisito #05:
		// Restrição legal: só podem ser adicionados à uma NF, P/Ss que estejam cadastrados
		//   no BD:P/S (Banco de Dados de Produtos e Serviços). Só o BD:P/S pode criar
		//   objetos P/S. O BD:P/S contém informação sobre produtos e serviços inclusive a
		//   categoria tributária de cada P/S especifico.
		/**************************************/
		// O construtor privado de P/S proíbe a instanciação de novos P/S.
		// 	Somente o BDPS pode criar novos P/S, pelo método createNewPS
	
		// produto = new PS_Concrete("P1", null); // Resulta em erro de compilação.
		// A Facde de BDPS redireciona o pedido de criação de produto à classe BDPS:
		produto = BDPS.getInstance().createNewPS("P1", "A", null);
		
		// BDPS contém uma lista de produtos e outra de Categoria Tributária,
		//   vinculadas fortemente através de um índice comum:
		assertEquals("A", BDPS_Facade.getTributeCat(produto));
	}
	
	@Test
	public void test_Requisito_06(){
		/**************************************/
		// Requisito #06:
		// Restrição legal: Uma NF é criada no estado “em elaboração” e isto deve constar de
		//	uma eventual impressão.
		/**************************************/
		// A NF criada pertence a uma classe cujo objeto é ainda mutável:
		myNF_ = builderNF_.constructNF();
		item1 = myNF_.addNewIV(ps1, 1, 10);
		assertEquals(2, myNF_.sizeIVs());

		// O método getStatus() imprime o estado atual da NF.
		assertEquals("NF em elaboração", myNF_.getStatus());
	}

	@Test
	public void test_Requisito_08(){
		/**************************************/
		// Requisito #07:
		// Restrição legal: Uma vez que esteja completamente preenchida com todos os seus
		//   IV, uma NF deve ser validada (checa requisitos e calcula todos os impostos) e
		//   armazenada no subsistema BD:NF (Banco de Dados de NF), que também se
		//   encarrega de submeter na prefeitura. Então, a NF deve passar para o estado
		//   “validada”, e deve ser então completamente imutavel. Nunca nenhum dado da NF
		//   inclusive de qualquer IV, deve ser modificado em uma NF validada. O BD:NF não
		//   deve aceitar nem validar nem gerar um ID para uma NF já validada, ou com dados
		//   inválidos. Se uma NF for corretamente validada, um objeto imutavel representandoa
		//   deve ser passado como resposta ao usuárioprogramador.
		/**************************************/
		// Já implementei a validação da NF pelo banco de dados, mas sugou fazer os testes.
		fail();
	}
}
