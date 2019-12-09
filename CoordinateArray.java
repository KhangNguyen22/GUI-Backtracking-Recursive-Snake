package LongestPath;

public class CoordinateArray {
    Coordinate[] path;

    public void append(Coordinate current){
        if (path == null){
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

    public Coordinate pop(){
        Coordinate lastElement = path[path.length - 1];

        Coordinate[] temp = new Coordinate[path.length - 1];

        for (int i = 0; i < path.length -1; i++){
            temp[i] = path[i];
        }
        path = temp;

        return lastElement;
        //To do pop off the end, look on google how to manually do arraylist using arrays only.
    }

    public Coordinate[] getPath(){
        return path;
    }

    public int getPathLength(){
        return path.length;
    }


}
