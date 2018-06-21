package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.ProductClass;

public interface ProductClassDAO {
	public int add(ProductClass productclass); 
    
    public int delete(List<ProductClass> idList); 
        
    public ProductClass selectByName(ProductClass productclass); 
    
    public ProductClass selectById(int id); 
      
    public int update(ProductClass productclass);  
        
    public List<ProductClass> list(ProductClass productclass);
     
    public int total(); 
}
