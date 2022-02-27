class Linkedlist_Stack {
 
    private class Node {
    	
        int Data;
        Node Node_Type;
    }

    Node Top_Item;
    
    Linkedlist_Stack()
    {
        this.Top_Item = null; // Vaciar lista
    }
 
    // Agregar datos
    public void Add_Data(int x) 
    {
        // Crear un nodo temporal para colocar el dato al principio
        Node Temporal_Storage = new Node();
 
        // Iniciar data 
        Temporal_Storage.Data = x;
 
        // Poner la referencia en el Nodo
        Temporal_Storage.Node_Type = Top_Item;
 
        // Actulaizar la referencia para settear el item hasta arriba.
        Top_Item = Temporal_Storage;
    }
 
    // Recuperar dato hasta arriba de la lista
    public int Get_Top()
    {
        return Top_Item.Data;
    }
 
    public void Get_Item(int x)
    {
    	// WIP
    }
    
    public void Delete() // Eliminar Item
    {
        Top_Item = (Top_Item).Node_Type; //Cambiar el top pointer al item abajo para eliminar el item hasta arriba de la lista
    }
}