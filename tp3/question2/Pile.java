package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile implements PileI {

    private Object[] zone;
    private int ptr;

    public Pile(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        this.zone = new Object[taille];
        this.ptr = 0;
    }

    public Pile() {
        this(0);
    }

    public void empiler(Object o) throws PilePleineException {
            if (estPleine())
            throw new PilePleineException();
        this.zone[this.ptr] = o;
        this.ptr++;
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        ptr--;
        return zone[ptr];
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        return zone[ptr-1];
       
    }

    public int capacite() {
        return zone.length;
    }

    public int taille() {
    return ptr;
    }

    public boolean estVide() {
       return ptr == 0;
    }

    public boolean estPleine() {
       return ptr == zone.length;
    }
    
    
   
     
   public boolean equals(Object o){
       // if (o instanceof PileI) { 
      // PileI p = (PileI) o; 
      // return this.capacite() == p.capacite() 
          // && this.hashCode() == p.hashCode(); 
    // } else 
      // return false; 
  // }
        PileI p = (PileI)o;
       
        int capacite = this.capacite();
        int taille = this.taille();
        if(capacite != p.capacite())
            return false;
        if(taille != p.taille())
            return false;
        
            if(! (o instanceof PileI))
            return false;
            
        //(empty check):
        if(taille == 0) return true;
        
        //element % element et prenant compte des sequences: 
        Pile a = new Pile(taille);
        Object b = new Object();
        for(int i=taille-1; i>=0 ; i--){
            try{
                b = p.depiler();
                a.empiler(b);
            } catch(PileVideException pve){}
            catch(PilePleineException ppe){}
            if(!b.equals(zone[i])){
                try{
                    p.empiler(b);
                } catch(PilePleineException ppe){}
                ldPile(a, p);
                return false;
            }  
        }
        
        ldPile(a, p);
        
        return true;
        
    }
  
    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = ptr - 1; i >= 0; i--) {
            sb.append(zone[i].toString());
            if (i > 0)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
        //method for loading back the structure:
    private void ldPile(PileI a, PileI b){
        int taillePile = a.taille();
        for(int i=0; i<taillePile; i++){
            try{
                b.empiler(a.depiler());
            } catch(PileVideException pve){}
            catch(PilePleineException ppe){}
        }
    }
}