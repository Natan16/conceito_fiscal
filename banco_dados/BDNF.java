package banco_dados;

import imposto.Imposto_Info;

import java.util.ArrayList;

import conceito_fiscal.NF;
import conceito_fiscal.NF_Abstract;
import conceito_fiscal.NF_Builder;
import conceito_fiscal.NF_Final;

public class BDNF 
{
	private static int nfID_;
	private ArrayList<NF_Final> finalNFs_;
	private ArrayList<NF> NFs_;
	
	// Construtor de BDNF usando Mock
	public BDNF(){
		// MockDados gera uma lista de NFs, que depois são validadas
		//   pelo BDNF, gerando um Mock de Banco de dados.
		BDNF_MockDados Mock = new BDNF_MockDados();
		NFs_ = new ArrayList<NF>(Mock.getNFs_());
		finalNFs_ = new ArrayList<NF_Final>();
		for (nfID_ = 0; nfID_ < Mock.getNMockObjects(); nfID_++)
			validateNF(NFs_.get(nfID_));
	}
	
	// Padrão Singleton:
	private static BDNF INSTANCE;
	public static BDNF getInstance() {
		if (BDNF.INSTANCE == null)
			BDNF.INSTANCE = new BDNF();
		return BDNF.INSTANCE;
	}

	/**********************************/
	/*   Manipulação dos ArrayLists   */
	/**********************************/
	// Operação de Validação de NF
	public NF_Final validateNF(NF_Abstract nf) {
		NF_Final finalNF = null;
		if (!nf.isFinal())
		{
			finalNF = NF_Builder.constructNF_Final((NF) nf, nfID_++);
			// Toda vez que uma nota fiscal é finalizada ele pode ser
			//usada pra calcular impostos sobre novas notas fiscais
			Imposto_Info.addNF(finalNF.getTotalTribute());
			finalNFs_.add(finalNF);
		}
		return finalNF;
	}
	// Obtendo finalNF do Banco de Dados
	public NF_Final getNF(int ID){
		return finalNFs_.get(ID);
	}
	public boolean contains(NF_Final nf){
		return finalNFs_.contains(nf);
	}
	
}
