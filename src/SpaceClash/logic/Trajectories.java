package SpaceClash.logic;

import java.util.ArrayList;

public class Trajectories {

    private Trajectories trajectories;
    protected int[] x;
    protected int[] y;

    public Trajectories(){

    }

    //---------------------------------------------------------------
    // PRIVATE INSTANCE METHODS
    //---------------------------------------------------------------

    private ArrayList<Point> trajectory(int xPosition, int yPosition, Trajectories trajectories){
        int[] x2=new int[trajectories.x.length];
        int[] y2=new int[trajectories.y.length];
        for(int i=0; i<x2.length; i++){
            if(i==0){
                x2[i]=0;
                y2[i]=0;
            }else{
                x2[i]=trajectories.x[i]-trajectories.x[i-1];
                y2[i]=trajectories.y[i]-trajectories.y[i-1];
            }
        }
        int[] xfinal=new int[x2.length];
        int[] yfinal=new int[y2.length];

        for(int i=0; i<xfinal.length; i++){
            if(i==0){
                xfinal[i]=xPosition;
                yfinal[i]=yPosition;
            }else {
                xfinal[i]=x2[i]+xfinal[i-1];
                yfinal[i]=y2[i]+yfinal[i-1];
            }
        }
        return getPoints(xfinal, yfinal);
    }

    private ArrayList<Point> getPoints(int[] x, int[] y) {
        ArrayList<Point> points=new ArrayList<>();
        for(int i=0; i<x.length-1; i++){
            int a=y[i+1]-y[i];
            int b=-(x[i+1]-x[i]);
            int c=(y[i]*x[i+1])-(x[i]*y[i+1]);
            if(x[i]<x[i+1]) {
                for (int xstraightline = x[i]; xstraightline < x[i + 1]; xstraightline++) {
                    int ystraightline = ((-a * xstraightline) - c) / b;
                    Point p = new Point(xstraightline, ystraightline);
                    points.add(p);
                }
            }
            else {
                for (int xstraightline = x[i]; xstraightline > x[i + 1]; xstraightline--) {
                    int ystraightline = ((-a * xstraightline) - c) / b;
                    Point p = new Point(xstraightline, ystraightline);
                    points.add(p);
                }
            }
        }
        return points;
    }

    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------

    public ArrayList<Point> getRandomTrajectory(int selection, int xposition, int yposition){
        ArrayList<Point> trajectorySelected=new ArrayList<>();
        if(selection==0){
            trajectorySelected=getFirstTrajectory(xposition,yposition);
        }else if(selection==1){
            trajectorySelected=getSecondTrajectory(xposition,yposition);
        }else if(selection==2){
            trajectorySelected=getThirdTrajectory(xposition,yposition);
        }
        return trajectorySelected;
    }

    public ArrayList<Point> getFirstTrajectory(int xposition, int yposition){
        trajectories=new FirstTrajectory();
        return trajectory(xposition,yposition,trajectories);
    }

    public ArrayList<Point> getSecondTrajectory(int xposition, int yposition){
        trajectories=new SecondTrajectory();
        return trajectory(xposition,yposition,trajectories);
    }

    public ArrayList<Point> getTrajectoryLevelThree(){
        trajectories=new TrajectoryLevelThree();
        return getPoints(trajectories.x,trajectories.y);
    }

    public ArrayList<Point> getThirdTrajectory(int xposition, int yposition){
        trajectories=new ThirdTrajectory();
        return trajectory(xposition,yposition,trajectories);
    }

    public int getXMin(ArrayList<Point> trajectory){
        int xmin=trajectory.get(0).getX();
        for(int i=0; i<trajectory.size(); i++){
            if(trajectory.get(i).getX()<xmin){
                xmin=trajectory.get(i).getX();
            }
        }
        return xmin;
    }

    public int getXMax(ArrayList<Point> trajectory){
        int xmax=trajectory.get(0).getX();
        for(int i=0; i<trajectory.size(); i++){
            if(trajectory.get(i).getX()>xmax){
                xmax=trajectory.get(i).getX();
            }
        }
        return xmax;
    }


}
