package imposto;

import java.util.ArrayList;
import conceito_fiscal.IV;

public class Imposto_Calc 
{
	private Imposto_Info info_;
	private Imposto calc;
	
	public int tax(ArrayList<IV> iVs_) {
		return 0;
	}

	public Imposto_Info getInfo_() {
		return info_;
	}

	public void setInfo_(Imposto_Info info_) {
		this.info_ = info_;
	}

	public Imposto getCalc() {
		return calc;
	}

	public void setCalc(Imposto calc) {
		this.calc = calc;
	}
	
}
