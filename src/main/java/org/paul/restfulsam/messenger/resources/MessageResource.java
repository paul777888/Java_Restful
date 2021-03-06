package org.paul.restfulsam.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.paul.restfulsam.messenger.model.Message;
import org.paul.restfulsam.messenger.service.MessageService;;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	@GET
	@Path("/test")
	public String test(){
		return "test";
	}
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
		if(messageFilterBean.getYear() > 0){
			System.out.println("using year param method");
			return messageService.getAllMessagesForYear(messageFilterBean.getYear());
		}
		if(messageFilterBean.getStart()>=0 && messageFilterBean.getSize() >=0){
			System.out.println("using start+size param method");
			return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		System.out.println("using non-param method");
		return messageService.getAllMessages();
		
	}
	

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {	
		return messageService.getMessage(id);
	}
	
	@POST
	public Message addMessage(Message message){
		
		return messageService.addMessage(message);
		
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		System.out.println("redirecting...");
		return new CommentResource();
	}
}
