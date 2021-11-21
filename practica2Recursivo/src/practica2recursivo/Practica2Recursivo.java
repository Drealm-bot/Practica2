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
package practica2recursivo;

/**
 *
 * @author lalo9
 */
public class Practica2Recursivo {
    
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
        
        procS();
        if (sim=='¬')
            System.out.println("Se acepta la secuencia ");
        else
            System.out.println("Se rechaza la secuencia ");
    }
    
    public static void procS(){
        // <S> --> <E>{Resultado} 
        
        boolean res=false;
        
        switch (sim) {
            case '|':case '&':case '<':case '>':case '=':case '!':case '+':case '-':case '*':case '/':case '^':case 'i':
                    NoTerminal s1 = new NoTerminal("s1",0,0);
                    procELO(s1);
                    resultado(s1.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
        
    public static void procELO(NoTerminal s1){
        // 2.	<ELO> s1 = <EL2> s2 <ELO_L> i1,s3
        
        switch (sim) {
            case '|':case '&':case '<':case '>':case '=':case '!':case '+':case '-':case '*':case '/':case '^':case 'i':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procEL2(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procELO_L(s2, s3);
                    s1.setValor(s3.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procELO_L(NoTerminal i1, NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '|':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procEL2(s2);
                    pRelacional(i1, s2);
                    NoTerminal s4 = new NoTerminal("s4",0,0);
                    procOR(i1, s2, s4);
                    NoTerminal s5 = new NoTerminal("s5",0,0);
                    procELO_L(s4, s5);
                    s1.setValor(s5.getValor());
                    return;
            case ' ':
                s1.setValor(i1.getValor());
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procEL2(NoTerminal s1){
        // 2.	<ELO> s1 = <EL2> s2 <ELO_L> i1,s3
        
        switch (sim) {
            case '&':case '<':case '>':case '=':case '!':case '+':case '-':case '*':case '/':case '^':case 'i':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procER(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procEL2_L(s2, s3);
                    s1.setValor(s3.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procEL2_L(NoTerminal i1, NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '&':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procER(s2);
                    pRelacional(i1, s2);
                    NoTerminal s4 = new NoTerminal("s4",0,0);
                    procAND(i1, s2, s4);
                    NoTerminal s5 = new NoTerminal("s5",0,0);
                    procEL2_L(s4, s5);
                    s1.setValor(s5.getValor());
                    return;
            case ' ':
                s1.setValor(i1.getValor());
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procER(NoTerminal s1){
        // 2.	<ELO> s1 = <EL2> s2 <ELO_L> i1,s3
        
        switch (sim) {
            case '<':case '>':case '=':case '!':case '+':case '-':case '*':case '/':case '^':case 'i':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procE(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procER_L(s2, s3);
                    s1.setValor(s3.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procER_L(NoTerminal i1, NoTerminal s1){
        // 2.	<ELO> s1 = <EL2> s2 <ELO_L> i1,s3
        
        switch (sim) {
            case '<':case '>':case '=':case '!':case '+':case '-':case '*':case '/':case '^':case 'i':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procORR(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procE(s3);
                    NoTerminal s4 = new NoTerminal("s4",0,0);
                    pComparar(i1, s3, s2, s4);
                    s1.setValor(s4.getValor());
                    return;
            case ' ':
                s1.setValor(i1.getValor());
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procORR(NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '<':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procME(s2);
                    s1.setValor(s2.getValor());
                    return;
            case '>':
                    avance();
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procMA(s3);
                    s1.setValor(s3.getValor());
                    return;
            case '=':
                    avance();
                    NoTerminal s4 = new NoTerminal("s4",0,0);
                    procIG(s4);
                    s1.setValor(s4.getValor());
                    return;
            case '!':
                    avance();
                    NoTerminal s5 = new NoTerminal("s5",0,0);
                    procDI(s5);
                    s1.setValor(s5.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procME(NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(-1);
                    return;
            case ' ':
                s1.setValor(-2);
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procMA(NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(-3);
                    return;
            case ' ':
                s1.setValor(-4);
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procIG(NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(-5);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procDI(NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '=':
                    avance();
                    s1.setValor(-6);
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procE(NoTerminal s1){
        // 2.	<ELO> s1 = <EL2> s2 <ELO_L> i1,s3
        
        switch (sim) {
            case '+':case '-':case '*':case '/':case '^':case 'i':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procT(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procE_L(s2, s3);
                    s1.setValor(s3.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procE_L(NoTerminal i1, NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '+':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procT(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    suma(i1.getValor(), s2.getValor(), s3);
                    NoTerminal s4 = new NoTerminal("s4",0,0);
                    procE_L(s3, s4);
                    s1.setValor(s4.getValor());
                    return;
            case '-':
                    avance();
                    NoTerminal s5 = new NoTerminal("s5",0,0);
                    procT(s5);
                    NoTerminal s6 = new NoTerminal("s6",0,0);
                    resta(i1.getValor(), s5.getValor(), s6);
                    NoTerminal s7 = new NoTerminal("s7",0,0);
                    procE_L(s6, s7);
                    s1.setValor(s7.getValor());
                    return;
            case ' ':
                s1.setValor(i1.getValor());
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procT(NoTerminal s1){
        // 2.	<ELO> s1 = <EL2> s2 <ELO_L> i1,s3
        
        switch (sim) {
            case '*':case '/':case '^':case 'i':
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procF(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    procT_L(s2, s3);
                    s1.setValor(s3.getValor());
                    return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
    
    public static void procT_L(NoTerminal i1, NoTerminal s1){
        // 3.	<ELO_L> i1,s1 = | <EL2> s2{pRelacional} i2,i3 {procOR} i4,i5,s4 <ELO_L> i6,s5
        
        switch (sim) {
            case '*':
                    avance();
                    NoTerminal s2 = new NoTerminal("s2",0,0);
                    procF(s2);
                    NoTerminal s3 = new NoTerminal("s3",0,0);
                    multiplicacion(i1.getValor(), s2.getValor(), s3);
                    NoTerminal s4 = new NoTerminal("s4",0,0);
                    procT_L(s3, s4);
                    s1.setValor(s4.getValor());
                    return;
            case '/':
                    avance();
                    NoTerminal s5 = new NoTerminal("s5",0,0);
                    procF(s5);
                    NoTerminal s6 = new NoTerminal("s6",0,0);
                    division(i1.getValor(), s5.getValor(), s6);
                    NoTerminal s7 = new NoTerminal("s7",0,0);
                    procT_L(s6, s7);
                    s1.setValor(s7.getValor());
                    return;
            case ' ':
                s1.setValor(i1.getValor());
                return;
            default: 
                    System.out.println("Secuencia"+cad+" no se acepta");
                    rechace();
        }
    }
}
