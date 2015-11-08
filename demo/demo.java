package demo;

import static org.junit.Assert.*;

import org.junit.*;

import conceito_fiscal.*;
import banco_dados.BDNF_Facade;
import banco_dados.BDPS_Facade;

import java.util.ArrayList;

public class demo {
	// VariÃ¡veis usadas para os testes
	NF_Final finalMyNF_, finalNf1_, finalNf2_;
	PS_Abstract produto_, ps1_, ps2_, ps3_, ps4_;
	NF myNF_, nf1_, nf2_;
	IV item_, item1_;
	ArrayList<PS_Abstract> lista_PS;

	// Algumas classes maiores e mais complexas sÃ£o criadas
	// apenas uma vez BeforeClass, como os bancos de dados:
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	// Antes de cada teste, criamos algumas instÃ¢ncias comuns
	// para os testes, como por exemplo uma nova NF:
	@Before
	public void setUp() throws Exception {
		myNF_ = NF_Builder.constructNF();
		ps1_ = BDPS_Facade.getPS(3);
		ps2_ = BDPS_Facade.getPS(7);
		ps3_ = BDPS_Facade.getPS(9);
		lista_PS = new ArrayList<PS_Abstract>();
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
		// RestriÃ§Ã£o legal: NF nÃ£o pode ter zero IV. Deve ter 1 ou
		// mais.
		/**************************************/
		// A classe builderNF se encarrega de construir uma NF com
		// exatamente um IV inicial, sendo que ela nÃ£o inicia vazia:
		myNF_ = NF_Builder.constructNF();
		assertEquals(1, myNF_.sizeIVs());

		// Quando tentamos remover algum elemento da NF,
		// a NF garante que nunca ficarÃ¡ vazia.
		myNF_.removeID(1);
		assertEquals(1, myNF_.sizeIVs());
	}

	@Test
	public void test_Requisito_02() {
		/**************************************/
		// Requisito #02:
		// RestriÃ§Ã£o legal: Todo IV deve pertencer a exatamente uma NF.
		/**************************************/
		// Os IVs sÃ£o criados dentro da prÃ³pria NF, garantindo que
		// eles sempre irÃ£o pertencer a exatamente uma NF:
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		assertEquals(myNF_, item_.getNF_());

		// Como a NF do IV Ã© determinada em seu construtor e o campo
		// NF de IV Ã© privado, cada IV irÃ¡ pertencer sempre Ã quela
		// mesma NF.
	}

	@Test
	public void test_Requisito_03() {
		/**************************************/
		// Requisito #03:
		// RestriÃ§Ã£o legal: Todo IV se referirÃ¡ a exatamente um produto
		// ou serviÃ§o.
		/**************************************/
		// Os IVs sÃ£o criados dentro do mÃ©todo addNewIV da NF, em que
		// um de seus parÃ¢metros Ã© um dado produto PS. Assim todo
		// IV se referirÃ¡ sempre a exatamente um produto ou serviÃ§o PS.
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		produto_ = item_.getPS_();
		assertEquals(ps1_, produto_);

		// Posso no mÃ¡ximo alterar o seu produto ou serviÃ§o usando um
		// Setter,
		// mas nÃ£o removÃª-lo, pois o campo PS de item Ã© privado.
		item_.setPS_(ps2_);
		produto_ = item_.getPS_();
		assertEquals(ps2_, produto_);
	}

	@Test
	public void test_Requisito_04() {
		/**************************************/
		// Requisito #04:
		// RestriÃ§Ã£o legal: Um P/S deve sempre pertencer a um IV ou a um
		// outro P/S.
		/**************************************/
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		// IV contem PS (item contem ps1_)
		produto_ = item_.getPS_();
		assertEquals(ps1_, produto_);

		// PS contem PS (ps4_ contem lista de ps que contem ps2_)
		lista_PS.add(ps2_);
		PS_Concrete ps4_;
		ps4_ = (PS_Concrete) BDPS_Facade.createNewPS("P1", "A", lista_PS);
		assertEquals(ps2_, ps4_.getPSs_(0));
	}

	@Test
	public void test_Requisito_05() {
		/**************************************/
		// Requisito #05:
		// RestriÃ§Ã£o legal: sÃ³ podem ser adicionados Ã  uma NF, P/Ss
		// que estejam cadastrados
		// no BD:P/S (Banco de Dados de Produtos e ServiÃ§os). SÃ³ o BD:P/S
		// pode criar
		// objetos P/S. O BD:P/S contÃ©m informaÃ§Ã£o sobre produtos e
		// serviÃ§os inclusive a
		// categoria tributÃ¡ria de cada P/S especifico.
		/**************************************/
		// O construtor privado de P/S proÃ­be a instanciaÃ§Ã£o de novos
		// P/S.
		// Somente o BDPS pode criar novos P/S, pelo mÃ©todo createNewPS

		// produto = new PS_Concrete("P1", null); // Resulta em erro de
		// compilaÃ§Ã£o.
		// A Facade de BDPS redireciona o pedido de criaÃ§Ã£o de produto
		// Ã  classe BDPS:
		produto_ = BDPS_Facade.createNewPS("P1", "A", null);

		// BDPS contÃ©m uma lista de produtos e outra de Categoria
		// TributÃ¡ria,
		// vinculadas fortemente atravÃ©s de um Ã­ndice comum:
		assertEquals("A", BDPS_Facade.getTributeCat(produto_));
	}

	@Test
	public void test_Requisito_06() {
		/**************************************/
		// Requisito #06:
		// RestriÃ§Ã£o legal: Uma NF Ã© criada no estado â€œem
		// elaboraÃ§Ã£oâ€� e isto deve constar de
		// uma eventual impressÃ£o.
		/**************************************/
		// A NF criada pertence a uma classe cujo objeto Ã© ainda mutÃ¡vel:
		myNF_ = NF_Builder.constructNF();
		item1_ = myNF_.addNewIV(ps1_, 1, 10);
		assertEquals(2, myNF_.sizeIVs());

		// O mÃ©todo getStatus() imprime o estado atual da NF.
		assertEquals("NF em elaboracao", myNF_.getStatus());
	}

	@Test
	public void test_Requisito_07() {
		/**************************************/
		// Requisito #07:
		// RestriÃ§Ã£o legal: Uma vez que esteja completamente preenchida
		// com todos os seus
		// IV, uma NF deve ser validada (checa requisitos e calcula todos os
		// impostos) e
		// armazenada no subsistema BD:NF (Banco de Dados de NF), que tambÃ©m
		// se
		// encarrega de submeter na prefeitura. EntÃ£o, a NF deve passar para
		// o estado
		// â€œvalidadaâ€�, e deve ser entÃ£o completamente imutavel.
		// Nunca nenhum dado da NF
		// inclusive de qualquer IV, deve ser modificado em uma NF validada. O
		// BD:NF nÃ£o
		// deve aceitar nem validar nem gerar um ID para uma NF jÃ¡ validada,
		// ou com dados
		// invÃ¡lidos. Se uma NF for corretamente validada, um objeto imutavel
		// representandoa
		// deve ser passado como resposta ao usuÃ¡rioprogramador.
		/**************************************/
		// Quando criada, a NF estÃ¡ em estado de elaboraÃ§Ã£o, podendo
		// ser modificada:
		myNF_ = NF_Builder.constructNF();
		myNF_.addNewIV(ps1_, 2, 10);

		// A validaÃ§Ã£o da NF Ã© feito pelo BDNF, conforme mostrado
		// abaixo:
		finalMyNF_ = BDNF_Facade.validateNF(myNF_);
		assertTrue(BDNF_Facade.contains(finalMyNF_));

		// O valor total de imposto jÃ¡ Ã© calculado automaticamente:
		assertEquals(43, finalMyNF_.getTotalTribute());
		// A NF obtida pertence a uma classe Final com atributos final e
		// protegidos,
		// de modo que uma vez validada, nÃ£o Ã© possÃ­vel alterar suas
		// informaÃ§Ãµes:
		assertTrue(finalMyNF_.isFinal());

		// Ã‰ tambÃ©m inviÃ¡vel validar uma NF jÃ¡ validada, tendo em
		// vista
		// que NF e NF_Final sÃ£o duas subclasses diferentes:
		assertNull(BDNF_Facade.validateNF(finalMyNF_));
	}

	@Test
	public void test_Requisito_08() {
		/**************************************/
		/*
		 * Requisito #08:Requisito: Há um conjunto de varios impostos a serem
		 * aplicados em uma NF. Cada imposto possui uma aliquota default para
		 * produtos e serviços, e cada categoria tributária de P/S pode ter
		 * uma aliquota diferenciada . O BD:P/S é mantido atualizado e
		 * confiamos nas aliquotas armazenadas.
		 */
		/**************************************/
		// item_ = myNF_.addNewIV(ps1_, 2, 100);
		int imposto = myNF_.calculaImposto();
		// cada imposto tem uma alíquota default para produtos e serviços
		assertEquals(21, imposto);
		item_ = myNF_.addNewIV(ps2_, 2, 100);
		imposto = myNF_.calculaImposto();
		assertEquals(261, imposto);
		// um determinado produto tem uma categoria de impostos, que pode conter
		// uma alíquota
		// diferenciada
		// produto_;
	}

	@Test
	public void test_Requisito_11() {
		/**************************************/
		// Requisito #11:
		// RestriÃ§Ã£o Legal: Cada NF validada deve ter um identificador
		// Ãºnico, gerado durante a
		// validaÃ§Ã£o, que nunca pode se repetir6. Uma vez validada, esse
		// ID deve aparecer em
		// qualquer impressÃ£o.
		/**************************************/
		// O ID Ãºnico de cada NF Ã© garantido pelo campo estÃ¡tico ID_ do
		// BDNF,
		// que Ã© incrementado sempre que uma nova finalNF Ã© validada.
		myNF_ = NF_Builder.constructNF();
		nf1_ = NF_Builder.constructNF();
		nf2_ = NF_Builder.constructNF();

		// Cada vez que validateNF() Ã© chamada, um novo ID incrementado Ã©
		// atribuÃ­do
		finalMyNF_ = BDNF_Facade.validateNF(myNF_);
		finalNf1_ = BDNF_Facade.validateNF(nf1_);
		finalNf2_ = BDNF_Facade.validateNF(nf2_);

		// Observe o ID crescente das NFs validadas:
		assertEquals("NF #11 validada.", finalMyNF_.getStatus());
		assertEquals("NF #12 validada.", finalNf1_.getStatus());
		assertEquals("NF #13 validada.", finalNf2_.getStatus());

		// A classe NF_Abstract tambÃ©m possui um mÃ©todo de impressÃ£o,
		// em que consta seu Status, seu ID e os seus IVs:
		System.out.println("*** Requisito # 11 ***");
		System.out.println(finalNf1_.toPrint());
		System.out.println("**********************");
	}

	@Test
	public void test_Requisito_12() {
		/**************************************/
		// Requisito #12:
		// [Removido]
		/**************************************/
		assertTrue(true);
	}

	@Test
	public void test_Requisito_14() {
		/**************************************/
		// Requisito #14:
		// Requisito do product owner: uma vez criada uma NF (antes da
		// validaÃ§Ã£o), os seus
		// itens de venda devem ser modificados, adicionados ou deletados apenas
		// pelos
		// metodos apropriados. Devese cuidar que nÃ£o haja acesso de escrita
		// inapropriado a
		// lista de itens por outros meios.
		/**************************************/
		// A lista de IVs de NF Ã© privada, de modo que ela somente pode ser
		// alterada pelos mÃ©todos apropriados, como mostrado abaixo:

		// A NF jÃ¡ Ã© criada com um IV:
		assertEquals(1, myNF_.sizeIVs());

		// Adicionando um novo IV pelo mÃ©todo apropriado:
		item_ = myNF_.addNewIV(ps1_, 2, 100);
		assertTrue(myNF_.containsIV(item_));
		assertEquals(2, myNF_.sizeIVs());

		// Removendo um IV pelo mÃ©todo apropriado:
		myNF_.removeIV(item_);
		assertFalse(myNF_.containsIV(item_));
		assertEquals(1, myNF_.sizeIVs());
	}

	@Test
	public void test_Requisito_15() {
		/**************************************/
		// Requisito #15:
		// Requisito: CÃ³digo de BD (mesmo que mockado), deve estar
		// completamente
		// separado, desacoplado do restante do sistema, e acessivel por uma API
		// Ãºnica, que
		// Ã© responsÃ¡vel por cadastros, buscas, submissÃµes. AlÃ©m
		// disso, para simplificar logs,
		// seguranÃ§a, e desacoplamento, deve haver apenas um objeto (objeto,
		// nÃ£o classe)
		// responsÃ¡vel por acessar o BD (isso representa uma restriÃ§Ã£o
		// como â€œo seu aplicativo
		// sÃ³ pode fazer uma conexÃ£o com o BDâ€�).
		/**************************************/
		// O cÃ³digo dos BDs foram colocados em um pacote Ã  parte. Os BDs
		// sÃ£o acessados
		// somente mediante suas respectivas Facades, que sÃ£o Singletons:
		assertSame(BDNF_Facade.getInstance(), BDNF_Facade.getInstance());
		assertSame(BDPS_Facade.getInstance(), BDPS_Facade.getInstance());

		// Os mÃ©todos dessa Facade sÃ£o todos estÃ¡ticos, simplificando o
		// redirecionamento
		// de delegaÃ§Ã£o de tarefas e evitando instanciaÃ§Ãµes
		// desnecessÃ¡rias:
		ps1_ = BDPS_Facade.getPS(3);
		finalNf1_ = BDNF_Facade.getNF(3);
	}

	@Test
	public void test_Requisito_17() {
		/**************************************/
		// Requisito #17:
		// Requisito: Todas as entidades armazenadas em BD devem
		// corresponder a entidades imutaveis uma vez retirados do
		// BD. PSs, NFs validadas.[Imutable Object]
		/**************************************/

		// aqui, pegamos uma NF do BD
		finalNf1_ = BDNF_Facade.getNF(3);
		assertTrue(finalNf1_.isFinal());

		// Aqui pegamos um PS do BD
		ps1_ = BDPS_Facade.getPS(3);
		assertTrue(ps1_.isFinal());

	}

	@Test
	public void test_Requisito_18() {
		/**************************************/
		// Requisito #18:
		// Cada produto ou servico pode ser subdivido em outros
		// produtos e/ou servicos.A quantidade de subdivisões depende do P/S
		// específico. Por
		// exemplo, SPintura sempre tem “SMaode Obra�? e “PTinta�?.Não
		// há limitação na
		// profundidade das subdivisões8. Por exemplo, uma subclasse de “SMao
		// de Obra�?
		// pode permitir um subcontratado, e o subcontratado subsubcontrata
		// outro, etc. A NFdeve listar todas as subdivisões inclusive
		// todas as folhas do último nível [Composite e Visitor]
		/**************************************/

		// [1� etapa]:
		// criamos uma lista e adicionamos PSs a ela
		lista_PS.add(ps1_);
		lista_PS.add(ps2_);

		// ps4_ é o ps que subcontrata ps1_ e ps2_, entao os possui
		// em sua lista.
		PS_Concrete ps4_;
		ps4_ = (PS_Concrete)BDPS_Facade.createNewPS("P4", "A", lista_PS);
		assertEquals(ps2_, ps4_.getPSs_(1));
		assertEquals(ps1_, ps4_.getPSs_(0));
		
		
		// [2� etapa]:
		// criamos uma lista e adicionamos ps4_ a ela
		lista_PS.clear();
		lista_PS.add(ps4_);
		lista_PS.add(ps3_);

		// ps5_ � um produto que subcontrata ps4_. 
		//   assim, ps5_ subsubcontrata ps2_ e ps1_ (Composite)
		PS_Concrete ps5_;
		ps5_ = (PS_Concrete) BDPS_Facade.createNewPS("P5", "A", lista_PS);
		assertEquals(ps4_, ps5_.getPSs_(0));
		assertEquals(ps3_, ps5_.getPSs_(1));
		
		// Eu sei que h� viola��o da Lei de Demeter abaixo, mas quero
		//   deixar bem clara a estrutura em �rvore, de ps5_ subsubcontratar ps2_ e ps1_.
		assertEquals(ps2_, ((PS_Concrete)ps5_.getPSs_(0)).getPSs_(1));
		assertEquals(ps1_, ((PS_Concrete)ps5_.getPSs_(0)).getPSs_(0));
		
		// Estrutura em �rvore gerada:
		//           ps5_
		//      ps3_       ps4_
		//             ps1_     ps2_
	}

}
