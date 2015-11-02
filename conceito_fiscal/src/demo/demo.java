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
	NF_Final finalMyNF_, finalNf1_, finalNf2_;
	PS_Concrete produto_, ps1_, ps2_;
	NF myNF_, nf1_, nf2_;
	IV item_, item1_;
	
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
		ps1_ = BDPS_Facade.getPS(3);
		ps2_ = BDPS_Facade.getPS(7);
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
	public void test_Requisito_01() {
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
		myNF_.removeID(1);
		assertEquals(1, myNF_.sizeIVs());
	}
	
	@Test
	public void test_Requisito_02() {
		/**************************************/
		// Requisito #02:
		// Restri��o legal: Todo IV deve pertencer a exatamente uma NF.
		/**************************************/
		// Os IVs s�o criados dentro da pr�pria NF, garantindo que
		//   eles sempre ir�o pertencer a exatamente uma NF:
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		assertEquals(myNF_, item_.getNF_());
		
		// Como a NF do IV � determinada em seu construtor e o campo
		//  NF de IV � privado, cada IV ir� pertencer sempre �quela mesma NF. 
	}
	
	@Test
	public void test_Requisito_03() {
		/**************************************/
		// Requisito #03:
		// Restri��o legal: Todo IV se referir� a exatamente um produto ou servi�o.
		/**************************************/
		// Os IVs s�o criados dentro do m�todo addNewIV da NF, em que
		//   um de seus par�metros � um dado produto PS. Assim todo
		//   IV se referir� sempre a exatamente um produto ou servi�o PS.
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		produto_ = (PS_Concrete) item_.getPS_();
		assertEquals(ps1_, produto_);
		
		// Posso no m�ximo alterar o seu produto ou servi�o usando um Setter,
		//  mas n�o remov�-lo, pois o campo PS de item � privado.
		item_.setPS_(ps2_);
		produto_ = (PS_Concrete) item_.getPS_();
		assertEquals(ps2_, produto_);
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
		produto_ = BDPS.getInstance().createNewPS("P1", "A", null);
		
		// BDPS cont�m uma lista de produtos e outra de Categoria Tribut�ria,
		//   vinculadas fortemente atrav�s de um �ndice comum:
		assertEquals("A", BDPS_Facade.getTributeCat(produto_));
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
		item1_ = myNF_.addNewIV(ps1_, 1, 10);
		assertEquals(2, myNF_.sizeIVs());

		// O m�todo getStatus() imprime o estado atual da NF.
		assertEquals("NF em elabora��o", myNF_.getStatus());
	}

	@Test
	public void test_Requisito_07(){
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
		// Quando criada, a NF est� em estado de elabora��o, podendo ser modificada:
		myNF_ = builderNF_.constructNF();
		myNF_.addNewIV(ps1_, 2, 10);
		
		// A valida��o da NF � feito pelo BDNF, conforme mostrado abaixo:
		finalMyNF_ = BDNF_Facade.validateNF(myNF_);
		assertTrue(BDNF_Facade.contains(finalMyNF_));
		
		// O valor total de imposto j� � calculado automaticamente:
		assertEquals(43, finalMyNF_.getTotalTribute());
		// A NF obtida pertence a uma classe Final com atributos final e protegidos,
		//   de modo que uma vez validada, n�o � poss�vel alterar suas informa��es:
		assertTrue(finalMyNF_.isFinal());
		
		// � tamb�m invi�vel validar uma NF j� validada, tendo em vista
		//  que NF e NF_Final s�o duas subclasses diferentes:
		assertNull(BDNF_Facade.validateNF(finalMyNF_));
	}
	
	@Test
	public void test_Requisito_11(){
		/**************************************/
		// Requisito #11:
		// Restri��o Legal: Cada NF validada deve ter um identificador �nico, gerado durante a
		// valida��o, que nunca pode se repetir6. Uma vez validada, esse ID deve aparecer em
		// qualquer impress�o.
		/**************************************/
		// O ID �nico de cada NF � garantido pelo campo est�tico ID_ do BDNF,
		//  que � incrementado sempre que uma nova finalNF � validada.
		myNF_ = builderNF_.constructNF();
		nf1_ = builderNF_.constructNF();
		nf2_ = builderNF_.constructNF();
		
		// Cada vez que validateNF() � chamada, um novo ID incrementado � atribu�do
		finalMyNF_ = BDNF_Facade.validateNF(myNF_);
		finalNf1_ = BDNF_Facade.validateNF(nf1_);
		finalNf2_ = BDNF_Facade.validateNF(nf2_);
		
		// Observe o ID crescente das NFs validadas:
		assertEquals("NF #11 validada.", finalMyNF_.getStatus());
		assertEquals("NF #12 validada.", finalNf1_.getStatus());
		assertEquals("NF #13 validada.", finalNf2_.getStatus());
		
		// A classe NF_Abstract tamb�m possui um m�todo de impress�o,
		//  em que consta seu Status, seu ID e os seus IVs:
		System.out.println("*** Requisito # 11 ***");
		System.out.println(finalNf1_.toPrint());
		System.out.println("**********************");
	}
	
	@Test
	public void test_Requisito_12(){
		/**************************************/
		// Requisito #12:
		// [Removido]
		/**************************************/
		assertTrue(true);
	}
	
	@Test
	public void test_Requisito_14(){
		/**************************************/
		// Requisito #14:
		// Requisito do product owner: uma vez criada uma NF (antes da valida��o), os seus
		// itens de venda devem ser modificados, adicionados ou deletados apenas pelos
		// metodos apropriados. Devese cuidar que n�o haja acesso de escrita inapropriado a
		// lista de itens por outros meios.
		/**************************************/
		// A lista de IVs de NF � privada, de modo que ela somente pode ser
		//   alterada pelos m�todos apropriados, como mostrado abaixo:
		
		// A NF j� � criada com um IV:
		assertEquals(1, myNF_.sizeIVs());
		
		// Adicionando um novo IV pelo m�todo apropriado:
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		assertTrue(myNF_.containsIV(item_));
		assertEquals(2, myNF_.sizeIVs());
		
		// Removendo um IV pelo m�todo apropriado:
		myNF_.removeIV(item_);
		assertFalse(myNF_.containsIV(item_));
		assertEquals(1, myNF_.sizeIVs());
	}
	
	@Test
	public void test_Requisito_15(){
		/**************************************/
		// Requisito #15:
		// Requisito: C�digo de BD (mesmo que mockado), deve estar completamente
		// separado, desacoplado do restante do sistema, e acessivel por uma API �nica, que
		// � respons�vel por cadastros, buscas, submiss�es. Al�m disso, para simplificar logs,
		// seguran�a, e desacoplamento, deve haver apenas um objeto (objeto, n�o classe)
		// respons�vel por acessar o BD (isso representa uma restri��o como �o seu aplicativo
		// s� pode fazer uma conex�o com o BD�).
		/**************************************/
		// O c�digo dos BDs foram colocados em um pacote � parte. Os BDs s�o acessados
		//  somente mediante suas respectivas Facades, que s�o Singletons:
		assertSame(BDNF_Facade.getInstance(), BDNF_Facade.getInstance());
		assertSame(BDPS_Facade.getInstance(), BDPS_Facade.getInstance());
		
		// Os m�todos dessa Facade s�o todos est�ticos, simplificando o redirecionamento
		//  de delega��o de tarefas e evitando instancia��es desnecess�rias:
		ps1_ = BDPS_Facade.getPS(3);
		finalNf1_ = BDNF_Facade.getNF(3); 
	}
}
