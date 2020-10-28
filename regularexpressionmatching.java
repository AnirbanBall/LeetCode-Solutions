class Solution {
    Boolean dp[][];
    public boolean regex(String s , String p , int i , int j){
        if(i ==s.length() && j == p.length())return true;
        
        if(j == p.length() && i!=s.length())return false;
        if(i == s.length()){
            for(int z =j;z<p.length();z++){
                if(p.charAt(z)!='*' && z ==p.length()-1)return false;
                if(p.charAt(z)!='*'){
                    if(z+1<p.length() && p.charAt(z+1)=='*'){
                        z++;
                    }
                    else return false;
                }
            }
            return true;
        }
        
        if(dp[i][j]!=null)return dp[i][j];
        
        char pchar = p.charAt(j);
        char schar = s.charAt(i);
        
        boolean ans = false;
        
        if(pchar == '*'){
            if(p.charAt(j-1)=='.'){
                if(regex(s,p,i+1,j)){
                    ans = true;
                }
                if(regex(s,p,i+1,j+1)){
                    ans = true;
                }
                if(regex(s,p,i,j+1)){
                    ans = true;
                }
            }
            
            if( regex(s,p,i,j+1)){
                ans = true;
            }
            if(p.charAt(j-1)==schar &&  regex(s ,p,i+1,j+1)){
                ans = true;
            }
            if(p.charAt(j-1)==schar && regex(s,p,i+1,j)){
                ans = true;
            }
            
        }
        else if(pchar =='.'){
            if(j+1<p.length() && p.charAt(j+1)=='*'){
                if( regex(s ,p , i, j+2)){
                    ans = true;
                }
            }
            if(regex(s,p , i+1 , j+1)){
                ans = true;
            }
        }
        
        else{
            if(pchar == schar){
                if(regex(s ,p, i+1 , j+1)){
                    ans = true;
                }
            }
            if(j+1<p.length() && p.charAt(j+1)=='*'){
                if( regex(s ,p , i, j+2)){
                    ans = true;
                }
            }
        }
        dp[i][j] = ans;
        return ans;
    }
    public boolean isMatch(String s, String p) {
        dp = new Boolean[s.length()][p.length()];
        return regex(s ,p , 0 , 0);
    }
}
