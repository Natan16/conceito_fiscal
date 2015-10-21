package conceito_fiscal;

import java.util.ArrayList;

public class NF {
	private ArrayList<IV> IVs_;
	
	public NF(){
		IVs_ = new ArrayList<IV>();
	}
	public void addIV(IV item){
		IVs_.add(item);
	}
	public void removeIV(IV item){
		IVs_.remove(item);
	}
}

