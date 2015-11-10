package conceito_fiscal;

import java.util.ArrayList;

public class NF_Final extends NF_Abstract
{	
	protected final int ID_;
	
	// Construtor: finalNF deve ser igual a nf, porém final.
	protected NF_Final(NF nf, int id){
		IVs_ = new ArrayList<IV>(nf.getIVs());
		TotalTribute_ = this.calculaImposto();
		ID_ = id;
	}
	
	// Checagem do Status da NF.
	@Override
	public String getStatus() {
		return "NF #"+ID_+" validada.";
	}
	@Override
	public boolean isFinal() {
		return true;
	}
	public int getID(){
		return ID_;
	}
	/**********************************/
	/* Manipulação da Lista de IVs    */
	/**********************************/
	public boolean containsIV(IV item){
		return IVs_.contains(item);
	}
	public IV getIV (int ID){
		return IVs_.get(ID);
	}
	public ArrayList<IV> getIVs(){
		return IVs_;
	}
	public int sizeIVs(){
		return IVs_.size();
	}
}

