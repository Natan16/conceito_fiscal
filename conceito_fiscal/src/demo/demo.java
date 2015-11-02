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
	NF_Final finalMyNF_, finalNf1_, finalNf2_;
	PS_Concrete produto_, ps1_, ps2_;
	NF myNF_, nf1_, nf2_;
	IV item_, item1_;
	
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
		// Restrição legal: NF não pode ter zero IV. Deve ter 1 ou mais.
		/**************************************/
		// A classe builderNF se encarrega de construir uma NF com
		//   exatamente um IV inicial, sendo que ela não inicia vazia:
		myNF_ = builderNF_.constructNF();
		assertEquals(1, myNF_.sizeIVs());
		
		// Quando tentamos remover algum elemento da NF,
		//    a NF garante que nunca ficará vazia.
		myNF_.removeID(1);
		assertEquals(1, myNF_.sizeIVs());
	}
	
	@Test
	public void test_Requisito_02() {
		/**************************************/
		// Requisito #02:
		// Restrição legal: Todo IV deve pertencer a exatamente uma NF.
		/**************************************/
		// Os IVs são criados dentro da própria NF, garantindo que
		//   eles sempre irão pertencer a exatamente uma NF:
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		assertEquals(myNF_, item_.getNF_());
		
		// Como a NF do IV é determinada em seu construtor e o campo
		//  NF de IV é privado, cada IV irá pertencer sempre àquela mesma NF. 
	}
	
	@Test
	public void test_Requisito_03() {
		/**************************************/
		// Requisito #03:
		// Restrição legal: Todo IV se referirá a exatamente um produto ou serviço.
		/**************************************/
		// Os IVs são criados dentro do método addNewIV da NF, em que
		//   um de seus parâmetros é um dado produto PS. Assim todo
		//   IV se referirá sempre a exatamente um produto ou serviço PS.
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		produto_ = (PS_Concrete) item_.getPS_();
		assertEquals(ps1_, produto_);
		
		// Posso no máximo alterar o seu produto ou serviço usando um Setter,
		//  mas não removê-lo, pois o campo PS de item é privado.
		item_.setPS_(ps2_);
		produto_ = (PS_Concrete) item_.getPS_();
		assertEquals(ps2_, produto_);
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
		// A Facade de BDPS redireciona o pedido de criação de produto à classe BDPS:
		produto_ = BDPS.getInstance().createNewPS("P1", "A", null);
		
		// BDPS contém uma lista de produtos e outra de Categoria Tributária,
		//   vinculadas fortemente através de um índice comum:
		assertEquals("A", BDPS_Facade.getTributeCat(produto_));
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
		item1_ = myNF_.addNewIV(ps1_, 1, 10);
		assertEquals(2, myNF_.sizeIVs());

		// O método getStatus() imprime o estado atual da NF.
		assertEquals("NF em elaboração", myNF_.getStatus());
	}

	@Test
	public void test_Requisito_07(){
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
		// Quando criada, a NF está em estado de elaboração, podendo ser modificada:
		myNF_ = builderNF_.constructNF();
		myNF_.addNewIV(ps1_, 2, 10);
		
		// A validação da NF é feito pelo BDNF, conforme mostrado abaixo:
		finalMyNF_ = BDNF_Facade.validateNF(myNF_);
		assertTrue(BDNF_Facade.contains(finalMyNF_));
		
		// O valor total de imposto já é calculado automaticamente:
		assertEquals(43, finalMyNF_.getTotalTribute());
		// A NF obtida pertence a uma classe Final com atributos final e protegidos,
		//   de modo que uma vez validada, não é possível alterar suas informações:
		assertTrue(finalMyNF_.isFinal());
		
		// É também inviável validar uma NF já validada, tendo em vista
		//  que NF e NF_Final são duas subclasses diferentes:
		assertNull(BDNF_Facade.validateNF(finalMyNF_));
	}
	
	@Test
	public void test_Requisito_11(){
		/**************************************/
		// Requisito #11:
		// Restrição Legal: Cada NF validada deve ter um identificador único, gerado durante a
		// validação, que nunca pode se repetir6. Uma vez validada, esse ID deve aparecer em
		// qualquer impressão.
		/**************************************/
		// O ID único de cada NF é garantido pelo campo estático ID_ do BDNF,
		//  que é incrementado sempre que uma nova finalNF é validada.
		myNF_ = builderNF_.constructNF();
		nf1_ = builderNF_.constructNF();
		nf2_ = builderNF_.constructNF();
		
		// Cada vez que validateNF() é chamada, um novo ID incrementado é atribuído
		finalMyNF_ = BDNF_Facade.validateNF(myNF_);
		finalNf1_ = BDNF_Facade.validateNF(nf1_);
		finalNf2_ = BDNF_Facade.validateNF(nf2_);
		
		// Observe o ID crescente das NFs validadas:
		assertEquals("NF #11 validada.", finalMyNF_.getStatus());
		assertEquals("NF #12 validada.", finalNf1_.getStatus());
		assertEquals("NF #13 validada.", finalNf2_.getStatus());
		
		// A classe NF_Abstract também possui um método de impressão,
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
		// Requisito do product owner: uma vez criada uma NF (antes da validação), os seus
		// itens de venda devem ser modificados, adicionados ou deletados apenas pelos
		// metodos apropriados. Devese cuidar que não haja acesso de escrita inapropriado a
		// lista de itens por outros meios.
		/**************************************/
		// A lista de IVs de NF é privada, de modo que ela somente pode ser
		//   alterada pelos métodos apropriados, como mostrado abaixo:
		
		// A NF já é criada com um IV:
		assertEquals(1, myNF_.sizeIVs());
		
		// Adicionando um novo IV pelo método apropriado:
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		assertTrue(myNF_.containsIV(item_));
		assertEquals(2, myNF_.sizeIVs());
		
		// Removendo um IV pelo método apropriado:
		myNF_.removeIV(item_);
		assertFalse(myNF_.containsIV(item_));
		assertEquals(1, myNF_.sizeIVs());
	}
	
	@Test
	public void test_Requisito_15(){
		/**************************************/
		// Requisito #15:
		// Requisito: Código de BD (mesmo que mockado), deve estar completamente
		// separado, desacoplado do restante do sistema, e acessivel por uma API única, que
		// é responsável por cadastros, buscas, submissões. Além disso, para simplificar logs,
		// segurança, e desacoplamento, deve haver apenas um objeto (objeto, não classe)
		// responsável por acessar o BD (isso representa uma restrição como “o seu aplicativo
		// só pode fazer uma conexão com o BD”).
		/**************************************/
		// O código dos BDs foram colocados em um pacote à parte. Os BDs são acessados
		//  somente mediante suas respectivas Facades, que são Singletons:
		assertSame(BDNF_Facade.getInstance(), BDNF_Facade.getInstance());
		assertSame(BDPS_Facade.getInstance(), BDPS_Facade.getInstance());
		
		// Os métodos dessa Facade são todos estáticos, simplificando o redirecionamento
		//  de delegação de tarefas e evitando instanciações desnecessárias:
		ps1_ = BDPS_Facade.getPS(3);
		finalNf1_ = BDNF_Facade.getNF(3); 
	}
}
