package nikita.ivanov.taskflow_back_end.habits;

import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitsController {

    @Autowired
    HabitsService habitsService;

    //creazione abitudine
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habits createHabit(@RequestBody @Validated HabitsCreateDTO habit, @AuthenticationPrincipal Users user){
        return this.habitsService.createHabit(habit, user);
    }

    //Lista di tutte le abitudini
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Habits> habitsList(@AuthenticationPrincipal Users user){
        return this.habitsService.habitsList(user.getId());
    }

    //Abitudine per ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Habits habitPerId(@PathVariable long id, @AuthenticationPrincipal Users user){
        return this.habitsService.habitPerId(id, user.getId());
    }

    //Modifica nome abitudine (PATCH)
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Habits updateNameHabit(@RequestBody @Validated HabitsCreateDTO updatedHabit, @PathVariable long id, @AuthenticationPrincipal Users user){
        return this.habitsService.updateHabit(updatedHabit, id, user.getId());
    }

    //Modifica days done abitudine (PATCH)
    @PatchMapping("/days/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Habits updateDaysDoneHabit(@RequestBody HabitsPatchDaysDTO updatedHabit, @PathVariable long id, @AuthenticationPrincipal Users user){
        return this.habitsService.updateHabitDays(updatedHabit,id, user.getId());
    }

    //Elimina abitudine
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHabit(@PathVariable long id, @AuthenticationPrincipal Users user){
        this.habitsService.findByIdAndDelete(id, user.getId());
    }



}
