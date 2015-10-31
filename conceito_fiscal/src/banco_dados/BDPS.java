package banco_dados;

import java.util.ArrayList;
import conceito_fiscal.PS_Concrete;

public class BDPS 
{
	private final ArrayList<PS_Concrete> PSs_;
	
	// Construtor de BDNF usando PS
	public BDPS(){
		BDPS_MockDados Mock = new BDPS_MockDados();
		PSs_ = new ArrayList<PS_Concrete>(Mock.getPSs_());
	}
	
	// Padrão Singleton:
	private static BDPS INSTANCE;
	public static BDPS getInstance() {
		if (BDPS.INSTANCE == null)
			BDPS.INSTANCE = new BDPS();
		return BDPS.INSTANCE;
	}

	// Operações no ArrayList:
	public void adicionaPS(PS_Concrete PS){
		PSs_.add(PS);
	}
	public void removePS(PS_Concrete PS){
		PSs_.remove(PS);
	}
	public PS_Concrete getPS(int ID){
		return PSs_.get(ID);
	}
}
