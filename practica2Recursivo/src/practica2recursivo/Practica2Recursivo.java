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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
