package Game;

import java.util.ArrayList;
import java.util.HashMap;

import MonoBehavior.Collider;
import Util.Layer;

public class ColliderManager {
	private static boolean DEBUG_MODE = false;
	
	private HashMap<Layer, ArrayList<Collider>> colliderMap;
	
	public ColliderManager() {
		colliderMap = new HashMap<Layer, ArrayList<Collider>>();
	}
	
	public void AddCollider(Collider _collider) {
		if(DEBUG_MODE)
			System.out.println(_collider.gameObject.name);
		if(colliderMap.containsKey(_collider.gameObject.layer)) {
			colliderMap.get(_collider.gameObject.layer).add(_collider);
		}
		else {
			ArrayList<Collider> temp = new ArrayList<Collider>();
			temp.add(_collider);
			colliderMap.put(_collider.gameObject.layer, temp);
		}
	}
	
	public void RemoveCollider(Collider _collider) {
		colliderMap.get(_collider.gameObject.layer).remove(_collider);
	}
	
	public ArrayList<Collider> GetColliderList(Layer _layer){
		if(DEBUG_MODE)
			System.out.println(colliderMap);
		return colliderMap.get(_layer);
	}

}
