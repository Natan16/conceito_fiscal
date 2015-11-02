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
	public static BDPS getBDPSinstance(){
		return BDPS.getInstance();
	}
	public static PS_Concrete createNewPS(String nome, String categoria, PS_Concrete PS){
		return BDPS.getInstance().createNewPS(nome, categoria, PS);
	}
	public static PS_Concrete getPS(int ID){
		return BDPS.getInstance().getPS(ID);
	}
	public static String getTributeCat(PS_Concrete PS){
		return BDPS.getInstance().getTributeCat(PS);
	}
}
