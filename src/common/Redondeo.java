package common;

import org.apache.commons.lang3.StringUtils;

public class Redondeo {
	public static String redondearCentimos(String monto){	
		String[] centimos = monto.split("\\.");
		int mon = (centimos[0].equalsIgnoreCase(""))? 0:Integer.parseInt(centimos[0]);
		int num = Integer.parseInt(StringUtils.left(centimos[1],2));		
		if(num < 5){			
			num = 00;
		}
		if(num >= 5 && num < 15 ){
			num = 10;
		}	
		if(num >= 15 && num < 25){
			num = 20;
		}
		if(num >= 25 && num < 35){
			num = 30;
		}	
		if(num >= 35 && num < 45){
			num = 40;
		}
		if(num >= 45 && num < 55){
			num = 50;
		}
		if(num >= 55 && num < 65){
			num = 60;
		}
		if(num >= 65 && num < 75){
			num = 70;
		}
		if(num >= 75 && num < 85){
			num = 80;
		}
		if(num >= 85 && num < 95){
			num = 90;
		}
		if(num >= 95){
			mon += 1;
			num = 00;			
			
		}
		
		return mon+"."+num;
	}
	
}
