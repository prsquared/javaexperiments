package com.priyesh.footballcommentary.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.priyesh.footballcommentary.entity.Team;
import com.priyesh.footballcommentary.hibernate.HibernateUtil;

public class FootballCommentaryDao {
	
	public void saveTeam(Team team){
		Session session = null;
    	Transaction tx = null;
    	try{
    		session = HibernateUtil.getSession();
    		tx = session.beginTransaction();
    		tx.setTimeout(5);
    		
    		session.save(team);
    		 
    		session.getTransaction().commit();
    		
    	}catch(RuntimeException e){
    		try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    		}
    		throw e;
    	}finally{
    		if(session!=null){
    			session.close();
    		}
    	}
		
	}
	
	@SuppressWarnings("unchecked")
	public Team getTeam(String name) {
		Session session = null;
    	Transaction tx = null;
    	try{
    		session = HibernateUtil.getSession();
    		tx = session.beginTransaction();
    		tx.setTimeout(5);
    		
    		//doSomething(session);
    		Query<Team> query = session.createQuery("from Team where name=:name");
    		query.setParameter("name", name);
    		 
    		return query.uniqueResult();
    		
    	}catch(RuntimeException e){
    		try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    		}
    		throw e;
    	}finally{
    		if(session!=null){
    			session.close();
    		}
    	}
	}
	
	@SuppressWarnings("unchecked")
	public Team getTeam(String name,Session session) {

		//doSomething(session);
    		Query<Team> query = session.createQuery("from Team where name=:name");
    		query.setParameter("name", name);
    		 
    		return query.uniqueResult();
    		
    
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeamList(List<String> names) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		Query<Team> query = session.createQuery("from Team where name in (:names)");
		query.setParameterList("name", names);
		 
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeamList(List<String> names, Session session) {
		
		Query<Team> query = session.createQuery("from Team where name in (:names)");
		query.setParameterList("name", names);
		 
		return query.list();
		
	}

	public void deleteTeam(List<String> teamNames) {
		Session session = null;
    	Transaction tx = null;
    	try{
    		session = HibernateUtil.getSession();
    		tx = session.beginTransaction();
    		tx.setTimeout(5);
    		for(String teamName:teamNames) {
    			Team team = getTeam(teamName,session);
    			session.delete(team);
    		}
    		session.getTransaction().commit();
    		
    	}catch(RuntimeException e){
    		try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    		}
    		throw e;
    	}finally{
    		if(session!=null){
    			session.close();
    		}
    	}
	}
}
