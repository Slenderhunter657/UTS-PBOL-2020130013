/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecpbol;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class ModelInv {
    private String playerId,item_id;
    private int item_count;
//    private HashMap<String,Integer> inventory = new HashMap<String,Integer>();

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    
//    public HashMap<String, Integer> getInventory() {
//        return inventory;
//    }
//
//    public void setInventory(HashMap<String, Integer> inventory) {
//        this.inventory = inventory;
//    }
//    
//    public void addItem(String id,int count){
//        if(this.inventory.containsKey(id)){
//            this.inventory.put(id,this.inventory.get(id)+count);
//        }else{
//            this.inventory.put(id,count);
//        }
//    }
//    
//    public int getItemCount(String id){
//        if(this.inventory.containsKey(id)){
//            return this.inventory.get(id);
//        }
//        
//        return 0;
//    }
//    
//    public void setItemCount(String id,int count){
//        this.inventory.put(id, count);
//    }

    
    
    
    //for tbv
    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    
}
