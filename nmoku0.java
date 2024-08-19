public class nmoku0{
    public static void main(String[] args){
        //banサイズ入力, n入力
        System.out.println("n, size x, size y");
        int n=Integer.parseInt(System.console().readLine("n="));
        int x=Integer.parseInt(System.console().readLine("x="));
        int y=Integer.parseInt(System.console().readLine("y="));
        int px,py,length,e,u,l,round=0,count=0,point=0;
        String stone,X=x+"",Y=y+"",d;
        String[][] ban=new String[x][y];
        boolean a=true,b=true,c=true;

        //u,l用意(短辺,長辺 斜め判定用)
        if (x>y){l=x;u=y;}
        else {l=y;u=x;}

        //space用意
        if (X.length()>Y.length()){length=X.length();}
        else {length=Y.length();}
        String[] space=new String[length];
        for (int i=0;i<length;i++){space[i]="";}
        for (int i=0;i<length;i++){for (int j=0;j<i;j++){space[length-i-1]+=" ";}}

        //ban用意
        for (int tx=0;tx<x;tx++){
            for (int ty=0;ty<y;ty++){ban[tx][ty]="-";}}

        //ゲーム部分
        while(b){
            //現在の石(交互)
            if (a){a=false;stone="○";}
            else {a=true;stone="●";}
            
            //プレイヤー石配置
            //round==0の時回避
            if (round!=0){c=true;}
            else {c=false;}
            while (c){
                System.out.println(stone);
                px=Integer.parseInt(System.console().readLine("x="));
                py=Integer.parseInt(System.console().readLine("y="));
                if (1>px||px>x||1>py||py>y){System.out.println("enter position between 1 - "+x);}
                else if (ban[px-1][py-1]!="-"){System.out.println("unavailable position");}
                else {c=false;ban[px-1][py-1]=stone;}}

            //ban出力
            System.out.print(round+"\n "+space[0]+" ");
            for (int tx=0;tx<x;tx++){
                d=(tx+1)+"";
                e=d.length()-1;
                System.out.print((tx+1)+space[e]+" ");}
            for (int ty=0;ty<y;ty++){
                d=(ty+1)+"";
                e=d.length()-1;
                System.out.print("\n"+(ty+1)+space[e]+" ");
                for (int tx=0;tx<x;tx++){System.out.print(ban[tx][ty]+space[0]+" ");}}
            System.out.println();

            //n並び判定
            //横
            for (int t=0;t<y;t++){
                count=0;
                for (int s=0;s<x;s++){
                    if (ban[s][t]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}}}
            //縦
            for (int s=0;s<x;s++){
                count=0;
                for (int t=0;t<y;t++){
                    if (ban[s][t]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}}}
            //斜め1
            for (int g=0;g<u-1;g++){
                count=0;
                for (int f=0;f<g+1;f++){
                    if (ban[f][y-g+f-1]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}}}
            for (int g=0;g<u-1;g++){
                count=0;
                for (int f=0;f<g+1;f++){
                    if (ban[x-g+f-1][f]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}}}
            if (x>y){
                for (int h=0;h<l-u+1;h++){
                    count=0;
                    for (int i=0;i<u;i++){
                        if (ban[h+i][i]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}}}}
            else {
                for (int h=0;h<l-u+1;h++){
                    count=0;
                    for (int i=0;i<u;i++){
                        if (ban[i][h+i]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}}}}
            //斜め2
            for (int g=0;g<u-1;g++){
                count=0;
                for (int f=0;f<g+1;f++){
                    if (ban[f][g-f]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}}}
            for (int g=0;g<u-1;g++){
                count=0;
                for (int f=0;f<g+1;f++){
                    if (ban[x-g+f-1][y-f-1]==stone){count+=1;}
                    else {count=0;}
                    if (count>=n){point+=1;}}}
            if (x>y){
                for (int h=0;h<l-u+1;h++){
                    count=0;
                    for (int i=0;i<u;i++){
                        if (ban[h+i][y-i-1]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}}}}
            else {
                for (int h=0;h<l-u+1;h++){
                    count=0;
                    for (int i=0;i<u;i++){
                        if (ban[i][y-h-i-1]==stone){count+=1;}
                        else {count=0;}
                        if (count>=n){point+=1;}}}}
            if (point>=1){b=false;System.out.print(stone);}
            
            round+=1;
        }
        
        System.out.print(" win!");
    }
}
//System.out.print("/c="+count+"/");
/*
メモ
・オブジェクト指向？
・一回の入力で2つ以上数値入力したい
・マウス入力(GUI？)
・対戦コンピュータ
・重力
*/