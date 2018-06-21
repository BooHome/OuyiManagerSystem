package org.ouyikonggu.dao;

import java.util.List;

import org.ouyikonggu.moudel.Slides;

public interface SlidesDAO {
	
	public int add(Slides slides); 
    
    public int delete(List<Slides> idList); 
        
    public Slides selectByName(Slides slides); 
    
    public Slides  selectById(int id); 
      
    public int update(Slides slides);  
        
    public List<Slides> queryList(Slides slides);
    
    public List<Slides> selectByScid(int scid);
     
}
