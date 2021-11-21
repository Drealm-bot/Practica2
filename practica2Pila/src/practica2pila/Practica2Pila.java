/*La gramatica que se va a reconocer es: 
 1.<S>       --> <ELO> {RESPUESTA}    	selección(1)={i,(}
 2.<ELO>     --> <EL2><ELO_L>          	selección(2)={i,(}
 3.<ELO_L>    --> |<EL2><ELO_L>        	selección(3)={|}
 4.<ELO_L>    -->                     		selección(4)={¬,)}
 5.<EL2>     --> <ER><EL2_L>          	selección(5)={i,(}
 6.<EL2_L>   --> &<ER><EL2_L>        	selección(6)={&}
 7.<EL2_L>   -->                     		selección(7)={¬,),|}
 8.<ER>      --> <E><ER_L>            	selección(8)={i,(}
 9.<ER_L>    --> <OR><E>             	selección(9)={<,>,=,!}
 10.<ER_L>   -->                     		selección(10)={¬,),&,|}
 11.<OR>    --> < <ME>               		selección(11)={<}
 12.<ME>     --> =                    		selección(12)={=}
 13.<ME>     -->                     		selección(13)={i,(}
 14.<OR>    --> > <MA>               		selección(14)={>}
 15.<MA>     --> =                    		selección(15)={=}
 16.<MA>     -->                     		selección(16)={i,(}
 17.<OR>    --> =<IG>                		selección(17)={=}
 18.<IG>     --> =                    		selección(18)={=}
 19.<OR>    --> !<DI>                		selección(19)={!}
 20.<DI>     --> =                    		selección(20)={=}
 21.<E>      --> <T><E_L>             		selección(21)={i,(}
 22.<E_L>    --> + <T><E_L>           	selección(22)={+}
 23.<E_L>    --> - <T><E_L>           	selección(23)={-}
 24.<E_L>    -->                     		selección(24)={¬,&,|,>,<,=,!,)}
 25.<T>      --> <P><T_L>             		selección(25)={i,(}
 26.<T_L>    --> * <P><T_L>           	selección(26)={*}
 27.<T_L>    --> / <P><T_L>           	selección(27)={/}
 28.<T_L>    -->                     		selección(28)={¬,&,|,>,<,=,!,),+,-}
 29.<P>      --> <F><P_L>             		selección(29)={i,(}
 30.<P_L>    --> ^<F><P_L>            	selección(30)={^}
 31.<P_L>    -->                     		selección(31)={¬,&,|,>,<,=,!,),+,-,*,/}
 32.<F>      --> ( <ELO> )            		selección(32)={(}
 33.<F>      --> I                    		selección(33)={i}

 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2pila;

/**
 *
 * @author lalo9
 */
public class Practica2Pila {

    /**
     * @param args the command line arguments
     */
    static String cad="+5*4 3¬";
    static String cad1 = "0123456789.";
    static int indice=0;
    static char sim=' ';
    static Lexico lex1 = new Lexico();
    static String cadavance="";
    static Analizador analizador = new Analizador();
    
    public static void main(String[] args) {
        // TODO code application logic here
        analisisLexico();
        cad=lex1.cadenaLexico();
        sim=lex1.darElemento(indice).darTipo();
        cadavance=cadavance+sim;
        
        NoTerminal v = new NoTerminal("v",0,0);
        NoTerminal s = new NoTerminal("s",0,0);
        
        analizador.adicionarNodo(v);
        analizador.adicionarNodo(s);
        
        NoTerminal nt=null;
    }
    
    public static void suma(){
        NoTerminal nt1,nt2,nt3,nt4;
        int u = analizador.ultimo();
        nt1=analizador.getnode(u-1);
        nt2=analizador.getnode(u-2);
        double sum=nt1.getValor()+nt2.getValor();
        nt3=analizador.getnode(u-3);
        nt4=analizador.getnode(nt3.getDirec());
        nt4.setValor(sum);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u-1);
        analizador.removeNodo(u-2);
        analizador.removeNodo(u-3);
        
    }
    
    public static void resta(){
        NoTerminal nt1,nt2,nt3,nt4;
        int u = analizador.ultimo();
        nt1=analizador.getnode(u-1);
        nt2=analizador.getnode(u-2);
        double res=nt1.getValor()-nt2.getValor();
        nt3=analizador.getnode(u-3);
        nt4=analizador.getnode(nt3.getDirec());
        nt4.setValor(res);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u-1);
        analizador.removeNodo(u-2);
        analizador.removeNodo(u-3);
        
    }
    
    public static void multiplicacion(){
        NoTerminal nt1,nt2,nt3,nt4;
        int u = analizador.ultimo();
        nt1=analizador.getnode(u-1);
        nt2=analizador.getnode(u-2);
        double mul=nt1.getValor()*nt2.getValor();
        nt3=analizador.getnode(u-3);
        nt4=analizador.getnode(nt3.getDirec());
        nt4.setValor(mul);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u-1);
        analizador.removeNodo(u-2);
        analizador.removeNodo(u-3);
        
    }
    
    public static void division(){
        NoTerminal nt1,nt2,nt3,nt4;
        int u = analizador.ultimo();
        nt1=analizador.getnode(u-1);
        nt2=analizador.getnode(u-2);
        double div=nt1.getValor()/nt2.getValor();
        nt3=analizador.getnode(u-3);
        nt4=analizador.getnode(nt3.getDirec());
        nt4.setValor(div);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u-1);
        analizador.removeNodo(u-2);
        analizador.removeNodo(u-3);
        
    }
    
    public static void exponente(){
        NoTerminal nt1,nt2,nt3,nt4;
        int u = analizador.ultimo();
        nt1=analizador.getnode(u-1);
        nt2=analizador.getnode(u-2);
        double exp=Math.pow(nt1.getValor(), nt2.getValor());
        nt3=analizador.getnode(u-3);
        nt4=analizador.getnode(nt3.getDirec());
        nt4.setValor(exp);
        //desapilar suma y sus atributos
        analizador.removeNodo(u);
        analizador.removeNodo(u-1);
        analizador.removeNodo(u-2);
        analizador.removeNodo(u-3);
        
    }
    
    public static void analisisLexico(){
        // Este analizador es sencillo determina solo constantes enteras y reales positivas
        // Trabaja los diferentes elementos en un ArrayList que trabaja con la clase Clexico
        // la cual define el ArrayList con la clase CElemento
        // Almacen los valores para poder hallar los resultados
        
        Elemento ele1; 
        
        int i=0;
        int ind=0;
        char tip=0;
        char sim1=cad.charAt(i);
        double val=0;
        
        while (sim1!='¬'){
            // determina si sim1 esta en la cadena de digitos cad1 que es global
            if (cad1.indexOf(sim1)!=-1){
                String num="";
                while(cad1.indexOf(sim1)!=-1){
                    num=num+sim1;
                    i++;
                    sim1=cad.charAt(i);
        
                }
                // en el String num se almacena el entero y se lo almacena en val como doble
                // DeterminarNumero(num);
                if (determinarNumero(num)){
                    val=Double.parseDouble(num);
                    tip='i';
                
                    // se tipifica el valor como i
                }
                else{
                    System.out.println("Se rechaza la secuencia");
                    System.exit(0);
                }
        
            }
            else {
               // si el simbolo de entrada no esta en cad1 lo tipifica como tal ej
               // +,-,* (,) etc.
                
               tip=(char)sim1;
               i++;
               sim1=cad.charAt(i);
               val=0;
              
            }
        
            // con los elementos establecidos anteriormente se crea el elemento y se lo
            // adicina a lex1 que es el objeto de la clase Clexico
            if (tip!=' '){
            ele1=new Elemento(tip,val,ind);
            lex1.adicionarElemento(ele1);
            
            ind=ind+1;
            }
            //System.out.print("indice ="+ind);
               
        }
        ele1=new Elemento('¬',0,ind);
        lex1.adicionarElemento(ele1);
        lex1.mostrarLexico();
        System.out.println(" cadena"+lex1.cadenaLexico());
    }
    
    public static boolean determinarNumero(String numero){
     // Este método recibe un número en string y determina mediante un autómata finito
     // si está o no correcto. El string es una cadena de dígitos y el punto.
     // Retorna un valor booleano.
     
        int estado=1,i=0;
        char simbolo;
        boolean b=true;
        while (i<numero.length()&&b) {
            simbolo = numero.charAt(i);
            switch (simbolo) {
                case '0':case '1':case '2':case '3':case '4':case '5':case '6':  
                case '7':case '8':case '9':    
                    switch (estado) {
                        case 1:
                           estado=2;
                           i++;
        
                           break;
                        case 2:
                           estado=2;
                           i++;
        
                           break;
                        case 3:
                           estado=4;
                           i++;
        
                           break;
                        case 4:
                            estado=4;
                           i++;
        
                           break;
                    
                    }
                    break;
                case '.':    
                    switch (estado) {
                        case 1:case 3: case 4:
                           b=false;
                           break;
                        case 2:
                           estado=3;
                           i++;
        
                           break;
                        
                    
                    }
                    break;
                default: b=false;
            }
        
    }
        return b;
    }
    
    
    public static void resultado(double res){
        System.out.println("Resultado "+res);
    }
    public static void avance(){
        indice++;
        if (indice<cad.length()) {
        sim=lex1.darElemento(indice).darTipo();
        cadavance=cadavance+sim;
        System.out.println("Cadena procesada "+cadavance);
              
       }
    }
}
