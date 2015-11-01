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
	// Vari�veis usadas para os testes
	static NF_Builder builderNF_;
	PS_Concrete produto, ps1, ps2;
	IV item, item1;
	NF myNF_;
	
	
	// Algumas classes maiores e mais complexas s�o criadas
	//  apenas uma vez BeforeClass, como os bancos de dados:
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		builderNF_ = new NF_Builder();
	}

	// Antes de cada teste, criamos algumas inst�ncias comuns
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
		// Restri��o legal: NF n�o pode ter zero IV. Deve ter 1 ou mais.
		/**************************************/
		// A classe builderNF se encarrega de construir uma NF com
		//   exatamente um IV inicial, sendo que ela n�o inicia vazia:
		myNF_ = builderNF_.constructNF();
		assertEquals(1, myNF_.sizeIVs());
		
		// Quando tentamos remover algum elemento da NF,
		//    a NF garante que nunca ficar� vazia.
		myNF_.removeIV(1);
		assertEquals(1, myNF_.sizeIVs());
	}
	
	@Test
	public void teste_Requisito_02() {
		/**************************************/
		// Requisito #02:
		// Restri��o legal: Todo IV deve pertencer a exatamente uma NF.
		/**************************************/
		// Os IVs s�o criados dentro da pr�pria NF, garantindo que
		//   eles sempre ir�o pertencer a exatamente uma NF:
		item = myNF_.addNewIV(ps1, 2, 100);
		assertEquals(myNF_, item.getNF_());
		
		// Como a NF do IV � determinada em seu construtor e o campo
		//  NF de IV � privado, cada IV ir� pertencer sempre �quela mesma NF. 
	}
	
	@Test
	public void teste_Requisito_03() {
		/**************************************/
		// Requisito #03:
		// Restri��o legal: Todo IV se referir� a exatamente um produto ou servi�o.
		/**************************************/
		// Os IVs s�o criados dentro do m�todo addNewIV da NF, em que
		//   um de seus par�metros � um dado produto PS. Assim todo
		//   IV se referir� sempre a exatamente um produto ou servi�o PS.
		item = myNF_.addNewIV(ps1, 2, 100);
		produto = (PS_Concrete) item.getPS_();
		assertEquals(ps1, produto);
		
		// Posso no m�ximo alterar o seu produto ou servi�o usando um Setter,
		//  mas n�o remov�-lo, pois o campo PS de item � privado.
		item.setPS_(ps2);
		produto = (PS_Concrete) item.getPS_();
		assertEquals(ps2, produto);
	}
	
	@Test
	public void test_Requisito_04(){
		/**************************************/
		// Requisito #04:
		// Restri��o legal: Um P/S deve sempre pertencer a um IV ou a um outro P/S.
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
		// Restri��o legal: s� podem ser adicionados � uma NF, P/Ss que estejam cadastrados
		//   no BD:P/S (Banco de Dados de Produtos e Servi�os). S� o BD:P/S pode criar
		//   objetos P/S. O BD:P/S cont�m informa��o sobre produtos e servi�os inclusive a
		//   categoria tribut�ria de cada P/S especifico.
		/**************************************/
		// O construtor privado de P/S pro�be a instancia��o de novos P/S.
		// 	Somente o BDPS pode criar novos P/S, pelo m�todo createNewPS
	
		// produto = new PS_Concrete("P1", null); // Resulta em erro de compila��o.
		// A Facde de BDPS redireciona o pedido de cria��o de produto � classe BDPS:
		produto = BDPS.getInstance().createNewPS("P1", "A", null);
		
		// BDPS cont�m uma lista de produtos e outra de Categoria Tribut�ria,
		//   vinculadas fortemente atrav�s de um �ndice comum:
		assertEquals("A", BDPS_Facade.getTributeCat(produto));
	}
	
	@Test
	public void test_Requisito_06(){
		/**************************************/
		// Requisito #06:
		// Restri��o legal: Uma NF � criada no estado �em elabora��o� e isto deve constar de
		//	uma eventual impress�o.
		/**************************************/
		// A NF criada pertence a uma classe cujo objeto � ainda mut�vel:
		myNF_ = builderNF_.constructNF();
		item1 = myNF_.addNewIV(ps1, 1, 10);
		assertEquals(2, myNF_.sizeIVs());

		// O m�todo getStatus() imprime o estado atual da NF.
		assertEquals("NF em elabora��o", myNF_.getStatus());
	}

	@Test
	public void test_Requisito_08(){
		/**************************************/
		// Requisito #07:
		// Restri��o legal: Uma vez que esteja completamente preenchida com todos os seus
		//   IV, uma NF deve ser validada (checa requisitos e calcula todos os impostos) e
		//   armazenada no subsistema BD:NF (Banco de Dados de NF), que tamb�m se
		//   encarrega de submeter na prefeitura. Ent�o, a NF deve passar para o estado
		//   �validada�, e deve ser ent�o completamente imutavel. Nunca nenhum dado da NF
		//   inclusive de qualquer IV, deve ser modificado em uma NF validada. O BD:NF n�o
		//   deve aceitar nem validar nem gerar um ID para uma NF j� validada, ou com dados
		//   inv�lidos. Se uma NF for corretamente validada, um objeto imutavel representandoa
		//   deve ser passado como resposta ao usu�rioprogramador.
		/**************************************/
		// J� implementei a valida��o da NF pelo banco de dados, mas sugou fazer os testes.
		fail();
	}
}
