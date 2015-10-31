package banco_dados;

import java.util.ArrayList;
import conceito_fiscal.NF;

public class BDNF 
{
	private final ArrayList<NF> NFs_;
	
	// Construtor de BDNF usando Mock
	public BDNF(){
		BDNF_MockDados Mock = new BDNF_MockDados();
		NFs_ = new ArrayList<NF>(Mock.getNFs_());
	}
	
	// Padrão Singleton:
	private static BDNF INSTANCE;
	public static BDNF getInstance() {
		if (BDNF.INSTANCE == null)
			BDNF.INSTANCE = new BDNF();
		return BDNF.INSTANCE;
	}

	// Operações no ArrayList:
	public void adicionaNF(NF nf){
		NFs_.add(nf);
	}
	public void removeNF(NF nf){
		NFs_.remove(nf);
	}
	public void getNF(int ID){
		NFs_.get(ID);
	}
}
