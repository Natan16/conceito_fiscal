package imposto;

public class Imposto_Factory {
	
	public static Imposto getImposto(String cat){
		if ( cat == "A"){
			return new ImpostoA();
		}
		else if( cat == "B"){
			return new ImpostoB();
		}
		else if( cat == "A+"){
			return new ImpostoA();
		}
		return new ImpostoNull();
	}
}
