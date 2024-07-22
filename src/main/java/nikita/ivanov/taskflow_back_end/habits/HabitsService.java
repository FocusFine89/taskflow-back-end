package nikita.ivanov.taskflow_back_end.habits;

import nikita.ivanov.taskflow_back_end.exceptions.NotFoundException;
import nikita.ivanov.taskflow_back_end.exceptions.UnauthorizedException;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitsService {
    @Autowired
    HabitsRepository habitsRepository;

    //creazione Abitudine
    public Habits createHabit(HabitsCreateDTO habit, Users user){
        Habits newHabit = new Habits(habit.name(),user);
        return this.habitsRepository.save(newHabit);
    }

    //Lista di tutte le abitudini per utente
    public List<Habits> habitsList(long userId){
        return this.habitsRepository.listHabits(userId);
    }

    //Abitudine per ID
    public Habits habitPerId(long habitId ,long userId){
        Habits habit = this.habitsRepository.findById(habitId).orElseThrow(()-> {throw new NotFoundException(habitId);
        });
        if(habit.getUser().getId() == userId){
            return habit;
        }else{
            throw new UnauthorizedException("Non hai il permesso di accedere a questa Abitudine");
        }


    }

    //Modifica nome abitudine
    public Habits updateHabit(HabitsCreateDTO updatedHabit, long habitId, long userId){
        Habits foundHabit = this.habitPerId(habitId, userId);
        foundHabit.setName(updatedHabit.name());
        return this.habitsRepository.save(foundHabit);
    }

    //Modifica DaysDone
    public Habits updateHabitDays(HabitsPatchDaysDTO updateHabit, long habitId, long userId){
        Habits foundhabit = this.habitPerId(habitId, userId);
        foundhabit.setDaysDone(foundhabit.getDaysDone() + updateHabit.daysDone());
        return this.habitsRepository.save(foundhabit);
    }

    //Elimina abitudine
    public void findByIdAndDelete(long habitId, long userId){
        Habits foundHabit = this.habitPerId(habitId, userId);
        this.habitsRepository.delete(foundHabit);
    }

}
