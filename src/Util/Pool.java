package Util;

import java.util.ArrayList;
import java.util.Stack;

public class Pool<T> {
	private ArrayList<T> objectPoolList;
	private Stack<Integer> indexPool;
	
	public Pool(){
		objectPoolList = new ArrayList<T>();
		indexPool = new Stack<Integer>();
	}
	
	public int AddPool(T _object) {
		objectPoolList.add(_object);
		return objectPoolList.size() - 1;
	}
	
	public boolean IsEmpty() {
		return indexPool.isEmpty();
	}
	
	public T GetPool() {
		return objectPoolList.get(indexPool.pop());
	}
	
	public void ReturnPool(int _index) {
		indexPool.push(_index);
	}
}
