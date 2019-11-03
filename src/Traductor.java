import java.lang.Math;

/**
 * <p>Clase para realizar la conversion de calendarios.</p>
 */
public class Traductor{

    /* Dia gregoriano. */
    private int dia;
    /* Mes gregoriano. */
    private int mes;
    /* Año gregoriano. */
    private int año;
    /* numeral del año. */
    private int numAño;
    /*nombre del año . */
    private String nomAño;
    /*nombre de la trecena . */
    private String trecena;
    /* num del dia lunar. */
    private int numDiaLunar;
    /* nombre del dia lunar. */
    private String nomDiaLunar;

    /* Construye un traductor con el dia, el mes y el año. */
    public Traductor(int dia, int mes, int año){
        dia = this.dia ;
        mes = this.mes;
        año = this.año;
    }

    /**
    * Funcion que convierte una fecha gregoriana anterior de 1582 
    * a juliana.
    * @param entero del dia.
    * @param entero del mes.
    * @param entero del año.
    * @return entero con el dia juliano antes de 1582.
    */
    public int gregorianToJulianBefore1582(int dd, int mm, int aaaa){
        int diaAux = dd;
        int mesAux = mm;
        int añoAux = aaaa;
        if (añoAux<0) 
            añoAux = añoAux +1;
        if(mesAux < 3){
            añoAux--;
            mesAux += 12;
        }
        Double e = Math.floor(365.25*(añoAux + 4716));
        Double f = Math.floor(30.6001 *(mesAux + 1));
        Double aux = 0.0+ diaAux + e + f -1524;
        return aux.intValue() ; 
    }

    /**
    * Funcion que convierte una fecha gregoriana posterior de 1582 
    * a juliano.
    * @param entero del dia.
    * @param entero del mes.
    * @param entero del año.
    * @return entero con el dia juliano despues de 1582.
    */
    public int gregorianToJulianAfter1582(int dd, int mm, int aaaa){
        Double a = Math.floor((14-mm)/12);
        Double y = aaaa + 4800 -a;
        Double m = mm + 12*a -3;
        Double z =  dd + Math.floor ((153*m +2)/ 5) + 365* y + Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400) -32045;
        int za = z.intValue();
        return za;
    }

    /**
    * Funcion que recibe un juliano y regresa el año en el Tonalpohualli.
    * @param entero con el dia juliano
    * @return String con el año en el Tonalpohualli.
    */
    public String añoTonalpohualli(int juliano){
        int dia = juliano -142;
        Double año = Math.floor(dia/365);
        String[] años = new String[4];
        años[0] = "pedernal";
        años[1] = "casa";
        años[2] = "conejo";
        años[3] = "caña";
        año = (año%52)+52 -8;
        Double v1 = (año%13) +1; 
        numAño = v1.intValue();
        Double v2 = (año%4); 
        int nomAño2 = v2.intValue();
        nomAño = años[nomAño2];
        return "El año es " + numAño + "-" +  años[nomAño2] + ".";
    }

    /**
    * Funcion que recibe un juliano y regresa el dia y la trecena en el Tonalpohualli.
    * @param entero con el dia juliano.
    * @return String con el dia y la trecena en el Tonalpohualli.
    */  
    public String julianToTonalpohualli(int juliano){
        Double i,j,j1,j2, a,m , d, a1,a2, d1, d2, x, l; //muchas variables lo se, pero asi salio... 
        Double daysum, corr, yearc, tonalnum,     //y se veia feo declarandoras en lineas distintas.
        tonalname, yearscorr, xihuitltemp, 
        xihuitlnum, xihuitlname;

        j = juliano + 0.0;
        corr = 584283.0 ;
        yearc = 162.0;
        daysum = j +5757 + 584283 -corr ;
        tonalnum = ((daysum -6)%13)+1;
        tonalnum = tonalnum -(tonalnum % 1);
        tonalname = ((daysum -1) % 20) +1;
        tonalname = tonalname -(tonalname%1);
        xihuitltemp =(daysum -39) % 365;
        xihuitlnum = ((daysum +353 -xihuitltemp) % 13)+1;
        xihuitlname = ((daysum +358 -xihuitltemp) % 20) +1 ;
        j1 = ((j-1 +5297)%13520)+1 ;
        j2 = j1;
        i= j2;
        a= Math.floor( ((i-1) / 260 )) +1 ;
        d = ((i-1) % 260) +1 ;

        x = Math.floor (((d-1) /13)) +1;
        l = ( ( (x-1) * 13) %20) +1;

        a1 = ( (a-1) % 13)+1;
        a2 = ( (a-1) % 4) +1;
        d1 = ( (d-1) % 13)+1;
        d2 = ( (d-1) % 20)+1;
        trecena = mesTrecenaLunar(l.intValue());
        numDiaLunar = d1.intValue();
        nomDiaLunar = mesTrecenaLunar(d2.intValue());
        return "El dia lunar es " + d1 + "-"
                + mesTrecenaLunar(d2.intValue()) + "."+
                "\nLa trecena lunar es " 
                + mesTrecenaLunar(l.intValue()) +".";
    }
    
    /**
    *Metodo que traduce la fecha del Tonalpohualli a Nahuatl.
    * @return cadena con la fecha en nahuatl.
    */
    public String fechaToTonalpohualli(){
        return  "El dia Lunar en Tonalpohualli es " 
                    + numDiaLunar + "-" + toNahuatl(nomDiaLunar) + ". \n"
                    + "La trecena Lunar en Tonalpohualli es " + toNahuatl(trecena) + ". \n"
                    +"El año en Nahuatl es " + numAño + "-"+ toNahuatl(nomAño) ;
    }

    private String mesTrecenaLunar(int a){
        switch (a) {
            case 1 : return "caiman";
            case 2 : return "viento";
            case 3 : return "casa";
            case 4 : return "lagartija";
            case 5 : return "Serpiente";
            case 6 : return "muerte";
            case 7 : return "venado";
            case 8 : return "conejo";
            case 9 : return "agua";
            case 10 : return "perro";
            case 11 : return "mono";
            case 12 : return "hierba";
            case 13 : return "caña";
            case 14 : return "jaguar";
            case 15 : return "aguila";
            case 16 : return "buitre";
            case 17 : return "movimiento";
            case 18 : return "pedernal";
            case 19 : return "lluvia";
            case 20 : return "flor";
            default:
                  return "null";  
        }
    }
    private String toNahuatl(String a){
        switch (a) {
            case "caiman" : return "Cipactli";
            case "viento" : return "Ehecatl";
            case "casa" : return "Calli";
            case "lagartija" : return "Cuetzpalin";
            case "Serpiente" : return "Coatl";
            case "muerte" : return "Miquiztli";
            case "venado" : return "Mazatl";
            case "conejo" : return "Tochtli";
            case "agua" : return "Atl";
            case "perro" : return "Itzcuintli";
            case "mono" : return "Ozomatli";
            case "hierba" : return "Malinalli";
            case "caña" : return "Acatl";
            case "jaguar" : return "Ocelotl";
            case "aguila" : return "Cuauhtli";
            case "buitre" : return "Cozcaquauhtli";
            case "movimiento" : return "Ollin";
            case "pedernal" : return "Tecpatl";
            case "lluvia" : return "Quiahuitl";
            case "flor" : return "Xochitl";
            default:
                  return "null"; 
        }
    }
}
