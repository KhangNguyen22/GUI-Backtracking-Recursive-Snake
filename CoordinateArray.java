package LongestPath;

public class CoordinateArray {
    Coordinate[] path;

    public CoordinateArray(){

    }

    public void append(Coordinate current){
        if (path.length == 0 ){
            path = new Coordinate[1];
            path[0] = current;
        }
        else {
            Coordinate[] temp = new Coordinate[path.length + 1];

            for (int i = 0; i < path.length; i++){
                temp[i] = path[i];
            }
            temp[path.length] = current;
            path = temp;
        }
    }

    public void pop(){
        //To do pop off the end, look on google how to manually do arraylist using arrays only.
    }

    public Coordinate[] getPath(){
        return path;
    }

    public int getPathLength(){
        return path.length;
    }


}
