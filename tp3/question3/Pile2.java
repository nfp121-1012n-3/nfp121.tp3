package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>{
    /** par délégation : utilisation de la class Stack */
    private Stack<T> stk;
    /** la capacité de la pile */
    private int capacite;

    /** Création d'une pile.
     * @param taille la "taille maximale" de la pile, doit être > 0
     */
    public Pile2(int taille){
        
        if(taille <= 0) 
        
        taille = CAPACITE_PAR_DEFAUT;
        
        this.stk = new Stack<T>();
        
        this.capacite = taille;
        
    }
    
    public Pile2(){
        this(0);
    }
    
    public int capacite() {
        return this.capacite;
    }
    
    public void empiler(T o) throws PilePleineException{
        if(estPleine())throw new PilePleineException();
        stk.push(o);
    }

    public T depiler() throws PileVideException{
        if(estVide())throw new PileVideException();
        return stk.pop();
    }

    public T sommet() throws PileVideException{
        if(estVide())throw new PileVideException();
        return stk.peek();
    }


    public boolean estPleine() {
        return stk.size() == capacite;
    }

    public boolean estVide() {
        return stk.size() == 0;
    }
    
    public int hashCode() {
        return toString().hashCode();
    }
    
    public int taille() {
        return stk.size();
    }
       
    
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = stk.size() - 1; i >= 0; i--) {
            Object[]tab=stk.toArray();
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
            
        PileI pile = (PileI)o;
        
        if(super.equals(o))
          
        return true;
        
        int capacite = this.capacite();
        
       
        int taille = this.taille();
        
        if(capacite != pile.capacite())
        
        
        return false;
        
        
           
        if(taille != pile.taille())
           
        return false;
        
        if(o == null) return false;
        
        if(taille == 0) return true;
        
        Pile2 v1 = new Pile2(taille);
        
        Pile2 v2 = new Pile2(pile.taille());
        
        boolean elementsEgaux;
        
        while (!pile.estVide() && !this.estVide()){
            
        try{
                
                elementsEgaux = false;
                
                if(this.sommet() == null){
                    
                    if(pile.sommet() == null) 
                    
                        elementsEgaux = true;
                }        
                else if(pile.sommet() == null){
                    
                    if(this.sommet() == null) 
                    
                        elementsEgaux = true;
                        
                }  
                else if(this.sommet().equals(pile.sommet())){
                    
                    elementsEgaux = true;
                }
                
                if(elementsEgaux){
                    
                    v1.empiler(this.depiler());
                    
                    v2.empiler(pile.depiler());
                    
                }
               
                else{
                   
                    loadPile(v1, this);
                    
                    loadPile(v2, pile);
                    
                    return false;
                }
            } 
            catch(PilePleineException pe){pe.printStackTrace();
                
            }
            
            catch(PileVideException v){v.printStackTrace();
            }
        }
        
      loadPile(v1, this);
      
      loadPile(v2, pile);
        
     return true;
    }
    private void loadPile(PileI p1, PileI p2){
        while(!p1.estVide()){
            try{
                p2.empiler(p1.depiler());
            } catch (PileVideException v){v.printStackTrace();}
            catch (PilePleineException pe){pe.printStackTrace();}
        }
    }
    
    
} // Pile2