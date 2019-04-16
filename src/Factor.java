import java.util.ArrayList;
import java.util.List;

public interface Factor {

    int factorSize();

    boolean containsVar(Character var);

    List<Float> getVarValues(Character val, boolean value);

    List<Float> getAllValues();

    List<Character> getVariables();
}
