import java.util.Scanner;
import java.lang.Math;

/**
* Proyecto 1 : Correlacion de Calendarios.
*/
public class Main{

    public static void main(String[] args) {

        /*int para el dia recibido en gregoriano */
        int dia=0;

        /*String para el mes recibido en gregoriano*/
        String mes= "";

        /*int para el año recibido en gregoriano*/
        int año = 0;

        if(args.length == 0){
            Scanner sc = new Scanner(System.in);
            //DIA
            System.out.println("Ingresa el dia con 2 digitos");
            String cadena = sc.nextLine();
            if(esDia(cadena))
                dia = Integer.parseInt(cadena);
            else{
                System.err.println("Por favor ingrese una dia valido");
                return ;
            }
            //MES
            System.out.println("Ingresa las primeras 3 letras del mes.");
            String cadena2 = sc.nextLine();
            if (cadena2.length() > 2)
                cadena2 = cadena2.substring(0,3);
            else {
                System.err.println("Por favor ingrese un mes valido");
                return ;
            }
            if (esMes(cadena2) )
                 mes = cadena2;
            else{
                System.err.println("Por favor ingrese un mes valido");
                return ;
            }
            //AÑO
            System.out.println("Ingresa los 4 digitos del año.");
            String cadena3 = sc.nextLine();
            if(esAño(cadena3))
                 año = Integer.parseInt(cadena3);
             else {
                System.err.println("Por favor ingrese una año valido");
                return ;
             }
        }
        //Args con datos 
        if (args.length !=  0) {
            String cadena;
            if (args.length<=1) 
                cadena = convertDate(args[0]);
            else
                cadena = args[0] + args [1] + args [2];
            String diaAux = cadena.substring(0,2);
            mes = cadena.substring(2,4);
            String añoAux = cadena.substring(4,cadena.length());
            if(esDia(diaAux)&&esMes(mes)&&esAño(añoAux)== false)
                System.err.print("Por favor introduce una fecha valida de la forma dd/mm/aaaa usando diagonales, espacios, guiones, comas o puntos como separacion");
            dia =  Integer.parseInt(diaAux);
            año = Integer.parseInt(añoAux);
        }
        mes = mes.toLowerCase();
        int numMes = numMes(mes);
        if (año == 1582 && numMes == 10 && dia>=5 &&dia<15) {
             System.err.println("Fecha invalida por la Reforma Gregoriana");
             return ;
         }
        System.out.println("_________________________________________________");
        System.out.println("_________________________________________________");
        Traductor traductor = new Traductor(dia,numMes,año);
            int julianDate = 0;
            if ((año>1582) || (año == 1582 && numMes > 10) || (año == 1582 && numMes == 10 && dia >=15)){
                julianDate = traductor.gregorianToJulianAfter1582(dia,numMes,año);
            }
            else{
                julianDate = traductor.gregorianToJulianBefore1582(dia,numMes,año);
            }
            if (julianDate == 0) {
                System.err.println("Has introducido una fecha invalida");
            }
         System.out.println("\tCorrespondencia de Antonio Caso.");
         System.out.println("*A partir de 1582 se tomara como referencia la fecha gregoriana, no la juliana.*");  
        System.out.println("\t - ESPAÑOL -");
        System.out.println(traductor.julianToTonalpohualli(julianDate));
        System.out.println(traductor.añoTonalpohualli(julianDate));
        System.out.println("\t - NAHUATL -");
        System.out.println(traductor.fechaToTonalpohualli());
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
        System.out.println("Basado en azteccalendar.com*");
        System.out.println("*Exceptuando 1582, donde en la pagina se aplica la reforma gregoriana a inicio de año,\ncuando debio ser aplicada en octubre.");
    }
    
    
   private static String convertDate(String fecha) {
        fecha = fecha.replaceAll("-", "");
        fecha = fecha.replaceAll("/", "");
        fecha = fecha.replaceAll(" ", "");
        fecha = fecha.replaceAll("_", "");
        fecha = fecha.replaceAll(",", "");
        fecha = fecha.replaceAll(".", "");
        return fecha;
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    private static boolean esDia(String cadena){
        int dia = 0;
        if (isNumeric(cadena))
            dia = Integer.parseInt(cadena);
            if (dia>0 && dia<=31)
                return true;    
        return false;
    } 

    private static boolean esMes(String cadena){
        cadena = cadena.toLowerCase();
        if (cadena.equals("ene") ||cadena.equals("feb") ||cadena.equals("mar") ||cadena.equals("abr") ||
            cadena.equals("may") ||cadena.equals("jun") ||cadena.equals("jul") ||cadena.equals("ago") ||
            cadena.equals("sep") ||cadena.equals("oct") ||cadena.equals("nov") ||cadena.equals("dic") )
                    return true;
         return false;
    }       

    private static int numMes(String cadena){
        switch (cadena) {
            case "ene": return 1; 
            case "feb": return 2; 
            case "mar": return 3; 
            case "abr": return 4; 
            case "may": return 5; 
            case "jun": return 6; 
            case "jul": return 7; 
            case "ago": return 8; 
            case "sep": return 9; 
            case "oct": return 10; 
            case "nov": return 11; 
            case "dic": return 12; 
            default:
                return 0;
        }
    }
    
    private static boolean esAño(String cadena){
        int año =0;
        if (isNumeric(cadena))
            año = Integer.parseInt(cadena);
            if (año>1000 && año< 9999)
                return true;
        return false;
    }
}