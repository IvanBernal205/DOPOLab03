package domain;

/**
 * The EcoSafari class represents the environment where the simulation takes place.
 * It contains a grid of entities and manages their interactions.
 * 
 * @author Ivan Andres Bernal Sabogal
 * @author César Santiago Malaver Garnica 
 * @version 26-09-2026
 */
public class EcoSafari{

    private static final int SIZE=25;
    private Entity[][] cells;
    
    /**
     * Constructs a new EcoSafari
     */
    public EcoSafari() {
        cells=new Entity[SIZE][SIZE];
        someEntities();
    }

    /**
     * Pupulates the EcoSafari with some entities
     */
    public void someEntities(){  
        // Primera parte la sustentación 
        
        Elephant dumbo = new Elephant(this, 5, 5);
        Elephant babar = new Elephant(this, 10, 10);
        
        Bush mopane = new Bush(this, 12, 14);
        Bush acacia = new Bush(this, 12, 7);
        
        Storm thor = new Storm(this, 12, 3);
        Storm tempest = new Storm(this, 16, 10);
        
        Mamut bernal = new Mamut(this, 0, 0);
        Mamut malaver = new Mamut(this, 24, 24);
        
        Leopard ivan = new Leopard(this, 21,21);
        Leopard santiago = new Leopard (this, 20, 20);
        
        //Segunda parte de la sustentación

        // for(int i = 0; i<SIZE; i++){
        //     for(int j = 0; j<SIZE; j++){
        //         new Earth(this, i, j);
        //     }
        // }
        
        // Lion simba = new Lion(this, 12, 12);
        // Lion nala = new Lion(this, 3, 20);
        // Lion scar = new Lion(this, 20, 4);
        // Lion mufasa = new Lion(this, 20, 20);

        // Zebra marty = new Zebra(this, 2, 2);
        // Zebra gloria = new Zebra(this, 2, 6);
        // Zebra rayas = new Zebra(this, 6, 10);
        // Zebra luna = new Zebra(this, 8, 18);
        // Zebra sol = new Zebra(this, 10, 3);
        // Zebra estrella = new Zebra(this, 15, 14);
        // Zebra trueno = new Zebra(this, 17, 9);
        // Zebra brisa = new Zebra(this, 22, 12);
        // Zebra nube = new Zebra(this, 22, 16);
        // Zebra roca = new Zebra(this, 14, 22);
    }
    
    /**
     * Returns the size of the EcoSafari 
     * @return 
     */
    public int  getSize(){
        return SIZE;
    }

    /**
     * Determines whether a position is inside the EcoSafari
     * @param r the row
     * @param c the column
     * @return 
     */
    public boolean isInside(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * Returns the entity located at a specified position
     * @param r the row
     * @param c the column
     * @return 
     */
    public Entity get(int r,int c){
        return (isInside(r,c)? cells[r][c]: null);
    }

    /**
     * Places an entity at a specified position
     * @param r the row
     * @param c the column
     */
    public void set(Entity e, int r, int c){
        if (isInside(r,c)){ 
            cells[r][c]=e;
        }
    }

    
    /**
     * Finds the position of a specified entity
     * @param e the entity
     * @return an array {row, column} if the entity is found. null otherwise
     */
    public int[]  find(Entity e){
       int[] position=null;
       for (int r=0 ; r<SIZE && position== null; r++){
           for (int c=0 ; c<SIZE && position==null ;c++){
               if (cells[r][c]==e){
                   position=new int [] {r,c};
               }
           }
       }
       return position;
    }
    
 
    /**
     * Advances the simulation by one time step
     */
    //First, all entities execute their tic() action
    //Then, all entities execute their tac() actions
    public void ticTac(){  
        for (Entity[] row: cells){ 
            for (Entity e : row){
                if (e !=null) e.tic();
            }
        }
        
        for (Entity[] row: cells){
            for (Entity e : row){
                if(e!=null) e.tac();
            }
        }
    }

}
