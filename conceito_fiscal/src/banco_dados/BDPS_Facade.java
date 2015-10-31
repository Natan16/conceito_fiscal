package banco_dados;

import conceito_fiscal.PS_Concrete;

public class BDPS_Facade 
{
	// Padrão Singleton:
	private static BDPS_Facade INSTANCE;
	public static BDPS_Facade getInstance() {
		if (BDPS_Facade.INSTANCE == null)
			BDPS_Facade.INSTANCE = new BDPS_Facade();
		return BDPS_Facade.INSTANCE;
	}
		
	// Métodos Facade:
	public BDPS getBDPSinstance(){
		return BDPS.getInstance();
	}
	public void adicionaPS(PS_Concrete ps){
		BDPS.getInstance().adicionaPS(ps);
	}
	public PS_Concrete getPS(int ID){
		return BDPS.getInstance().getPS(ID);
	}
}
