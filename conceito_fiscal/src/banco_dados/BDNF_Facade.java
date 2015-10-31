package banco_dados;

import conceito_fiscal.NF;

public class BDNF_Facade 
{
	// Padrão Singleton:
	private static BDNF_Facade INSTANCE;
	public static BDNF_Facade getInstance() {
		if (BDNF_Facade.INSTANCE == null)
			BDNF_Facade.INSTANCE = new BDNF_Facade();
		return BDNF_Facade.INSTANCE;
	}
		
	// Métodos Facade:
	public BDNF getBDNFinstance(){
		return BDNF.getInstance();
	}
	public void adicionaNF(NF nf){
		BDNF.getInstance().adicionaNF(nf);
	}
	public void getNF(int ID){
		BDNF.getInstance().getNF(ID);
	}
}
