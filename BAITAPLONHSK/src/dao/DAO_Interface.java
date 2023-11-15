package dao;

import java.util.ArrayList;

public interface DAO_Interface<T> {		
	
	public int them(T t);
	
	public int sua(T t);
	
	public int xoa(T t);
	
	public ArrayList<T> lietKe(T t);
	
	public T timKiem(T t);
}
