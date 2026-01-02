import java.util.*;
class Pair{
    String node;
    double val;
    Pair(String s,double v){
        node=s;
        val=v;
    }
}
public class Q2 {
    static double[] res;
    static void find(Map<String,List<Pair>> map,String cur,double val,String dest,int idx,Set<String> vis){
        if(vis.contains(cur))return;
        if(cur.equals(dest)&&map.containsKey(cur)){
            res[idx]=val;
            return;
        }
        vis.add(cur);
        for(Pair nxt:map.getOrDefault(cur,new ArrayList<>())){
            find(map,nxt.node,val*nxt.val,dest,idx,vis);
        }
        vis.remove(cur);
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Map<String,List<Pair>> map= new HashMap<>();
        for(int i=0;i<n;i++){
            String x=in.next();
            String y=in.next();
            double w=in.nextDouble();
            map.computeIfAbsent(x,k->new ArrayList<>());
            map.computeIfAbsent(y,k->new ArrayList<>());
            map.get(x).add(new Pair(y,w));
            map.get(y).add(new Pair(x,1/w));
        }
        int m=in.nextInt();
        res=new double[m];
        int idx=0;
        Set<String> vis=new HashSet<>();
        Arrays.fill(res,-1);
        for(int i=0;i<m;i++){
            String x=in.next();
            String y=in.next();
            find(map,x,1.0,y,idx,vis);
            idx++;
        }
        System.out.println(res);
    }
}