package banco_dados;

import conceito_fiscal.NF_Abstract;
import conceito_fiscal.NF_Final;

public class BDNF_Facade 
{
	// Padr�o Singleton:
	private static BDNF_Facade INSTANCE;
	public static BDNF_Facade getInstance() {
		if (BDNF_Facade.INSTANCE == null)
			BDNF_Facade.INSTANCE = new BDNF_Facade();
		return BDNF_Facade.INSTANCE;
	}
		
	// M�todos Facade:
	public static BDNF getBDNFinstance(){
		return BDNF.getInstance();
	}
	public static NF_Final getNF(int ID){
		return BDNF.getInstance().getNF(ID);
	}
	public static NF_Final validateNF(NF_Abstract nf){
		return BDNF.getInstance().validateNF(nf);
	}
	public static boolean contains(NF_Final nf){
		return BDNF.getInstance().contains(nf);
	}
}
