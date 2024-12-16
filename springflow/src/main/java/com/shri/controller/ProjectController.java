package com.shri.controller;
import com.shri.model.Chat;
import com.shri.model.Invitation;
import com.shri.model.Project;
import com.shri.model.User;
import com.shri.request.InvitationRequest;
import com.shri.response.MessageResponse;
import com.shri.service.InvitationService;
import com.shri.service.ProjectService;
import com.shri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController
{

    @Autowired
    private ProjectService projectService;


    @Autowired
    private InvitationService invitationService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false)String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt


    ) throws Exception
    {
                   User user=userService.findUserProfileByJwt(jwt);
                   List<Project>projects=projectService.getProjectByTeam(user,category,tag);

             return  new ResponseEntity<>(projects, HttpStatus.OK);

    }



    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    )throws  Exception
        {
            User user=userService.findUserProfileByJwt(jwt);
               Project project=projectService.getProjectById(projectId);
               return  new ResponseEntity<>(project,HttpStatus.OK);
     }


     @PostMapping()
   public ResponseEntity<Project> createProject(
           @RequestHeader("Authorization") String jwt,
           @RequestBody Project project
     )  throws  Exception
     {
         User user =userService.findUserProfileByJwt(jwt);
         Project project1=projectService.createProject(project,user);
         return  new ResponseEntity<>(project1,HttpStatus.OK);
     }

@PatchMapping("/{projectId}")
    public  ResponseEntity<Project>updateProject(

            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
)throws  Exception
    {
             User user=userService.findUserProfileByJwt(jwt);
             Project updatedProject=projectService.updateProject(project,projectId);
             return new ResponseEntity<>(updatedProject,HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public  ResponseEntity<MessageResponse>deleteProject(

            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt

    )throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId,user.getId());
        MessageResponse res=new MessageResponse("Project Deleted");
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    @GetMapping("/search")
    public  ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        List<Project> projects=projectService.searchProject(keyword,user);
        return new ResponseEntity<>(projects,HttpStatus.OK);

    }

    @GetMapping("/{projectId}/chat")
    public  ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);
        Chat chat=projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat,HttpStatus.OK);
    }

    @PostMapping("/invite")
    public  ResponseEntity<MessageResponse>inviteProject(
            @RequestHeader("Authorization") String jwt,
            @RequestBody InvitationRequest invitationRequest

            ) throws  Exception
    {
        User user=userService.findUserProfileByJwt(jwt);

        invitationService.sendInvitation(invitationRequest.getEmail(),invitationRequest.getProjectId());
        MessageResponse res=new MessageResponse("Invitation Sent Sucesss");
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }


    @GetMapping("/accept/{token}")
    public  ResponseEntity<Invitation>acceptInvite(
            @RequestParam String token,
            @RequestHeader("Authorization") String jwt


) throws  Exception
{
       User user=userService.findUserProfileByJwt(jwt);
       Invitation invitation=invitationService.acceptInvitation(token, user.getId());
       projectService.addUserToProject(invitation.getProjectId(),user.getId());
       return new ResponseEntity<>(invitation,HttpStatus.ACCEPTED);

}
}





