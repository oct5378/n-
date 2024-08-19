public class nmoku{
    public static void main(String[] args){
        int x=Integer.parseInt(System.console().readLine("x="));
        int y=Integer.parseInt(System.console().readLine("y="));
        int[] ban=new int[x*y];
        String a,a2,b;

        for (int ty=0;ty<y+1;ty++){
            for (int tx=0;tx<x+1;tx++){
                if (ty==0){a="┌"; b="┬"; a2="┐";}
                else if (ty==y-1){a="├"; b="┼"; a2="┤";}
                else {a="└"; b="┴"; a2="┘";}

            }
        }
        
        System.out.println("┌───┬───┐\n│ ○ │ ○ │\n├───┼───┤\n│ ○ │ ○ │\n└───┴───┘");
    }
}

/*─ │ ┌ ┐ └ ┘ ├ ┬ ┤ ┴ ┼ ○ ●
┌───┬───┐
│ ○ │ ○ │
├───┼───┤
│ ○ │ ○ │
└───┴───┘
*/