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
	static BDNF_Facade BDNF_Facade_;
	static BDPS_Facade BDPS_Facade_;
	PS_Concrete produto, ps1, ps2;
	NF myNF_;
	IV item;
	
	
	// Algumas classes maiores e mais complexas s�o criadas
	//  apenas uma vez BeforeClass, como os bancos de dados:
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		builderNF_ = new NF_Builder();
		BDNF_Facade_ = BDNF_Facade.getInstance();
		BDPS_Facade_ = BDPS_Facade.getInstance();
	}

	// Antes de cada teste, criamos algumas inst�ncias comuns
	//   para os testes, como por exemplo uma nova NF:
	@Before
	public void setUp() throws Exception {
		myNF_ = builderNF_.constructNF();
		ps1 = BDPS_Facade_.getPS(3);
		ps2 = BDPS_Facade_.getPS(7);
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
		assertEquals(ps2, produto);
	}

}
