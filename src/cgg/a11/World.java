package cgg.a11;

import java.util.ArrayList;
import java.util.List;

import cgg.Light;

public class World {
    protected List<Light> lightList;
    protected Group scene;

    public World(List<Light> lightList, Group scene){
        this.lightList = lightList;
        this.scene = scene;
    }

    public World(Group scene, List<Light> lightList){
        this.lightList = lightList;
        this.scene = scene;
    }

    public World(Group scene){
        this.lightList = new ArrayList<Light>();
        this.scene = scene;
    }

    public void addLight(Light light){
        lightList.add(light);
    }
}
