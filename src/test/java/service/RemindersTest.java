package service;

import exception.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RemindersTest {

    @InjectMocks
    Reminders reminders;

    @Test
    void shouldHaveAddTaskHappyCase() {
        //given
        String category = "Trabajo";
        String taskDescription = "comer";

        //when
        reminders.addTask(category, taskDescription);

        //then
        assertTrue(reminders.tasks().contains(taskDescription));
        assertEquals(1,reminders.tasks().size());

    }

    @Test
    void shouldHaveAllTaskByCategoryHappyCase() {
        //given
        reminders.addTask("ITBA", "Consultar Intercambio");
        reminders.addTask("ITBA", "Entregar el TPE");
        reminders.addTask("Casa", "Llamar Electricista");
        reminders.addTask("Casa", "Cambiar filtro");
        reminders.addTask("Casa", "Pagar Expensas");

        //when
        List<String> tasks = reminders.tasks("Casa");
        //then
        assertEquals(3, tasks.size());
        assertEquals("Cambiar filtro", tasks.get(0));
        assertEquals("Pagar Expensas", tasks.get(2));
    }

    @Test
    void shouldDontHaveAllTaskByCategoryWhenCategoryDontExistException() {
        //given
        reminders.addTask("ITBA", "Consultar Intercambio");
        reminders.addTask("ITBA", "Entregar el TPE");
        reminders.addTask("Casa", "Llamar Electricista");
        reminders.addTask("Casa", "Cambiar filtro");
        reminders.addTask("Casa", "Pagar Expensas");

        //when
        //then
        assertThrows(CategoryNotFoundException.class, () -> reminders.tasks("Trabajo"));
    }
}