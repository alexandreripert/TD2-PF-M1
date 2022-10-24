import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class App<T> {
    Predicate<Paire<Integer, Double>> tailleTropPetite = p -> p.fst < 100;
    Predicate<Paire<Integer, Double>> tailleTropGrande = p -> p.fst > 200;
    Predicate<Paire<Integer, Double>> tailleIncorrecte = p -> tailleTropPetite.test(p) || tailleTropGrande.test(p);
    Predicate<Paire<Integer, Double>> tailleCorrecte = Predicate.not(tailleIncorrecte);
    Predicate<Paire<Integer, Double>> poidsTropLourd = p -> p.snd > 150.0;
    Predicate<Paire<Integer, Double>> poidsCorrect = Predicate.not(poidsTropLourd);
    Predicate<Paire<Integer, Double>> accesAutorise = p -> tailleCorrecte.test(p) && poidsCorrect.test(p);

    public <T> List<T> filtragePredicatif(List<Predicate<T>> conditions, List<T> elements){
        List<T> liste = new ArrayList<>();
        Predicate<T> predicat = x -> true;

        for(Predicate<T> p : conditions){
            predicat = predicat.and(p);
        }
        for(T e : elements) {
            if (predicat.test(e)){
                liste.add(e);
            }
        }
        return liste;
    }
}