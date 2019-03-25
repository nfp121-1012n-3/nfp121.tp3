package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2 implements PileI {
    /** par delegation : utilisation de la class Stack */
    private Stack<Object> stk;

    /** la capacite de la pile */
    private int capacite;

    
 //creation d'un type stack qui accepte une taille:
public class SizedStack<T> extends Stack<T> {
    private int maxSize;

    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public T push(T object) {
        //If the stack is too big, remove elements until it's the right size.
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}

    /**
     * Creation d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit etre > 0
     */
    public Pile2(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        
        this.stk = new SizedStack<Object>(taille);
        this.capacite = taille;
    }

    // constructeur fourni
    public Pile2() {
        this(0);
    }

    public void empiler(Object o) throws PilePleineException {
        if(estPleine())throw new PilePleineException();
        stk.push(o);
    }

    public Object depiler() throws PileVideException {
        if(estVide())throw new PileVideException();
        Object o1=(Object)stk.pop();
        return o1;
    }

    public Object sommet() throws PileVideException {
        if(estVide())throw new PileVideException();
        Object o1=(Object)stk.peek();
        return o1;
    }

    /**
     * Effectue un test de l'etat de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        return stk.empty();
    }

    /**
     * Effectue un test de l'etat de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        
        return stk.size()==capacite;
    }

    /**
     * Retourne une representation en String d'une pile, contenant la
     * representation en String de chaque element.
     * 
     * @return une representation en String d'une pile
     */
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

    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Retourne le nombre d'element d'une pile.
     * 
     * @return le nombre d'element
     */
    public int taille() {
        return stk.size();
    }

    /**
     * Retourne la capacite de cette pile.
     * 
     * @return le nombre d'element
     */
    public int capacite() {
        return this.capacite;
    }

} // Pile2.java
