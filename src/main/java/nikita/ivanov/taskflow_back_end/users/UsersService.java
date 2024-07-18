package nikita.ivanov.taskflow_back_end.users;

import nikita.ivanov.taskflow_back_end.exceptions.BadRequestException;
import nikita.ivanov.taskflow_back_end.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder bcrypt;

    //Creazione Utenti
    public Users createUser(UsersRegisterDTO newUser){
        this.usersRepository.findByEmail(newUser.email()).ifPresent(
                users -> {
                    throw new BadRequestException("Un utente con l'email: " + newUser.email() + " già esiste");
                }
        );

        //TODO aggiungere il set per l'immagine di profilo

        Users user = new Users(newUser.name(), newUser.surname(), newUser.email(), bcrypt.encode(newUser.password()));
        return usersRepository.save(user);
    }

    //Lista di tutti gli utenti (Solo gli admin avranno il permesso di accederci)
    public Page<Users> findAllUsers(int pageNumber, int pageSize){
        if (pageSize > 100) pageSize=100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return this.usersRepository.findAll(pageable);
    }

    //Cerca utente per ID
    public Users findById(long id){
        return this.usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //Cerca utente per email
    public Users findByEmail(String email){
        return this.usersRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }

    //Elimina Utente
    public void findByIdAndDelete(long id){
        Users deleteUser =  this.findById(id);
        this.usersRepository.delete(deleteUser);
    }

}
