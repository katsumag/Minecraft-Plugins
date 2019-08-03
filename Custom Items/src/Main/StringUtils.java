package Main;

public class StringUtils {
	
	public static int romanToInt(String s) {
	    if(s == null){
	        return 0;
	    }
	    
	    int length = s.toString().length();
	    int sum = 0;
	    int pre = 0;
	    
	    for(int i = length - 1; i >= 0; i--){
	        int cur = romanTable(s.toString().charAt(i));
	        
	        if(i == length - 1){
	            sum = sum + cur;
	        }else{
	           if(cur < pre){
	               sum = sum - cur;
	           }else{
	               sum = sum + cur;
	           }
	        }
	        pre = cur;
	    }
	    
	    return sum;
	}

	public static int romanTable(char string){
	    int num = 0;
	    switch(string){
	        case 'I':
	            num = 1;
	            break;
	        case 'V':
	            num = 5;
	            break;
	         case 'X':
	            num = 10;
	            break;
	         case 'L':
	             num = 50;
	             break;
	         case 'C':
	             num = 100;
	             break;
	         case 'D':
	             num = 500;
	             break;
	         case 'M':
	             num = 1000;
	             break;
	         default:
	             num = 0;
	             break;
	    }
	    return num;
	}
	
}
