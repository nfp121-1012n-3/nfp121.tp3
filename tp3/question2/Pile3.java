package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Vector;

/**
 * Décrivez votre classe PileVector ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile3 implements PileI {

	private Vector<Object> v;

	public Pile3() {
		this(0);
	}

	public Pile3(int taille) {
		if (taille <= 0)
            {taille = CAPACITE_PAR_DEFAUT;}
            v=new Vector<Object>(taille);
	}

	public void empiler(Object o) throws PilePleineException {
		if(estPleine())throw new PilePleineException();
		v.add(o);
	}

	public Object depiler() throws PileVideException {
	    if(estVide())throw new PileVideException();	
	    Object removed= v.lastElement();
	    v.remove(v.size()-1);
	    return removed;
	}

	public Object sommet() throws PileVideException {
		if(estVide())throw new PileVideException();
		return v.lastElement();
	}

	public int taille() {
		return v.size();
	}

	public int capacite() {
		return v.capacity();
	}

	public boolean estVide() {
		return v.size()==0;
	}

	public boolean estPleine() {
		return v.size()==v.capacity();
	}

	    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = v.size() - 1; i >= 0; i--) {
            Object[]tab=v.toArray();
            sb.append(tab[i].toString());
            if (i > 0)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
        }

	public boolean equals(Object o) {
		
        if(!(o instanceof PileI))
           
        return false;
        
        PileI p1 = (PileI)o;
        
        if(super.equals(o))
            return true;
        
        int capacite = this.capacite();
        
        int taille = this.taille();
        
        if(capacite != p1.capacite())
        
            return false;
            
        if(taille != p1.taille())
        
            return false;
            
        if(o == null) return false;
        
        if(taille == 0) return true;
        
        
        Pile3 v1 = new Pile3(taille);
        
        Pile3 v2 = new Pile3(p1.taille());
        
        
        boolean equals;
        
        while (!p1.estVide() && !this.estVide()){
           
            try{
                
                equals = false;
                
                if(this.sommet() == null){
                    
                if(p1.sommet() == null)
                    
                equals = true;
                        
                }        
                else if(p1.sommet() == null){
                    
                if(this.sommet() == null) 
                    
                equals = true;
                        
                }  
                
                else if(this.sommet().equals(p1.sommet())){
                    
                equals = true;
                }
                
                if(equals){
                
                v1.empiler(this.depiler());
                    
                v2.empiler(p1.depiler());
                }
                
                else{
                    
                loadPile(v1, this);
                    
                loadPile(v2, p1);
                    
                return false;
                }
            } 
            catch(PilePleineException pe){pe.printStackTrace();}
            
            catch(PileVideException v){v.printStackTrace();}
        }
        loadPile(v1, this);
        
        loadPile(v2, p1);
        
        return true;
    }
    
    private void loadPile(PileI a, PileI b){
        while(!a.estVide()){
            try{
                b.empiler(a.depiler());
            } catch (PileVideException v){v.printStackTrace();}
            catch (PilePleineException pe){pe.printStackTrace();}
        }
    }

	// fonction fournie
	public int hashCode() {
		return toString().hashCode();
	}

}
