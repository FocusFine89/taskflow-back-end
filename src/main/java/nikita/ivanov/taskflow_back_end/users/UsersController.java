package nikita.ivanov.taskflow_back_end.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    // end point /me per vedere il proprio profilo
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Users getUserProfile(@AuthenticationPrincipal Users currentUser){
        return this.usersService.findById(currentUser.getId());
    }

    //Lista di tutti gli utenti (solo admin hanno accesso a questo end-point)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Users> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size){
        return this.usersService.findAllUsers(page, size);
    }

    //Modifica Utente
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Users updateUser(@RequestBody UsersUpdateDTO updatedUser, @AuthenticationPrincipal Users user){
        return this.usersService.findByIdAndUpdate(updatedUser, user);
    }

    //Elimina un utente (Solo gli admin possono eliminare un utente)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void FindUserByIdAndDelete(@PathVariable long id){
        this.usersService.findByIdAndDelete(id);
    }


}
