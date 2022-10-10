/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecpbol;

/**
 *
 * @author ASUS
 */
public class ModelPetBase {
    private String id;
    private int type;
    private float maxHappy,maxHunger;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getMaxHappy() {
        return maxHappy;
    }

    public void setMaxHappy(float maxHappy) {
        this.maxHappy = maxHappy;
    }

    public float getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(float maxHunger) {
        this.maxHunger = maxHunger;
    }
    
}
