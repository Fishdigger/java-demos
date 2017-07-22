package edu.mga.itec.springboot.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository repo;
	
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<>();
		repo.findAll().forEach(topics::add);
		return topics;
	}
	
	public Topic getTopic(String id){
		return repo.findOne(id);
	}

	public void addTopic(Topic topic) {
		repo.save(topic);
	}
	
	public void editTopic(String id, Topic topic){
		repo.save(topic);
	}
	
	public void deleteTopic(String id){
		repo.delete(id);
	}
	
}
