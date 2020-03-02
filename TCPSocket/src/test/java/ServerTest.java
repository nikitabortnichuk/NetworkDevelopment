import com.kson.calculator.server.MyServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServerTest {


    @Test
    public void shouldReturnCharactersListWithoutSpaces(){

        String expression = "2 - 2";

        String[] actual = MyServer.parseExpression(expression);

        String[] expected = new String[]{
                "2", "-", "2"
        };

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldThrowException(){
        String expression = "2  2";

        assertThrows(RuntimeException.class, () -> MyServer.parseExpression(expression));
    }

}
