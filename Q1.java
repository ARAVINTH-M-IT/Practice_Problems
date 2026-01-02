import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1 {
    static List<String> res=new ArrayList<>();
    static void find(List<List<String>> l,int idx,StringBuilder cur){
        if(idx==l.size()){
            res.add(cur.toString());
            return;
        }
        for(String x:l.get(idx)){
            int len=cur.length();
            cur.append(x);
            find(l,idx+1,cur);
            cur.setLength(len);
        }
    }
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        String x=in.nextLine();
        List<List<String>> l=new ArrayList<>();
        List<String> ll=new ArrayList<>();
        int up=0;
        StringBuilder str=new StringBuilder();
        for(char ch:x.toCharArray()){
            if(ch==','){
                if(up==0)str.append(ch);
                else{
                    ll.add(str.toString());
                    str=new StringBuilder();
                }
            }
            else if(ch=='{'||ch=='}'){
                ll.add(str.toString());
                str=new StringBuilder();
                up+=(ch=='}'?-1:1);
                l.add(ll);
                ll=new ArrayList<>();
            }
            else str.append(ch);
        }
        if(str.length()>0)ll.add(str.toString());
        if(ll.size()>0)l.add(ll);
        System.out.println(l);
        find(l,0,new StringBuilder());
        for(String st:res)System.out.println(st);
    }
}
